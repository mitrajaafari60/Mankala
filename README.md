Mankala-Game
Web based game.

Back-End: Java Spring Boot, Rest API, MVC architecture, JUnit, Gradle, (TomCat local server) -- Intellij 
Front-End: HTML, CSS, Javascript

Made by Mitra Jaafari- Github

Setup

Clone the git/unzip to a local directory
Run the Application found in Root src/main/java/niuniverse/mankala/demo/MankalaApplication.java
Open browser and go to http://localhost:8080/  Chrome or Firefox
The game is implemented using a basic MVC like architecture with a Rest API.

Controller

Rest API via GameController -> GameRules ->  (Model) ResponseMessage



Game Play and Rules

The player who begins with the first move picks up all the stones in any of his own six pits, and sows the
stones on to the right, one in each of the following pits, including his own big pit. No stones are put in the
opponents' big pit. If the player's last stone lands in his own big pit, he gets another turn. This can be
repeated several times before it's the other player's turn.

During the game the pits are emptied on both sides. Always when the last stone lands in an own empty pit,
the player captures his own stone and all stones in the opposite pit (the other player’s pit) and puts them in
his own big pit.

The game is over as soon as one of the sides runs out of stones. The player who still has stones in his pits
keeps them and puts them in his big pit. The winner of the game is the player who has the most stones in
his big pit.

