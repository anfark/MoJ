package de.innohacks.MoJ.midi;

import javax.sound.midi.ShortMessage;

public class NoteOn extends MidiNote {
    public NoteOn(int pitch) {
        super(ShortMessage.NOTE_ON, pitch, 127);
    }
}
