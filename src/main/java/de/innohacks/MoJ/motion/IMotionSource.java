package de.innohacks.MoJ.motion;

import de.innohacks.MoJ.motion.event.IEvent;
import org.json.JSONObject;

/**
 * Created by roman on 30.09.17.
 */
public interface IMotionSource {
    /**
     * Opens the source and makes it ready to fetch updates.
     */
    void open();

    /**
     * Fetches a new update.
     * @return a new {@link IEvent} or null if something went wrong.
     */
    IEvent fetchUpdate();

    /**
     * Closes the source.
     */
    void close();

    /**
     * Checks if the source is open.
     * @return
     */
    boolean isOpen();
}
