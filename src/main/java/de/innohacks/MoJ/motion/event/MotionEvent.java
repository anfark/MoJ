package de.innohacks.MoJ.motion.event;

/**
 * Created by roman on 30.09.17.
 */
public final class MotionEvent implements IEvent {
    private final double dx;
    private final double dy;
    private final boolean down;

    MotionEvent(double dx, double dy, boolean down) {
        super();
        this.dx = dx;
        this.dy = dy;
        this.down = down;
    }

    public double getDx() {
        return dx;
    }

    public double getDy() {
        return dy;
    }

    public boolean isDown() {
        return down;
    }

    @Override
    public String toString() {
        return "[MotionEvent dx=" + getDx() + " dy=" + getDy() + " isDown=" + isDown() + "]";
    }
}
