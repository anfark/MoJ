package de.innohacks.MoJ.midi;

import javax.sound.midi.*;

public class MidiWriter {

    private final Receiver receiver;

    MidiWriter() throws MidiUnavailableException {
        receiver = MidiSystem.getReceiver();
    }

    public void writeMidi(MidiNote note) {
        ShortMessage myMsg = new ShortMessage();
        try {
            myMsg.setMessage(note.getType(), 0, note.getValue(), 93);
        } catch (InvalidMidiDataException e) {
            e.printStackTrace();
        }
        long timeStamp = -1;
        receiver.send(myMsg, timeStamp);
    }
}