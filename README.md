# MoJ
MoJ stands for Motion DJ and the goal of the project was it, to transform motion gestures into Midi signals to be able to use them in any Midi compatible software, like Traktor wich is a DJ software. We also used the motion sensors to control a Tetris Game, to show that the component can easily be used in other contexts. This is a project of Simon Heiss and Roman Schulte during the Inno{Hacks}2017. 

# Used Components
- Motion Sensor of Kinmenic (http://kinemic.com)
- Android App of Kinmenic for publishing events on an ZMQ Port
- Java Midi Library https://docs.oracle.com/javase/tutorial/sound/accessing-MIDI.html
- Traktor from Native Instruments https://www.native-instruments.com/de/products/traktor/
- OSX Virtual Midi Ports

# General Usage
The motion sensor braclet communicates with an Android app and sends recognized gestures to an ZMQ port, wich we subscribe with a Java programm. The .jar needs at least one adress string as an argument to know where the app socket ist located. The software can handle multiple devices, if you provide multiple adresses to the jar. The gestures recieved from the App are in a Json format, so we parse them and give them into an converter, that creates our domain objects, wich represent Midi signals. We implemented "Note On/Off" and "Continuous Change", to be able to use the most important features of Midi Compatible Softwares. Those Midi Objects are being send to a Midi Writer Class, that handles the Midi connection to the Traktor software. To be able to pass Midi signals from Java to Traktor, we needed to add a virtual Midi cable, wich can be done in the Midi/Audio settings under OSX. The virtual cable gets used by Traktor to receive the Midi events and then they can be configured to control elements in Traktor. "Note On/Off" signals are used to toggle or trigger buttons and "Continuous Change" events are used to control faders or potentiometers. Generally this software can be used with every midi compatible software and also will be able to communicate with other Midi hardware, if a Midi hardware interface is connected to the computer.

# Known Issue
The motion sensor used only provides delta move values. Therefore it is very difficult to provide a correct x/y position, as the delta is not a very good aproximation of the current location. Therefore we implemented a "reset" gesture, to set the 0,0 point back to the current location of the motion sensor in case the location gets out of hand. Using a different motion sensor with absolute x/y position would fix this issues.

# Repository structure
- branch "simon" contains the midi compatible software
- branch "tetris" contains the software controlling a simple tetris game
