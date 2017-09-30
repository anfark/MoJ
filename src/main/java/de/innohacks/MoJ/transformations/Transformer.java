package de.innohacks.MoJ.transformations;

import de.innohacks.MoJ.midi.CCNote;
import de.innohacks.MoJ.midi.MidiNote;
import de.innohacks.MoJ.midi.NoteOn;
import de.innohacks.MoJ.motion.event.GestureEvent;
import de.innohacks.MoJ.motion.event.IEvent;
import de.innohacks.MoJ.motion.event.MotionEvent;

import java.util.ArrayList;
import java.util.List;

public class Transformer {

    List<Tuple<MidiNote, MidiNote>> noteOnFlyweights;
    double x;
    double y;

    public Transformer() {
        this.noteOnFlyweights = new ArrayList<>();
        noteOnFlyweights.add(new Tuple<>(new NoteOn(0), null));
        noteOnFlyweights.add(new Tuple<>(new NoteOn(1), null));
        noteOnFlyweights.add(new Tuple<>(new NoteOn(2), null));
        noteOnFlyweights.add(new Tuple<>(new NoteOn(3), null));
        noteOnFlyweights.add(new Tuple<>(new NoteOn(4), null));
        noteOnFlyweights.add(new Tuple<>(new NoteOn(5), null));
        noteOnFlyweights.add(new Tuple<>(new NoteOn(6), null));
        noteOnFlyweights.add(new Tuple<>(new NoteOn(7), null));
        this.x = 0;
        this.y = 0;
    }

    public Tuple<MidiNote, MidiNote>  transform(IEvent motionEvent) {
        if (motionEvent instanceof MotionEvent) {
            return parseToCCNote((MotionEvent) motionEvent);
        }
        else if (motionEvent instanceof GestureEvent) {
            return parseToNotOn((GestureEvent) motionEvent);
        }
        else {
            return new Tuple<>(null, null);
        }
    }

    private Tuple<MidiNote, MidiNote> parseToNotOn(GestureEvent motionEvent) {
        switch (motionEvent.getGesture()) {
            case SWIPE_L: return this.noteOnFlyweights.get(0);
            case SWIPE_R: return this.noteOnFlyweights.get(1);
            case CIRCLE_L: return this.noteOnFlyweights.get(2);
            case CIRCLE_R: return this.noteOnFlyweights.get(3);
            case ROTATE_LR: return this.noteOnFlyweights.get(4);
            case ROTATE_RL: return this.noteOnFlyweights.get(5);
            case EARTOUCH_L: return this.noteOnFlyweights.get(6);
            case EARTOUCH_R: return this.noteOnFlyweights.get(7);
            default: return null;
        }
    }

    private Tuple<MidiNote, MidiNote> parseToCCNote(MotionEvent motionEvent) {
        this.x += motionEvent.getDx();
        this.y += motionEvent.getDy();

        int xNormalized = normalize(this.x);
        int yNormalized = normalize(this.y);

        return new Tuple<>(new CCNote(0, xNormalized), new CCNote(1, yNormalized));
    }

    private int normalize(double y) {
        int parsed = (int) (((y + 20) / 40) * 127);

        return Math.max(Math.min(parsed, 127), 0);
    }
}
