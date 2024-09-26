#include <iostream>
#include <list>
#include <thread>
#include <mutex>
#include <condition_variable>
#include <functional>
#include <chrono>
# include <vector>
using namespace std;


class Workers {
private:
    list<function<void()>> tasks;
    vector<thread> threads;
    mutex tasks_mutex;
    condition_variable cv;
public:
    int number_of_threads;
    bool running;

    Workers(int number_of_threads) {
        this->number_of_threads = number_of_threads;
        this->running = true;
    }

    void start() {
        for (int i = 0; i < number_of_threads; ++i) {
            threads.emplace_back([this] { worker_thread(); });
        }
    }

    void stop() {
        {
            std::unique_lock<std::mutex> lock(tasks_mutex);
            running = false;
        }
        cv.notify_all();
    }

    void join() {
        for (auto& thread : threads) {
            if (thread.joinable()) {
                thread.join();
            }
        }
    }

    void post(const function<void()>& task) {
        {
            unique_lock<mutex> lock(tasks_mutex);
            tasks.push_back(task);
        }
        cv.notify_one();
    }

    void post_timeout(const function<void()>& task, int timeout_ms) {
        this_thread::sleep_for(chrono::milliseconds(timeout_ms));
        post(task);
    }

    void worker_thread() {
        while (running) {
            function<void()> task;
            {
                unique_lock<mutex> lock(tasks_mutex);
                cv.wait(lock, [this] { return !tasks.empty() || !running; });

                if (!tasks.empty()) {
                    task = *tasks.begin();
                    tasks.pop_front();
                }
            }
            if (task) {
                task();
            }
        }
    }
};

int main() {
    Workers worker_threads(4);
    Workers event_loop(1);

    worker_threads.start();
    event_loop.start();

    worker_threads.post([] {
        std::cout << "Task A\n";
    });

    worker_threads.post([] {
        std::cout << "Task B\n";
    });

    event_loop.post([] {
        std::cout << "Task C\n";
    });

    event_loop.post([] {
        std::cout << "Task D (always after C)\n";
    });

    event_loop.post_timeout([] {
        std::cout << "Task E (after 3 seconds)\n";
    }, 3000);

    worker_threads.stop();
    event_loop.stop();

    worker_threads.join();
    event_loop.join();

    return 0;
}