package de.innohacks.MoJ.motion.event;


/**
 * Created by roman on 30.09.17.
 */
public class GestureEvent implements IEvent {
    private Gesture gesture;

    public GestureEvent(Gesture gesture) {
        this.gesture = gesture;
    }

    public Gesture getGesture() {
        return gesture;
    }

    @Override
    public String toString() {
        return "[GestureEvent gesture=" + getGesture().toString() + "]";
    }
}
