package de.innohacks.MoJ.tetris;

import de.innohacks.MoJ.motion.Location;
import de.innohacks.MoJ.motion.MotionListener;
import de.innohacks.MoJ.motion.MotionManager;
import de.innohacks.MoJ.motion.event.Gesture;
import de.innohacks.MoJ.motion.event.GestureEvent;
import de.innohacks.MoJ.motion.event.IEvent;
import de.innohacks.MoJ.motion.event.MotionEvent;
import de.innohacks.MoJ.tetris.gui.WorldRenderer;
import de.innohacks.MoJ.tetris.model.World;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Created by roman on 01.10.17.
 */
public class Tetris extends JFrame {
    private static Location loc = new Location();
    private JPanel topPanel;
    private WorldRenderer renderer;
    private Timer timer;
    MotionManager manager;

    public Tetris(String address) {
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        topPanel = new JPanel();
        add(topPanel, BorderLayout.NORTH);

        World world = new World(20, 20);

        //world.update();
        //world.update();
        //world.update();

        renderer = new WorldRenderer(world);
        add(renderer, BorderLayout.CENTER);

        setTitle("MoJ - Tetris");
        setFocusable(true);

        timer = new Timer(400, (e -> {
            world.update();
            renderer.repaint();

            System.out.println(Tetris.loc);
            Tetris.loc.reset();
        }));
        timer.setRepeats(true);
        timer.start();

        manager = new MotionManager(address);
        manager.addListener(new MotionHandler(world));
    }

    public static void startGame(String address) {
        SwingUtilities.invokeLater(() -> {
            Tetris game = new Tetris(address);

            game.setSize(new Dimension(500, 500));
            game.setLocationRelativeTo(null);

            game.setVisible(true);
        });
    }

    private class MotionHandler implements MotionListener {
        private long lastFastMotionMillis = 0;
        private World world;

        public MotionHandler(World world) {
            this.world = world;
        }

        @Override
        public void handle(MotionManager manager, IEvent event) {
            if (event instanceof GestureEvent) {
                GestureEvent ge = (GestureEvent)(event);

                System.out.println("Gesture " + ge);
                switch (ge.getGesture()) {
                    case SWIPE_L:
                        world.tryToMove(-1, 0);

                        break;
                    case SWIPE_R:
                        world.tryToMove(1, 0);
                        break;
                    case ROTATE_LR:
                        world.tryToRotate(true);
                        break;
                    case ROTATE_RL:
                        world.tryToRotate(false);
                        break;
                    case CIRCLE_L:
                        world.reset();
                        break;
                    case CIRCLE_R:
                        world.setPause(!world.isPaused());
                        break;
                    default:
                        break;
                }

            }
            else if (event instanceof MotionEvent) {
                MotionEvent me = (MotionEvent) (event);
                loc.update(me);
                if (System.currentTimeMillis() - lastFastMotionMillis > 500) {
                    if (me.getDx() > 3.5) {
                        lastFastMotionMillis = System.currentTimeMillis();
                        System.out.println("FAST L");
                        world.tryToMove(1, 0);
                    }
                    else if (me.getDx() < -3.5) {
                        lastFastMotionMillis = System.currentTimeMillis();
                        System.out.println("FAST R");
                        world.tryToMove(-1, 0);
                    }
                }

            }
        }
    }
}
