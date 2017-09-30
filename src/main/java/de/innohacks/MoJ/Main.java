package de.innohacks.MoJ;

import de.innohacks.MoJ.motion.Location;
import de.innohacks.MoJ.motion.MotionManager;
import de.innohacks.MoJ.motion.event.IEvent;
import de.innohacks.MoJ.motion.event.MotionEvent;
import org.zeromq.ZMQ;

import java.util.Arrays;


/**
 * Created by roman on 30.09.17.
 */
public class Main {
    private static MotionManager manager;

    public static void main(String[] args) {

        Location loc = new Location();

        manager = new MotionManager(args[0]);
        manager.addListener((MotionManager man, IEvent event) -> {
            if (event instanceof MotionEvent) {
                MotionEvent e = (MotionEvent)(event);
                loc.update(e);
                //System.out.println("" + loc);
            }
            else {
                //System.out.println("Received " + event);
                loc.reset();
            }

            char[] pos = new char[81];
            Arrays.fill(pos, '-');

            int index = (int) Math.abs(Math.max(Math.min(((-loc.getX() + 20.0) / 40.0) * 80, 80),0));
            pos[index] = '|';
            System.out.println('[' + new String(pos) + ']');
        });

    }
}
