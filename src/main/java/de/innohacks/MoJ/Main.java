package de.innohacks.MoJ;

import de.innohacks.MoJ.motion.MotionManager;
import de.innohacks.MoJ.motion.event.IEvent;
import org.zeromq.ZMQ;

import java.util.logging.Logger;


/**
 * Created by roman on 30.09.17.
 */
public class Main {

    private static MotionManager manager;

    public static void main(String[] args) {


        manager = new MotionManager(args[0]);
        manager.addListener((MotionManager man, IEvent event) -> System.out.println("Received event " + event));
        /*
        String address = args[0];

        ZMQ.Context context = ZMQ.context(1);

        //  Socket to talk to server
        System.out.println("Collecting updates from weather server");
        ZMQ.Socket subscriber = context.socket(ZMQ.SUB);
        subscriber.connect(address);

        //  Subscribe to zipcode, default is NYC, 10001
        String filter = (args.length > 0) ? args[0] : "10001 ";
        subscriber.subscribe("");

        //  Process 100 updates

        while (true) {
            //  Use trim to remove the tailing '0' character
            String string = subscriber.recvStr(0).trim();

            System.out.println(string);

        }



        /*
        ZContext ctx = new ZContext();
        ZSocket socket = new ZSocket(ZMQ.SUB);

        socket.connect(address);
        socket.subscribe("");


        while (true) {
            String s = socket.receiveStringUtf8();
            System.out.println(s);
        }*/
    }
}
