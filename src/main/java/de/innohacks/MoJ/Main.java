package de.innohacks.MoJ;

import de.innohacks.MoJ.midi.MidiWriter;
import de.innohacks.MoJ.motion.MotionManager;
import de.innohacks.MoJ.motion.event.IEvent;

import javax.sound.midi.MidiUnavailableException;

/**
 * Created by roman on 30.09.17.
 */
public class Main {
    private static MotionManager managerHandy1;
    private static MotionManager managerHandy2;
    private static MidiWriter midiWriter1;
    private static MidiWriter midiWriter2;

    public static void main(String[] args) throws MidiUnavailableException {

        managerHandy1 = new MotionManager(args[0]);
        //managerHandy2 = new MotionManager(args[1]);
        midiWriter1 = new MidiWriter(0);
        midiWriter2 = new MidiWriter(1);
        managerHandy1.addListener((MotionManager man, IEvent event) -> {System.out.println(event.toString());});
        //managerHandy2.addListener((MotionManager man, IEvent event) -> {System.out.println(event.toString());});
        managerHandy1.addListener(midiWriter1);
        //managerHandy2.addListener(midiWriter2);
    }
}
