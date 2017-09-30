package de.innohacks.MoJ;

import de.innohacks.MoJ.motion.MotionManager;
import de.innohacks.MoJ.motion.event.IEvent;
import org.zeromq.ZMQ;


/**
 * Created by roman on 30.09.17.
 */
public class Main {
    private static MotionManager manager;

    public static void main(String[] args) {

        manager = new MotionManager(args[0]);
        manager.addListener((MotionManager man, IEvent event) -> {System.out.println("Received " + event);});

        /*
        ZMQ.Context context = ZMQ.context(1);

        //  Socket to talk to server
        System.out.println("Collecting updates from " + args[0]);
        ZMQ.Socket subscriber = context.socket(ZMQ.SUB);
        subscriber.connect(args[0].trim());

        //  Subscribe to zipcode, default is NYC, 10001
        subscriber.subscribe("");


        //  Process 100 updates
        int update_nbr;
        long total_temp = 0;
        for (update_nbr = 0; update_nbr < 100; update_nbr++) {
            //  Use trim to remove the tailing '0' character
            String string = subscriber.recvStr(0).trim();
            System.out.println(string);

        }

        subscriber.close();
        context.term();
        */
    }
}
