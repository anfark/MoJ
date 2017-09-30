package de.innohacks.MoJ.motion;

import de.innohacks.MoJ.motion.event.IEvent;
import de.innohacks.MoJ.utils.LogUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;



/**
 * Created by roman on 30.09.17.
 */
public class MotionManager implements Runnable {
    private static final transient Logger log = LogUtils.createLogger(MotionManager.class, Level.FINE);

    private IMotionSource source;
    private List<MotionListener> observer;

    private volatile boolean running = false;


    /**
     * Creates a {@link MotionManager} which fetches events from a ZQM socket.
     * @param zqmSource
     */
    public MotionManager(String zqmSource) {
        this(new ZQMMotionSource(zqmSource));
    }


    /**
     * Creates a {@link MotionManager} which fetches events form the given source.
     * @param source
     */
    public MotionManager(IMotionSource source) {
        this.source = source;
        this.observer = new ArrayList<MotionListener>();

        Thread t = new Thread(this);
        t.start();
    }


    public void run() {
        if (isRunning()) {
            throw new RuntimeException("Failed to start MotionManager: MotionManager is already running!");
        }

        log.fine("MotionManager starts updates...");
        running = true;
        source.open();

        while (isRunning()) {
            final IEvent event = source.fetchUpdate();

            if (event != null) {
                //log.warning("Get event");
                observer.forEach( (listener) -> listener.handle(this, event));
            }
        }

        source.close();
        log.fine("MotionManager stops updates.");
    }

    /**
     * Adds a observer to the motion manager. The given {@link MotionListener} will be informed about every future motion.
     * @param listener
     */
    public void addListener(MotionListener listener) {
        observer.add(listener);
    }


    /**
     * Removes the given {@link MotionListener} from the {@link MotionManager}.
     * @param listener
     * @return <code>true</code> if the listener was removed from the {@link MotionManager}.
     */
    public boolean removeListener(MotionListener listener) {
        return observer.remove(listener);
    }


    /**
     * Checks if the {@link MotionManager} is currently running and fetches new updates.
     * @return
     */
    public boolean isRunning() {
        return running;
    }


    /**
     *  Stops this {@link MotionManager} and closes all open connections.
     */
    public void stop() {
        this.running = false;
    }
}
