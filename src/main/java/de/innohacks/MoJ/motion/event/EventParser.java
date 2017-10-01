package de.innohacks.MoJ.motion.event;

import de.innohacks.MoJ.utils.LogUtils;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by roman on 30.09.17.
 *
 * Parser to create {@link IEvent} instances from json strings.
 */
public class EventParser {
    private final static transient Logger log = LogUtils.createLogger(EventParser.class, Level.FINE);

    private final static String KEY_TYPE = "type";
    private final static String KEY_PARAMETERS = "parameters";
    private final static String KEY_GESTURE_NAME = "name";
    private final static String KEY_DX = "dx";
    private final static String KEY_DY = "dy";
    private final static String KEY_DOWN = "down";

    private final static String TYPE_GESTURE = "Gesture";
    private final static String TYPE_MOTION = "MouseEvent";
    private final static String TYPE_MOUSE_TOGGLE = "MouseToggle";


    /**
     * Tries to parse a {@link IEvent} out of a json string.
     * @param str the string which contains the json wiht the event information.
     * @return a {@link IEvent} instance or null if something went wrong.
     */
    public IEvent parse(String str) {

        // Parse JSON
        JSONObject obj;

        try {
            obj = new JSONObject(str);
        }
        catch (Exception e) {
            e.printStackTrace();
            log.warning("Failed to parse event: The given string was not a json.");
            return null;
        }

        //log.warning("Got JSON " + obj.keySet());
        if (!(obj.keySet().contains(KEY_PARAMETERS) && obj.keySet().contains(KEY_TYPE))) {
            log.warning("Failed to parse event: Invalid json " + str);
            return null;
        }



        // Search type and parameters
        final String type = (String)(obj.get(KEY_TYPE));

        // Catch MouseToggle events, because they don't have a params object.
        if (type.equals(TYPE_MOUSE_TOGGLE)) {
            return new MouseToggle();
        }



        final JSONObject params = (JSONObject)(obj.get(KEY_PARAMETERS));

        if (type == null || params == null) {
            log.warning("Failed to parse event: Type or parameters missing in " + str);
            return null;
        }

        // Create Event
        switch (type) {
            case TYPE_GESTURE:
                // read name
                final String gestureName = params.getString(KEY_GESTURE_NAME);

                if (gestureName == null) {
                    log.warning("Failed to parse gesture event: Gesture name is missing");
                    return null;
                }

                final Optional<Gesture> gesture = Gesture.getGesture(gestureName);

                if (gesture.isPresent()) {
                    return new GestureEvent(gesture.get());
                }
                else {
                    log.warning("Failed to parse gesture event: Unknown gesture " + gestureName);
                    return null;
                }


            case TYPE_MOTION:
                // Parse MotionEvent
                String[] keys = {KEY_DX, KEY_DY, KEY_DOWN};
                if (params.keySet().containsAll(Arrays.asList(keys))) {
                    try {
                        final double dx = (Double)(params.get(KEY_DX));
                        final double dy = (Double)(params.get(KEY_DY));
                        final boolean down = (Boolean) (params.get(KEY_DOWN));

                        return new MotionEvent(dx, dy, down);
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                        log.fine("Failed to parse motion event: Some parameters don't have the correct type in " + params);
                    }
                }
                else {
                    log.warning("Failed to parse motion event: Missing some parameters in " + params);
                    return null;
                }
            case TYPE_MOUSE_TOGGLE:
                return new MouseToggle();

            case "Heartbeat":
                log.finest("Received heartbeat");
                return null;
            default:
                log.warning("Received unknown event type: " + type);
                return null;
        }
    }

    private Optional<Double> parseDouble(JSONObject params, String key) {
        try {
            double d = (Double)(params.get(key));//Double.parseDouble(params.getString(key));
            return Optional.of(d);
        }
        catch (Exception e) {
            return Optional.empty();
        }
    }

}
