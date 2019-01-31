# Report
This application is designed to help children learn and enjoy learning topography.


## Navigation
<img src="https://github.com/moez-baksi/EindProject/blob/master/doc/planOverview.png" />


## Technical details, with classes
<img src="https://github.com/moez-baksi/EindProject/blob/master/doc/planDetail.png" />

### Here is a short description of the activities and their functions. 
- MainActivity: This is the main activity that the user will see, with a welcome text, an image, and two buttons which redirects you to either the control activity or the selection activity.
  - onCreate()
  - goStart(), starts up the control activity.
  - goScores(), starts up the selection activity.
  - onBackPressed() is a function to disable navigation button.
  
- ControlActivity: This activity is to explain the user how to play the game and select a region, where the user wanna play its game.
  - onCreate(), this function also sets up the adapter for the spinner and its elements.
  - goStart(), this function checks what the user selected, and starts up the MapsActivity, giving via intent.putExtra the seleccted game mode.
 
- MapsActivity: This activity is the activity which is responsible for the whole game, it contains a map fragment, a stop button, a center button, a chronometer, a remaining count and a hint.
  - onCreate(), this function prepares map asynconyous, gets the mode from the intent, sets up the mode specific variables and prepares the hardcoded cities.
  - onMapReady(), this function is called when the Map is prepared. Here the mapstyles are edited, specific gestures are disabled and the camerea is moved. Thereby are the CameraMoveListener, CameraBounds and onGroundOverlayclick listeners set up. Afterwards the right amount of pokemon are requested from the pokemon API. Thereafter it also calls a function to start up the chronometer.
  - setOverlay(), this function sets up a overlay on the map, which is clickable and only visible if the user is zoomed enough on the map. Thereby is this function also responsible downloading the image (using picasso) provided by the pokemon API. The hint is also updated and the remaining count of the pokemon. 
  - If the user is finished, the ScoreActivity is started, providing the score using an itent.
  - setCenter(), this function sets up the camera, which will be centered based on which region the user selected.
  - goReturn(), this function stops the game.
  - onBackPressed(), this function is to disable navigation button.
  
- SelectionActivity: this activity lets the user select which results the user wants to see.
  - onCreate(), this function also sets up the adapter for the spinner and its elements.
  - goStart(), this function checks what the user selected in the spinner, and starts up the ScoreActivity, giving via intent.putExtra the selected game mode.
  
- ScoreActivity: this activity views the resuls of a specific game mode sorted and based on the selected mode.
  - onCreate(), this function gets the score and mode using intent, and puts it in the SQL database. Thereafter all the data entries are requested, and put in a table. 
  - setTableProperties(), this function sets up a textview with the same properties as all the others.
  - onBackPressed(), this function is to disable navigation button.


## Changes:
I will shortly describe the major changes I made in my plan.

### Gameplay Changes:
- Quickly realised there was a major game element missing. It was not a "fun" game, so I decided to change my plan. At first the primary goal was to catch all the pokemon, whereas the secondary goal was to learn some placed on the map. Instead making an application for only the fun, the plan changed to focus more on the Maps API rather than the Pokemon API. Hence the idea for making it a **topography learning application**. Instead of catching all the pokemon as fast possible, the primary goal changed to remember certain places, using the pokemon API to make it more attractive to the younger students. 
- To make it more stimulating, i decided to change the way i score the students. Instead the of finding as much places in a given time, I instead **measure the time passed** finding all the places, which serves as a stimulance. 
- Due the time, it was possible to add another two regions, instead of only the the provinces. Due a random selection process, the regions Northern and Western Europe were added.


### Design Changes:

#### Maps activity:
- Made a center button, which you can use to zoom out so you would not lose time zooming out, which does not stimulate the learning process
- Made a quit button, so all the data is thrown away instead of cached.
- Due those two buttons, a gap was created, so decided to put the chronometer and the remaining count there.
- Replaced the tip to the bottom to balance the map more in the middle.
- Decided to create two different tips (city name or country name), which stimulates the learning process.
- Decided to keep the labels of the city names, but less visible, so if you are tuck you can always waste time by trying to read it. 
- Chose to leave the colors of the terrain present on the map, which usually lacks on topographic maps. This gives less the feeling that you are learning something, which stimulates children.
  
Original Plan              | Result
:-------------------------:|:-------------------------:
<img src="https://github.com/moez-baksi/EindProject/blob/master/doc/old%20design.png" width="300" height="450" /> |<img src="https://github.com/moez-baksi/EindProject/blob/master/doc/game1.png" width="300" height="450" /> 


#### Selection / Control Activity:
- I made a selection and a control activity in order to let the user select a region using a spinner. The extra regions was not included in the original plan, but  was added when there was some time left.
- The purpose of the selection activity is purely to select of which region the results should be displayed. 
- The purpose of the control activity is to select in what region you want to play. Thereby, there is also a [GIF](https://github.com/moez-baksi/EindProject/blob/master/doc/Tutoriall.gif) which displays how the game should be played.
  
Control Activity           | Selection Activity
:-------------------------:|:-------------------------:
<img src="https://github.com/moez-baksi/EindProject/blob/master/doc/selection.png" width="300" height="450" /> |<img src="https://github.com/moez-baksi/EindProject/blob/master/doc/selection2.png" width="300" height="450" /> 

