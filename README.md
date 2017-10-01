# MoJ
MoJ stands for Motion DJ and the goal of the project was it, to transform Motion Gestures into Midi Signals to be able to use them in any Midi compatible Software, like Traktor wich is a DJ Software. We also used the motion sensors to control a Tetris Game. This is a project of Simon Heiss and Roman Schulte during the Inno{Hacks}2017. 

# Used Components
- Motion Sensor of Kinmenic (http://kinemic.com)
- Android App of Kinmenic for publishing events on an ZMQ Port
- Java Midi Library https://docs.oracle.com/javase/tutorial/sound/accessing-MIDI.html
- Traktor from Native Instruments https://www.native-instruments.com/de/products/traktor/
- OSX Virtual Midi Ports

# General Usage
The Motion Sensor Braclet communicates with an Android App and sends recognized gestures to an ZMQ port, wich we subscribe with a Java Programm. The gestures are in a Json Format, so we parse them and give them into an Converter, that creates our Domain Objects, wich represent MidiSignals. We implemented Note On and Continuous Change, to be able to use the most important features of Midi Compatible Softwares. Those Midi Objects are being send to a Midi Writer Class, that handles the Midi Connection to the Traktor Software. To be able to pass Midi Signals from Java to Traktor, we needed to add a virtual Midi cable, wich can be done in the Midi/Audio Settings under OSX. The virtual Cable gets used by Traktor to receive the Midi Events and then they can be configured to control elements in Traktor. Note On Signals are used to toggle or trigger Buttons and Continuous Change Events are used to control Faders or Potentiometers. Generally this software can be used with all midi compatible software and also will be able to communicate with other Midi Hardware, if a Midi Hardware Interface is connected to the Computer.

# Repository structure
- branch "simon" contains the midi compatible software
- branch "tetris" contains the software controlling a simple tetris game
