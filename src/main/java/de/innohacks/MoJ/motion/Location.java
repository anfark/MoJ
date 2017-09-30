package de.innohacks.MoJ.motion;

import de.innohacks.MoJ.motion.event.MotionEvent;

/**
 * Created by roman on 30.09.17.
 */
public class Location {
    private double x = 0;
    private double y = 0;

    private double threshold = 0.05;

    public void update(MotionEvent event) {
        update(event.getDx(), event.getDy());
    }

    public void update(double dx, double dy) {
        if (Math.abs(dx) > threshold) {
            x += dx;
        }

        if (Math.abs(dy) > threshold) {
            y += dy;
        }
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    @Override
    public String toString() {
        return "[Location x="+ getX() + " y=" + getY() + "]";
    }
}
