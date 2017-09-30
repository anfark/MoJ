package de.innohacks.MoJ;

import de.innohacks.MoJ.midi.CCNote;
import de.innohacks.MoJ.midi.MidiNote;
import de.innohacks.MoJ.midi.MidiWriter;

import javax.sound.midi.*;

/**
 * Created by roman on 30.09.17.
 */
public class Main {

    public static void main(String[] args) throws MidiUnavailableException, InterruptedException {
        MidiWriter midiWriter = new MidiWriter();
        while (true) {
            for (int i = 0; i < 120; i++) {
                Thread.sleep(10);
                MidiNote note = new CCNote(i);
                midiWriter.writeMidi(note);
            }
        }
    }
}
