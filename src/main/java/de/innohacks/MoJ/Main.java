package de.innohacks.MoJ;

import de.innohacks.MoJ.midi.MidiWriter;
import de.innohacks.MoJ.motion.Location;
import de.innohacks.MoJ.motion.MotionManager;
import de.innohacks.MoJ.motion.event.Gesture;
import de.innohacks.MoJ.motion.event.GestureEvent;
import de.innohacks.MoJ.motion.event.IEvent;
import de.innohacks.MoJ.motion.event.MotionEvent;

import javax.sound.midi.MidiUnavailableException;
import java.util.Arrays;


/**
 * Created by roman on 30.09.17.
 */
public class Main {
    private static MotionManager manager;
    private static MidiWriter midiWriter;

    public static void main(String[] args) throws MidiUnavailableException {

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
                        loc.reset();
                    }
                }

                if (event == IEvent.MOUSE_TOGGLE) {
                    System.out.println("Reset");
                    loc.reset();
                    man.resetOrientation();
                }

                System.out.println("Received " + event);
            }

            char[] posX = new char[41];
            char[] posY = new char[41];

            Arrays.fill(posX, '-');
            Arrays.fill(posY, '-');

            int indexX = (int) Math.abs(Math.max(Math.min(((-loc.getX() + 20.0) / 40.0) * 40, 40),0));
            int indexY = (int) Math.abs(Math.max(Math.min(((-loc.getY() + 20.0) / 40.0) * 40, 40),0));
            posX[indexX] = 'X';
            posY[indexY] = 'Y';
            System.out.print('[' + new String(posX) + "] ");
            System.out.print('[' + new String(posY) + "] ");
            System.out.println(loc);

            int index = (int) Math.abs(Math.max(Math.min(((-loc.getX() + 20.0) / 40.0) * 80, 80),0));
            pos[index] = '|';
            System.out.println('[' + new String(pos) + "] " + loc);
        });
        manager.addListener(midiWriter);
    }
}
