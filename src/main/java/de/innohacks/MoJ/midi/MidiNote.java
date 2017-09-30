package de.innohacks.MoJ.midi;

public abstract class MidiNote {
    private int type;
    private int value;

    public MidiNote(int type, int value) {
        this.type = type;
        this.value = value;
    }

    public int getType() {
        return type;
    }

    public int getValue() {
        return value;
    }
}
