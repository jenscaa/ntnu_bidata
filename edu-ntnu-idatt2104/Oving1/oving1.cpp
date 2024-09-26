#include <iostream>
#include <thread>
#include <vector>
#include <cmath>
#include <algorithm>
#include <mutex>

using namespace std;

bool isPrime(int n) {
    if (n <= 1) {
        return false;
    }
    for (int i = 2; i <= sqrt(n); i++) {
        if (n % i == 0) {
            return false;
        }
    }
    return true;
}

void findPrimesInRange(int start, int end, vector<int>& primes, mutex& primesMutex, vector<vector<int>>& threadVector) {
    vector<int> threadPrimes;
    for (int i = start; i <= end; i++) {
        if (isPrime(i)) {
            unique_lock<mutex> lock(primesMutex);
            primes.push_back(i);
            threadPrimes.push_back(i);
        }
    }
    threadVector.push_back(threadPrimes);
}

int main() {
    int firstNumber = 1;
    int lastNumber = 1000;
    int numberOfThreads = 5;

    vector<int> primes;
    mutex primesMutex;
    vector<thread> threads;
    vector<vector<int>> threadVector; // A vector with vectors describing which thread found which prime number.

    int range = (lastNumber - firstNumber + 1) / numberOfThreads;
    int start = firstNumber;
    int end = start + range - 1;

    // Measure time
    auto time_start = std::chrono::high_resolution_clock::now();
    // Initialize threads
    for (int i = 0; i < numberOfThreads; i++) {
        threads.emplace_back(ref(findPrimesInRange), start, end, ref(primes), ref(primesMutex), ref(threadVector));

        start = end + 1;
        end = (i == numberOfThreads - 2) ? lastNumber : end + range;
    }

    // Join threads
    for (auto& thread : threads) {
        thread.join();
    }
    auto time_end = std::chrono::high_resolution_clock::now();
    auto duration = std::chrono::duration_cast<std::chrono::nanoseconds>(time_end - time_start);

    sort(primes.begin(), primes.end());
    cout << "Sorted list of prime numbers between " << firstNumber << " and " << lastNumber << " with " << numberOfThreads << " threads:\n";
    for (int prime : primes) {
        cout << prime << " ";
    }
    cout << "\nTotal number of primes: " << primes.size() << endl;
    cout << "Time taken: " << duration.count() / 1000 << " micro seconds" << endl;

    // Comparing threads by how many primes they found
    string number_of_primes_found;
    for (vector<int> v : threadVector) {
        number_of_primes_found += to_string(v.size()) + " ";
    }
    cout << "The threads found respectively " << number_of_primes_found  << " primes each" << endl;

    return 0;
}