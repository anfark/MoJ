package de.innohacks.MoJ.midi;

import javax.sound.midi.ShortMessage;

public class CCNote extends MidiNote {
    public CCNote(int volume) {
        super(ShortMessage.CONTROL_CHANGE, 0, volume);
    }
}
