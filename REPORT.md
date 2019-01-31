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
  - goStart(), this function checks what the user selected, and starts up the MapsActivity, giving via intent.putExtra the selected game mode.
 
 
- MapsActivity: This activity is the activity which is responsible for the whole game, it contains a map fragment, a stop button, a center button, a chronometer, a remaining count and a hint.
  - onCreate(), this function prepares map asynchronous, gets the mode from the intent, sets up the mode specific variables and prepares the hardcoded cities.
  - onMapReady(), this function is called when the Map is prepared. Here the map styles are edited, specific gestures are disabled, and the camera is moved. Thereby are the CameraMoveListener, CameraBounds and onGroundOverlayclick listeners set up. Afterwards the right amount of Pokémon is requested from the Pokémon API. Thereafter it also calls a function to start up the chronometer.
  - setOverlay(), this function sets up an overlay on the map, which is clickable and only visible if the user is zoomed enough on the map. Thereby is this function also responsible downloading the image (using Picasso) provided by the Pokémon API. The hint is also updated and the remaining count of the Pokémon. 
  - If the user is finished, the ScoreActivity is started, providing the score using an intent.
  - setCenter(), this function sets up the camera, which will be centered based on which region the user selected.
  - goReturn(), this function stops the game.
  - onBackPressed(), this function is to disable navigation button.
  
  
- SelectionActivity: this activity lets the user select which results the user wants to see.
  - onCreate(), this function also sets up the adapter for the spinner and its elements.
  - goStart(), this function checks what the user selected in the spinner, and starts up the ScoreActivity, giving via intent.putExtra the selected game mode.
  
  
- ScoreActivity: this activity views the results of a specific game mode sorted and based on the selected mode.
  - onCreate(), this function gets the score and mode using intent, and puts it in the SQL database. Thereafter all the data entries are requested and put in a table. 
  - setTableProperties(), this function sets up a TextView with the same properties as all the others.
  - onBackPressed(), this function is to disable navigation button.
  

- Other classes:
  - City, this class is used to create 'City' variables.
  - ListCities, this class contains the hardcoded cities.
  - ModeSpecificVar, this class is used to hold mode specific variables, which are created in the mapsactivity.
  - PokeRequest, to handle all the web, volley and callback stuff with the Pokémon API.
  - Pokémon, this class is used to create 'Pokémon' variables.
  - Score, this class is used to create 'Score' variables.
  - ScoreDatabase, this database class handles all the SQL stuff, such as insert() and selectAll()


## Changes:
I will shortly describe the major changes I made in my plan.

### Gameplay Changes:
- Quickly realized there was a major game element missing. It was not a "fun" game, so I decided to change my plan. At first the primary goal was to catch all the Pokémon, whereas the secondary goal was to learn some placed on the map. Instead making an application for only the fun, the plan changed to focus more on the Maps API rather than the Pokémon API. Hence the idea for making it a **topography learning application**. Instead of catching all the Pokémon as fast possible, the primary goal changed to remember certain places, using the Pokémon API to make it more attractive to the younger students. 
- To make it more stimulating, I decided to change the way I score the students. Instead the of finding as much places in a predefined time, I instead **measure the time passed** finding all the places, which serves as a stimulant. 
- Due the time, it was possible to add another two regions, instead of only the provinces. Due a random selection process, the regions Northern and Western Europe were added.


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
- I selected and a control activity in order to let the user select a region using a spinner. The extra regions were not included in the original plan but was added when there was some time left.
- The purpose of the selection activity is purely to select of which region the results should be displayed. 
- The purpose of the control activity is to select in what region you want to play. Thereby, there is also a [GIF](https://github.com/moez-baksi/EindProject/blob/master/doc/Tutoriall.gif) which displays how the game should be played.
  
Control Activity           | Selection Activity
:-------------------------:|:-------------------------:
<img src="https://github.com/moez-baksi/EindProject/blob/master/doc/selection.png" width="300" height="450" /> |<img src="https://github.com/moez-baksi/EindProject/blob/master/doc/selection2.png" width="300" height="450" /> 


## Argument + Challenges
The first challenge I faced when I started to work on this project, was the lack of a fun element. The original idea was to let the user catch as many Pokémon as possible. But to change the public of my application, I decided to change that into a more map focused game. Instead of catching all the Pokémon, the primary goal was to get familiar with the different places on google maps. Hence the idea of Pokémon topography was born. The goal of the whole application was changes; however, the implementation remained the same.

When I started to implement the idea, I stumbled over the map. The API I chose, had so many options which left me in the dark. I had no idea where to start and eventually blacked out. The day after I decided to continue with the default settings and look afterward to all the options. I am not one hundred percent happy with how the map looks now. For now, I made the labels white, but they were present. When I tried to remove it, the dots (which indicated cities) would also disappear. In ideal situation the dots would be visible, but the labels would have been removed. 

A lot of problems were also caused by the Pokémon API. Initially I thought you would receive an image with the API, but I received an image link. In order to display an image, I had to download it and convert it to bitmap. There is no direct way to do this in android, so I wasted too much time trying to combine standard functions in asynchronous classes. I gave up after a couple of DAYS, and used Picasso instead, which works like a charm. 

The score activity is also not what I wanted it to be. I feel like the whole screen lacks something, an image for example. But I could not find anything that suited. I chose the way it looks now, because it is simple but yet effective. 

If I had more time, I think I would have implemented a lot more regions. Also an option to combine multiple regions would have been awesome. Thereby, I also would have wanted an online database for the scores, so you can not lose your progress if you lost your phone. Also a fun idea would have been an options menu, where you could select if you wanted labels, or select which pokemon you wanted to play with. But After all i’m really happy with the application I made.

