package de.innohacks.MoJ.midi;

import de.innohacks.MoJ.motion.MotionListener;
import de.innohacks.MoJ.motion.MotionManager;
import de.innohacks.MoJ.motion.event.IEvent;
import de.innohacks.MoJ.transformations.Transformer;
import de.innohacks.MoJ.transformations.Tuple;

import javax.sound.midi.*;

public class MidiWriter implements MotionListener{

    private final Receiver receiver;
    private final Transformer transformer;

    public MidiWriter(int ccStartAdress) throws MidiUnavailableException {
        receiver = MidiSystem.getReceiver();
        transformer = new Transformer(ccStartAdress);
    }

    public void writeMidi(MidiNote note) {
        ShortMessage myMsg = new ShortMessage();
        try {
            myMsg.setMessage(note.getType(), 0, note.getPitch(), note.getVolume());
        } catch (InvalidMidiDataException e) {
            e.printStackTrace();
        }
        long timeStamp = -1;
        receiver.send(myMsg, timeStamp);
    }

    @Override
    public void handle(MotionManager manager, IEvent event) {
        Tuple<MidiNote, MidiNote> transform = this.transformer.transform(event);
        if (transform.first != null) this.writeMidi(transform.first);
        if (transform.second != null) this.writeMidi(transform.second);
    }
}
