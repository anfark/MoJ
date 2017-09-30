package de.innohacks.MoJ.motion;

import de.innohacks.MoJ.motion.event.IEvent;

/**
 * Created by roman on 30.09.17.
 *
 * Listener-Interface to handle new events.
 */
public interface MotionListener {

    /**
     * This function will be called by the observed {@link MotionManager} when a new event arrives.
     *
     * @param manager the {@link MotionManager} that received the new event.
     * @param event the new event.
     */
    void handle(MotionManager manager, IEvent event);
}
