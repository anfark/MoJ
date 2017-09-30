package de.innohacks.MoJ.midi;

public abstract class MidiNote {
    private int type;
    private int pitch;
    private int volume;

    public MidiNote(int type, int pitch, int volume) {
        this.type = type;
        this.pitch = pitch;
        this.volume = volume;
    }

    public int getType() {
        return type;
    }

    public int getPitch() {
        return pitch;
    }

    public int getVolume() {
        return volume;
    }
}
