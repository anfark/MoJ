package de.innohacks.MoJ.motion;

import de.innohacks.MoJ.motion.event.EventParser;
import de.innohacks.MoJ.motion.event.IEvent;
import de.innohacks.MoJ.utils.LogUtils;
import org.zeromq.ZMQ;

import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by roman on 30.09.17.
 */
public class ZQMMotionSource implements IMotionSource {
    private static transient final Logger logger = LogUtils.createLogger(ZQMMotionSource.class, Level.FINE);//Logger.getLogger(ZQMMotionSource.class.getSimpleName());

    private final ZMQ.Socket socket;
    private final String address;
    private final EventParser parser = new EventParser();

    private boolean open = false;


    public ZQMMotionSource(String address) {
        this.address = address;

        ZMQ.Context context = ZMQ.context(1);
        socket = context.socket(ZMQ.SUB);
    }

    @Override
    public synchronized void open() {
        if (isOpen()) {
            return;
        }

        logger.fine("Open ZQM socket to " + address);

        open = true;
        socket.connect(address);
        socket.subscribe("");
    }

    @Override
    public IEvent fetchUpdate() {

        final String str = socket.recvStr();
        return parser.parse(str);
    }

    @Override
    public synchronized void close() {
        if (!isOpen()) {
            return;
        }

        logger.fine("Close ZQM socket");
        open = false;
        socket.unsubscribe("");
        socket.disconnect(address);
    }

    @Override
    public boolean isOpen() {
        return open;
    }
}
