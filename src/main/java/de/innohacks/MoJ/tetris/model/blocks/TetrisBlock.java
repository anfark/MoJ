package de.innohacks.MoJ.tetris.model.blocks;

import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by roman on 01.10.17.
 */
public class TetrisBlock extends Block {
    private TetrisShape shape;


    public TetrisBlock(TetrisShape shape, Point origin, Color color) {
        super(origin, color);
        this.shape = shape;
    }


    public Rectangle getBounds() {
        return new Rectangle(getOrigin().x + shape.getX(), getOrigin().y + shape.getY(), shape.getWidth(), shape.getHeight());
    }

    public void moveTo(Point point) {
        this.origin = point;
    }

    public void translate(int dx, int dy) {
        this.origin = new Point(origin.x + dx, origin.y + dy);
    }

    public void rotate(boolean left) {
        shape = shape.rotated(left);
    }

    public List<Block> getBlocks() {
        return shape.getPoints().
                stream().
                map(p -> new Block(new Point(p.x + origin.x, p.y + origin.y), getColor())).
                collect(Collectors.toList());
    }
}
