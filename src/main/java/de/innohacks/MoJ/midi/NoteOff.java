package de.innohacks.MoJ.midi;

import javax.sound.midi.ShortMessage;

public class NoteOff extends MidiNote {
    public NoteOff(int value) {
        super(ShortMessage.NOTE_OFF, value, 127);
    }
}
