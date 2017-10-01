package de.innohacks.MoJ.tetris.model;

import de.innohacks.MoJ.tetris.model.blocks.Block;
import de.innohacks.MoJ.tetris.model.blocks.IBlockFactory;
import de.innohacks.MoJ.tetris.model.blocks.SimpleBlockFactory;
import de.innohacks.MoJ.tetris.model.blocks.TetrisBlock;

import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Created by roman on 01.10.17.
 */
public class World {

    private IBlockFactory factory;
    private TetrisBlock currentBlock;
    private Grid grid;

    private boolean pause = false;
    private boolean gameOver = false;

    public World(int width, int height) {
        grid = new Grid(width, height);
        factory = new SimpleBlockFactory();
        currentBlock = createBlock();
    }

    public void reset() {
        grid = new Grid(grid.getWidth(), grid.getHeight());
        currentBlock = createBlock();
        gameOver = false;
    }

    public int getWidth() {
        return grid.getWidth();
    }

    public int getHeight() {
        return grid.getHeight();
    }

    public TetrisBlock getCurrentBlock() {
        return currentBlock;
    }

    public void setPause(boolean pause) {
        this.pause = pause;
    }

    public boolean isPaused() {
        return pause;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void update() {
        if (pause || gameOver) {
            return;
        }

        // Move TetrisBlock down
        if (!tryToMove(0, 1)) {

            if (currentBlock.getBlocks().stream().anyMatch(b -> b.getOrigin().y <= 0)) {
                gameOver = true;
            }
            else {
                grid.addAll(currentBlock.getBlocks());
                grid.clearFullRows();
                currentBlock = createBlock();
            }
        }
    }

    public synchronized boolean tryToMove(int dx, int dy) {
        Rectangle b = currentBlock.getBounds();

        if (b.getMaxY() + dy < grid.getHeight() &&
                //b.getMinY() + dy > 0 &&
                b.getMinX() + dx > 0 &&
                b.getMaxX() + dx < grid.getWidth()) {

            for(Block block: currentBlock.getBlocks()) {
                if (grid.getBlock(block.getOrigin().x + dx, block.getOrigin().y + dy) != null) {
                    return false;
                }
            }

            currentBlock.translate(dx, dy);
            return true;
        }
        else {
            return false;
        }
    }

    public synchronized boolean  tryToRotate(boolean left) {
        currentBlock.rotate(left);

        for (Block b: currentBlock.getBlocks()) {
            if (grid.getBlock(b.getOrigin().x, b.getOrigin().y) != null) {
                currentBlock.rotate(!left);
                return false;
            }
        }

        return true;
    }

    private TetrisBlock createBlock() {
        TetrisBlock block = factory.createRandomBlock();
        Rectangle bounds = block.getBounds();

        int x = (getWidth() - bounds.width) / 2;
        int y = -bounds.height;

        block.translate(x - bounds.x, y - bounds.y);

        return block;
    }


    public List<Block> getBlocks() {
        List<Block> out =  grid.getBlocks();
        out.addAll(currentBlock.getBlocks());
        return out;
    }
}
