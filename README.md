# Basketball Team Simulator

## Create your basketball event

User Story that I have achieved yet for GUI:
- As a user, I want to be able to create a new team 
- As a user, I want to be able to add players to a team 
- As a user, I want each player to be assigned a random skill rating
- As a user, I want to be able to create a basketball match
- As a user, I want to be able to select two teams and add them into a match
- As a user, I want to be able to make two teams play against each other and the team with higher skill point win
- As a user, I want the two teams with same skill points to tie
- As a user, I want to be able to save my teams to file
- As a user, I want to load the teams from the file
- As a user, I want to either create new players to a team or play with loaded/existing players

User Story that I did not finish for GUI: 
- As a user, I want to be able to keep count of each team's performance in the tournament(Keep track of each team's
  Match History)
- As a user, I want to add skill points to the team that lost, so they can comeback
- As a user, I want to have the option of creating a new team with new players after each match


## Citations
- JsonReader, JsonWriter, and Writable class in the persistence package was taken from JsonReader, JsonWriter, and 
Writable class in: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git


# Instructions for Grader
- You can generate the first required event related to adding Xs to Y by pressing the buttons "Add Player to T1" and
"Add Player to T2". A pop up window will appear and you will need to enter the players name in the text field and 
press the "Random Rating" button to generate a player rating from 0-100. If you do not press the button, the player will
have a default rating of 0. Press "Add" button to add the player to team list.
- You can generate the second required event related to adding Xs to Y by pressing keys '1' to add player to team 1
and '2' to add player to team 2. Same instruction as the previous bullet point.
- You can locate my visual component by looking at the top of the gui frame. A basketball icon next to "Basketball Team 
Generator"
- You can save the state of my application by press the button "Save Teams"
- You can reload the state of my application by press the button "Load Teams"


## Phrase 4: Task 2
Adding a player: 
- Player Lebron is created with rating 7
- Player Lebron is added to the team

Playing a Match:
- Match is played: Team One Win
- Match is played: Team Two Win
- Match is played: Tie

Saving and loading a team:
- File saved for team 1
- File saved for team 2
- File loaded for team 1
- File loaded for team 2

## Phrase 4: Task 2
What I would refactor If I had more time:
1. Looking at my UML diagram, both EventPlayer and Game has an association with multiplicity of 2 to Team. I would
refactor my program to have EventPlayer to just associate to Game ,so I can perform operations on teams only inside 
the Game class to improve cohesion and reduce coupling.
2. In my UML diagram, many classes associate with the Event class. There are multiple instances of Event in the program.
Perhaps I can make use of the Singleton pattern to make Event class only having one instance and provide a global point
access to it. This will reduce the complexity of the program, and it will be easy to keep track of all the classes that 
make use of the Event instance.
3. Finally, when doing my UML diagram, I realized I did not make use exception classes in my program, instead I used
require clauses. If I had more time I would make use of exceptions to make my code more robust.