package de.innohacks.MoJ;

import de.innohacks.MoJ.midi.MidiWriter;
import de.innohacks.MoJ.motion.Location;
import de.innohacks.MoJ.motion.MotionManager;
import de.innohacks.MoJ.motion.event.Gesture;
import de.innohacks.MoJ.motion.event.GestureEvent;
import de.innohacks.MoJ.motion.event.IEvent;
import de.innohacks.MoJ.motion.event.MotionEvent;
import de.innohacks.MoJ.tetris.Tetris;

import javax.sound.midi.MidiUnavailableException;
import java.util.Arrays;


/**
 * Created by roman on 30.09.17.
 */
public class Main {
    private static MotionManager manager;
    private static MidiWriter midiWriter;

    public static void main(String[] args) throws MidiUnavailableException {
        Tetris.startGame(args[0]);
        /*
        Location loc = new Location();

        manager = new MotionManager(args[0]);
        midiWriter = new MidiWriter();
        manager.addListener((MotionManager man, IEvent event) -> {
            if (event instanceof MotionEvent) {
                MotionEvent e = (MotionEvent)(event);
                loc.update(e);
            }
            else {

                if (event instanceof GestureEvent) {
                    GestureEvent e = (GestureEvent)(event);

                    if (e.getGesture() == Gesture.ROTATE_RL) {
                        System.out.println("Reset");
                        man.resetOrientation();
                    }
                }
                System.out.println("Received " + event);
            }

            char[] pos = new char[81];
            Arrays.fill(pos, '-');

            int index = (int) Math.abs(Math.max(Math.min(((-loc.getX() + 20.0) / 40.0) * 80, 80),0));
            pos[index] = '|';
            System.out.println('[' + new String(pos) + "] " + loc);
        });
        manager.addListener(midiWriter);
        */
    }
}
