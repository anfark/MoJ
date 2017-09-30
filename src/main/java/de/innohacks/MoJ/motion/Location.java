package de.innohacks.MoJ.motion;

import de.innohacks.MoJ.motion.event.MotionEvent;

/**
 * Created by roman on 30.09.17.
 */
public class Location {
    private double x = 0;
    private double y = 0;

    private static final double threshold = 0.05;

    public synchronized void update(MotionEvent event) {

        update(event.getDx(), event.getDy());
    }

    public synchronized void update(double dx, double dy) {
        if (Math.abs(dx) > threshold) {
            x += dx;
        }

        if (Math.abs(dy) > threshold) {
            y += dy;
        }
    }

    public synchronized double getX() {
        return x;
    }

    public synchronized double getY() {
        return y;
    }

    public synchronized void reset() {
        x = 0;
        y = 0;
    }

    @Override
    public synchronized String toString() {
        return String.format("[Location x=%.2f, y=%.2f]",
                Math.round(getX() * 100.0) / 100.0,
                Math.round(getY() * 100.0) / 100.0);
    }
}
