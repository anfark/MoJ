package de.innohacks.MoJ.tetris.model.blocks;

import java.awt.*;

/**
 * Created by roman on 01.10.17.
 */
public class SimpleBlockFactory implements IBlockFactory {
    private TetrisShape[] shapes;
    private Color[] colors;

    public SimpleBlockFactory() {

        TetrisShape quad = new TetrisShape(
                new Point(0, 0),
                new Point(1, 0),
                new Point(0, 1),
                new Point(1, 1));

        TetrisShape I = new TetrisShape(
                new Point(0, -2),
                new Point(0, -1),
                new Point(0, 0),
                new Point(0, 1));

        TetrisShape L = new TetrisShape(
                new Point(0, 0),
                new Point(0, -1),
                new Point(0, -2),
                new Point(1, 0));

        TetrisShape[] temp = {quad, I, L};
        shapes = temp;

        Color[] tempColor = {Color.BLUE, Color.RED, Color.GREEN};
        colors = tempColor;
    }


    @Override
    public TetrisBlock createRandomBlock() {
        int rand = (int)(Math.random() * shapes.length);
        return new TetrisBlock(shapes[rand], new Point(), colors[rand]);
    }
}
