package de.innohacks.MoJ.midi;

import javax.sound.midi.ShortMessage;

public class MidiNoteOn extends MidiNote {
    public MidiNoteOn(int pitch) {
        super(ShortMessage.NOTE_ON, pitch);
    }
}
