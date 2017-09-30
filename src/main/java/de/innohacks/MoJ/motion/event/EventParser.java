package de.innohacks.MoJ.motion.event;

import org.json.JSONObject;

import java.util.Optional;
import java.util.logging.Logger;

/**
 * Created by roman on 30.09.17.
 *
 * Parser to create {@link IEvent} instances from json strings.
 */
public class EventParser {
    private final static transient Logger log = Logger.getLogger(EventParser.class.getSimpleName());

    private final static String KEY_TYPE = "type";
    private final static String KEY_PARAMETERS = "parameters";
    private final static String KEY_GESTURE_NAME = "name";
    private final static String KEY_DX = "dx";
    private final static String KEY_DY = "dy";
    private final static String KEY_DOWN = "down";

    private final static String TYPE_GESTURE = "Gesture";
    private final static String TYPE_MOTION = "MouseEvent";


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

        // Search type and parameters
        final String type = (String)(obj.get(KEY_TYPE));
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

                // find gesture
                final Gesture gesture = Gesture.valueOf(gestureName);

                if (gesture == null) {
                    log.warning("Failed to parse gesture event: Unknown gesture name " + gestureName);
                    return null;
                }

                return new GestureEvent(gesture);

            case TYPE_MOTION:
                // Parse MotionEvent
                final Optional<Double> dx = parseDouble(params, KEY_DX);
                final Optional<Double> dy = parseDouble(params, KEY_DY);
                final String downStr = params.getString(KEY_DOWN);

                if (dx.isPresent() && dy.isPresent() && downStr != null) {
                    return new MotionEvent(dx.get(), dy.get(), Boolean.parseBoolean(downStr));
                }
                else {
                    log.warning("Failed to parse motion event: Missing some parameters in " + params.toString());
                    return null;
                }

            case "Heartbeat":
                log.fine("Received heartbeat");
                return null;
            default:
                log.warning("Received unknown event type: " + type);
                return null;
        }
    }

    private Optional<Double> parseDouble(JSONObject params, String key) {
        try {
            double d = Double.parseDouble(params.getString(key));
            return Optional.of(d);
        }
        catch (Exception e) {
            return Optional.empty();
        }
    }

}
