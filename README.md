# RacingMascots

This program contains three classes: RacingMohos, Mascots and Picture (Picture class already supplied).

The __RacingMohos__ Class uses multi-threading, handles all of the user mouse events, and contains 
knowledge of the DrawingCanvas. The class displays the racing lines as well as the visible 
images of the four mascots at the start of the program. On a user's mouse click, this class 
starts the animation by invoking start().

The __Mascot class__ is responsible for defining a blueprint for a racing mascot.
Along with the constructor for Mascot, this class contains a method that returns a random speed,
which is a characteristic of a Mascot object. The run method defines how the animation will run once
the start method is invoked (Start() invokes run).


__@author Sabirah Shuaybi__
 
__@version 11/01/16__
