package de.innohacks.MoJ.transformations;

import de.innohacks.MoJ.midi.CCNote;
import de.innohacks.MoJ.midi.MidiNote;
import de.innohacks.MoJ.midi.NoteOn;
import de.innohacks.MoJ.motion.event.GestureEvent;
import de.innohacks.MoJ.motion.event.IEvent;
import de.innohacks.MoJ.motion.event.MotionEvent;
import de.innohacks.MoJ.motion.event.MouseToggle;

import java.util.ArrayList;
import java.util.List;

public class Transformer {

    List<Tuple<MidiNote, MidiNote>> noteOnFlyweights;
    double x;
    double y;
    private int ccStartAdress;

    public Transformer(int ccStartAdress) {
        this.noteOnFlyweights = new ArrayList<>();
        this.ccStartAdress = ccStartAdress;
        noteOnFlyweights.add(new Tuple<>(new NoteOn(ccStartAdress * 7 + 0), null));
        noteOnFlyweights.add(new Tuple<>(new NoteOn(ccStartAdress * 7 + 1), null));
        noteOnFlyweights.add(new Tuple<>(new NoteOn(ccStartAdress * 7 + 2), null));
        noteOnFlyweights.add(new Tuple<>(new NoteOn(ccStartAdress * 7 + 3), null));
        noteOnFlyweights.add(new Tuple<>(new NoteOn(ccStartAdress * 7 + 4), null));
        noteOnFlyweights.add(new Tuple<>(new NoteOn(ccStartAdress * 7 + 5), null));
        noteOnFlyweights.add(new Tuple<>(new NoteOn(ccStartAdress * 7 + 6), null));
        noteOnFlyweights.add(new Tuple<>(new NoteOn(ccStartAdress * 7 + 7), null));
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
        else if (motionEvent instanceof MouseToggle){
            this.x = 0;
            this.y = 0;
            return new Tuple<>(null, null);
        } else {
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

        return new Tuple<>(new CCNote(ccStartAdress * 2 + 0, xNormalized), new CCNote(ccStartAdress * 2 + 1, yNormalized));
    }

    private int normalize(double y) {
        int parsed = (int) (((y + 20) / 40) * 127);

        return Math.max(Math.min(parsed, 127), 0);
    }
}
