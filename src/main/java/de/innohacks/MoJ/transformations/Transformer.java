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

    List<Tuple<MidiNote, MidiNote>> noteOneFlyweights;

    public Transformer() {
        this.noteOneFlyweights = new ArrayList<>();
        noteOneFlyweights.add(new Tuple<>(new NoteOn(0), null));
        noteOneFlyweights.add(new Tuple<>(new NoteOn(1), null));
        noteOneFlyweights.add(new Tuple<>(new NoteOn(2), null));
        noteOneFlyweights.add(new Tuple<>(new NoteOn(3), null));
        noteOneFlyweights.add(new Tuple<>(new NoteOn(4), null));
        noteOneFlyweights.add(new Tuple<>(new NoteOn(5), null));
        noteOneFlyweights.add(new Tuple<>(new NoteOn(6), null));
        noteOneFlyweights.add(new Tuple<>(new NoteOn(7), null));
    }
    public Tuple<MidiNote, MidiNote>  transform(IEvent motionEvent) {
        if (motionEvent instanceof MotionEvent) {
            return parseToCCNote((MotionEvent) motionEvent);
        }
        else if (motionEvent instanceof GestureEvent) {
            return parseToNotOn((GestureEvent) motionEvent);
        }
        else {
            throw new UnsupportedOperationException();
        }
    }

    private Tuple<MidiNote, MidiNote> parseToNotOn(GestureEvent motionEvent) {
        switch (motionEvent.getGesture()) {
            case SWIPE_L: return this.noteOneFlyweights.get(0);
            case SWIPE_R: return this.noteOneFlyweights.get(1);
            case CIRCLE_L: return this.noteOneFlyweights.get(2);
            case CIRCLE_R: return this.noteOneFlyweights.get(3);
            case ROTATE_LR: return this.noteOneFlyweights.get(4);
            case ROTATE_RL: return this.noteOneFlyweights.get(5);
            case EARTOUCH_L: return this.noteOneFlyweights.get(6);
            case EARTOUCH_R: return this.noteOneFlyweights.get(7);
            default: return null;
        }
    }

    private Tuple<MidiNote, MidiNote> parseToCCNote(MotionEvent motionEvent) {
        return new Tuple<>(new CCNote(0, (int)motionEvent.getDx()), new CCNote(1, (int)motionEvent.getDy()));
    }
}
