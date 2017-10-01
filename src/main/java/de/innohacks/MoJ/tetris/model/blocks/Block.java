package de.innohacks.MoJ.tetris.model.blocks;

import java.awt.*;

/**
 * Created by roman on 01.10.17.
 */
public class Block {
    private final Color color;

    protected Point origin;

    public Block(Point origin, Color color) {
        this.color = color;
        this.origin = origin;
    }

    public Point getOrigin() {
        return origin;
    }

    public Color getColor() {
        return color;
    }
    /*
    @Override
    public int hashCode() {
        return origin.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Block) {
            Block block = (Block)(obj);

            return block.origin.equals(origin) && block.color.equals(color);
        }

        return false;
    }*/
}
