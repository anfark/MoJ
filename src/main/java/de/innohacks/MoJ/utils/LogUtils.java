package de.innohacks.MoJ.utils;

import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by roman on 30.09.17.
 */
public class LogUtils {

    /**
     * Creates a new {@link Logger} thta prints the messages for the given {@link Level} to the console.
     * @param clazz
     * @param level
     * @return
     */
    public static Logger createLogger(Class<?> clazz, Level level) {
        Logger l = Logger.getLogger(clazz.getSimpleName());

        l.setLevel(level);

        ConsoleHandler handler = new ConsoleHandler();
        // PUBLISH this level
        handler.setLevel(level);
        l.addHandler(handler);

        return l;
    }
}
