package de.innohacks.MoJ.midi;

import javax.sound.midi.ShortMessage;

public class CCNote extends MidiNote {
    public CCNote(int channel, int volume) {
        super(ShortMessage.CONTROL_CHANGE, channel, volume);
    }
}
