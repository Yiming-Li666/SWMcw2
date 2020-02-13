# SWM cw2

Coursework 2 is about maintaining and extending a modern version of classic retro game (Packman).  

*@author Yiming LI 20031525*  
*Contains a log file to record all the working process.*   

## How to play it  
User can do setting before start the game.  
Users can choose prefered background color, obstacle color, difficulty and also the number of ghost.  
Medium will gain 1.2 times of final mark, while difficult will gain 1.5 times of final mark.  
After setting up, press OK button to confirm.  
After pressing start button, press any key to start and each time a user has three lives.  
After winning or died for three times, game will end and show the score including all the previous scores.  
User can press ESC to restart the game.

## Work flow
*SWMcw2.log is created to record all the work that has been done*
11/15  
Start the project and created gitlab project.  
**Push all the original code and images to the dev branch and also the master branch.**    
-until 11/25  
Developing the project on the dev branch.  
Finish most of the functionality part and fixed most of the bugs after testing.  
**Merge dev as a prototype to the master branch.**  
-until 11/27  
Doing the refactory on the dev branch.  
Finish the refactoring part and added comment to the code.  
**Merge dev as an improved coding version to the master branch.**  
-until 11/28  
Applied the ghost AI, tested and fixed bugs.  
**Merge dev as a finished version on coding to the master branch.**  
-until 12/08  
Finished class diagram, report and video.  
**Merge dev as a final version of all to the master branch.**  
-until 12/10  
Redo the MVC pattern on dev branch  
**Merge dev as a updated version to the master branch.**  

## Functionality (Required as task)  
1. A START screen, displaying a picture related to the game and a button that allows going to a SETUP screen.  
2. Allowing to choose background and wall colour for the game field (allowing a choice of at least 8 colours) and a button to go back to the START screen.  
3. A high score pop-up, appearing at the end of each round, showing the scores from each round, highest at the top.  
4. Two doors at the side of the play field to allow the pacman and the ghosts to transit between them (leaving at door 1 and immediately re-appearing at door 2 and vice versa).  
5. Load proper character figure.  
6. Load level descriptions from file and allow running the game with different layouts.  
7. Create a permanent high score list, using a file to store scores.  
8. Organize the code to adhere to the MVC pattern.  
9. Refactor the code by adding some design patterns to enhance maintainability.  

## Feature (Creative design)  
1. **Overall**  
- Button animation  
2. **Starting page**  
- Contains image and gif to make the interface.  
3. **Setting page**  
- There is a preview of the maze after change the color.  
- The game has three different level of difficulties, as three kinds of mazes are preloaded in the program.  
- The number of the ghosts can be chosen from three to five.  
- The selection operator has mouse On and Exit animation.  
4. **Game page**  
- Lives are represented as the number of pacman image.  
- Different difficulty has different calculation of the final score.  
- Different notification message between lose and win the game.
- The mouth of the pacman will change direction corresponding to the move direction.  
- When the pacman died near the starting point, the ghosts near to the starting point will be moved to the original point in case the pacman dies as soon as it re-alive.  
- The ghost have different speed for different difficulties.  
- The ghost can get out of the starting box quickly.  
- The ghost can track the rough position of pacman.  
5. **Score page**  
- If current mark is the highest, it will show that your mark is the highest.  
- If the ESC is pressed to restart the game page, the score page will close automatically.  

## Refactoring  
1. **Design pattern**  
+ Factory pattern  
   Use GhostFactory() to generate different ghosts by given the name of the ghost.  
   This type of design pattern comes under creational pattern, as this pattern provides one of the best ways to create an object, without exposing the creation logic to the client.  
+ Singleton pattern  
   Use Pacman() to create a new object, while this class contains only one object which can be accessed directly without need to instantiate the object of the class.  
   Involves a single class which is responsible to create a pacman while making sure that only pacman object gets created. This class provides a way to access its only object which do not need to instantiate the object of the class.  
+ State Pattern  
   A State interface is created defining an action, GameManager is a class which carries a State. Use GameManager and state objects to demonstrate change in GameManager behavior based on type of state it is in.  
   Treat each state as an individual class.

2. **Design principle**  
+ The moveUntilYouCant() method in ghost class is too long and has too many parameters, so it is refactored into four method. (WS04-S08)  
+ The getRandomBoolean() method in ghost class ends up doing nothing, so it is deleted. (WS04-S25)  
+ The checkCookieCoalition() method does multiple things/ too long, separate into four methods setPacmanLayout(), setCookieLayout(), checkCookieCoalition() and WinEnding(). The setPacmanLayout() method is also used in checkGhostCoalition(). (WS04-S05)  
+ In drawBoard() method exists a lot of duplicate code, new method CreateCookie() that encapsulates the code can then call it.(WS04-S05)  

## MVC Pattern
**Model, View, Controller is seperated into three packages**  
1. Model  
- Model represents an object. It can also have logic to update controller if its data changes.  
- Model contains objects that will be need to initialize the view. in some case, it can be listened to change immediatly on the binding object.  
2. View  
- View represents the visualization of the data that model contains.  
- View here is regarded as fxml file, it creates a basic UI whose style can be modified by css and controller.  
3. Controller  
- Controller acts on both model and view. It controls the data flow into model object and updates the view whenever data changes. It keeps view and model separate.  
- Controller contains methods including initialize and actions of different properties.  

## Javadoc  
**Javadoc is generated and located in the document folder.**  
1. The title of javadoc  
- Set to JavadocForPacman.  
2. The format of comments for javadoc  
- First line is the usage of the method.  
- Next few lines are detailed implementation of method.  
- @param is followed to explain the parameter contained.  
- @throws is followed to demonstrate exeception information.  
- @return is followed to show the return value.  
- @author is added to the main method to show the authority.  
3. jdk and VM option  
- jdk 1.8 is used for javadoc.  
- '-encoding UTF-8 -charset UTF-8' is used to have correct demonstration.  
4. Documentation for package and class  
- Documentation for package and class cannot be gnerated automatically by the eclipse.  
- Added in the html file directly to explain the usage of the package and class.  

## Video  
**This video contains two parts**  
1. Software in action and functionality.  
2. Refactoring efforts.  