const clubs = ["Liverpool", "Manchester City", "Aston Villa", "Arsenal", "Tottenham Hotspur"
    , "West Ham United", "Manchester United", "Brighton", "Chelsea", "Newcastle United", "Wolverhampton"
    , "Bournemouth", "Fulham", "Crystal Palace", "Nottingham Forest", "Brentford", "Everton", "Luton Town"
    , "Burnley", "Sheffield United"];

function hide_and_reveal_video() {
    const video = document.getElementById("jota-dive");
    video.style.opacity = (video.style.opacity === "0" ? "1" : "0")
}

function increment_liverpool_score() {
    const score_element = document.getElementById("score");
    const score_text = score_element.innerText;
    const result = score_text.split('-');
    const new_liverpool_score = parseInt(result[0]) + 1;
    score_element.innerText = new_liverpool_score + "-" + result[1];
}

function increment_newcastle_score() {
    const score_element = document.getElementById("score");
    const score_text = score_element.innerText;
    const result = score_text.split('-');
    const new_newcastle_score = parseInt(result[1]) + 1;
    score_element.innerText = result[0] + "-" + new_newcastle_score;
}

function generateRandomList() {
    var random_clubs = clubs.slice();
    const sidebar = document.getElementById("sidebar");
    sidebar.innerHTML = "";

    const random_number = Math.floor(Math.random() * clubs.length);

    for (let i = 0; i < random_number; i++) {
        var new_element = document.createElement("li");
        var random_index = Math.floor(Math.random() * random_clubs.length);
        var content = document.createTextNode(random_clubs[random_index]);
        random_clubs.splice(random_index, 1);
        new_element.appendChild(content);
        sidebar.appendChild(new_element);
    }
}