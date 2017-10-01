package de.innohacks.MoJ.tetris.model;

import de.innohacks.MoJ.tetris.model.blocks.Block;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by roman on 01.10.17.
 */
public class Grid {
    private final int width;
    private final int height;

    private List<Block> blocks;

    public Grid(int width, int height) {
        this.width = width;
        this.height = height;
        this.blocks = new ArrayList<Block>();
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Block getBlock(int x, int y) {
        for (Block b: blocks) {
            if (b.getOrigin().x == x && b.getOrigin().y == y) {
                return b;
            }
        }

        return null;
    }

    public List<Block> getBlocks() {
        return new ArrayList<Block>(blocks);
    }

    public void add(Block b) {
        blocks.add(b);
    }

    public void addAll(Collection<Block> blocks) {
        this.blocks.addAll(blocks);
    }

    public int clearFullRows() {
        return 0;
    }

    private void removeRow(int y) {
        blocks = blocks.stream().
                filter(b -> b.getOrigin().y != y).
                map(b -> (b.getOrigin().y > y)? b : new Block(new Point(b.getOrigin().x, b.getOrigin().y - 1), b.getColor())).
                collect(Collectors.toList());
    }
}
