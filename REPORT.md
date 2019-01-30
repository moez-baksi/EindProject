# Report
This application is designed to help children learn and enjoy learning topography.

## Navigation
<img src="https://github.com/moez-baksi/EindProject/blob/master/doc/planOverview.png" />

## Technical details, with classes
<img src="https://github.com/moez-baksi/EindProject/blob/master/doc/planDetail.png" />

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

