package de.innohacks.MoJ.motion.event;


/**
 * Created by roman on 30.09.17.
 *
 * A {@link Gesture} that can be recognized by the Kinemic Bracelet.
 */
public enum Gesture {
    SWIPE_L("Swipe L"),
    SWIPE_R("Swipe R"),
    ROTATE_LR("Rotate LR"),
    ROTATE_RL("Rotate RL"),
    CIRCLE_L("Circle L"),
    CIRCLE_R("Circle R"),
    EARTOUCH_L("Eartouch L"),
    EARTOUCH_R("Eartouch R");

    final String name;

    Gesture(String name) {
        this.name = name;
    }
}
