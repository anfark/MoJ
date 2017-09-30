package de.innohacks.MoJ.midi;

import javax.sound.midi.ShortMessage;

public class CCNote extends MidiNote {
    public CCNote(int pitch) {
        super(ShortMessage.CONTROL_CHANGE, pitch);
    }
}
