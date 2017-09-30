package de.innohacks.MoJ.motion;

import de.innohacks.MoJ.motion.event.EventParser;
import de.innohacks.MoJ.motion.event.IEvent;
import org.zeromq.ZMQ;

import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by roman on 30.09.17.
 */
public class ZQMMotionSource implements IMotionSource {
    private static transient final Logger logger = Logger.getLogger(ZQMMotionSource.class.getSimpleName());

    private final ZMQ.Socket socket;
    private final String address;
    private final EventParser parser = new EventParser();

    private boolean open = false;


    public ZQMMotionSource(String address) {
        this.address = address;

        ZMQ.Context context = ZMQ.context(1);
        socket = context.socket(ZMQ.SUB);

        logger.setLevel(Level.ALL);
        ConsoleHandler handler = new ConsoleHandler();
        // PUBLISH this level
        handler.setLevel(Level.FINER);
        logger.addHandler(handler);
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
        //logger.fine("Has more? " + socket.hasReceiveMore());
        //if (open && socket.hasReceiveMore()) {
            final String str = socket.recvStr();
            return parser.parse(str);
        //}
        //else return null;
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
