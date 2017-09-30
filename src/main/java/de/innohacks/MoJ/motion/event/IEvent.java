package de.innohacks.MoJ.motion.event;

import org.json.JSONObject;

/**
 * Created by roman on 30.09.17.
 */
public interface IEvent {
    IEvent MOUSE_TOGGLE = new IEvent() {
        @Override
        public String toString() {
            return "[MouseToggle]";
        }
    };
}
