package de.innohacks.MoJ;

import de.innohacks.MoJ.midi.MidiWriter;
import de.innohacks.MoJ.motion.MotionManager;
import de.innohacks.MoJ.motion.event.IEvent;

import javax.sound.midi.MidiUnavailableException;

/**
 * Created by roman on 30.09.17.
 */
public class Main {
    private static MotionManager manager;
    private static MidiWriter midiWriter;

    public static void main(String[] args) throws MidiUnavailableException {

        manager = new MotionManager(args[0]);
        midiWriter = new MidiWriter();
        manager.addListener((MotionManager man, IEvent event) -> {System.out.println(event.toString());});
        manager.addListener(midiWriter);
    }
}
