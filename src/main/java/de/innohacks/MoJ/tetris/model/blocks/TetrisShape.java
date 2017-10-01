package de.innohacks.MoJ.tetris.model.blocks;

import de.innohacks.MoJ.tetris.Tetris;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by roman on 01.10.17.
 */
public class TetrisShape {
    private final List<Point> points;


    public TetrisShape(List<Point> points) {
        this.points = points;
    }

    public TetrisShape(Point... points) {
        this(Arrays.asList(points));
    }

    public int getWidth() {
        return getMaxX() - getX();
    }

    public int getHeight() {
        return getMaxY() - getY();
    }

    public int getX() {
        return points.stream().map(p -> p.x).min((Integer a, Integer b) -> (a - b)).get();
    }

    public int getY() {
        return points.stream().map(p -> p.y).min((Integer a, Integer b) -> (a - b)).get();
    }

    public int getMaxX() {
        return points.stream().map(p -> p.x).max((Integer a, Integer b) -> (a - b)).get();
    }

    public int getMaxY() {
        return points.stream().map(p -> p.y).max((Integer a, Integer b) -> (a - b)).get();
    }

    public TetrisShape rotated(boolean left) {
        return new TetrisShape(points.stream().map(p -> new Point(p.y, -p.x)).collect(Collectors.toList()));
    }

    public List<Point> getPoints() {
        return new ArrayList<Point>(points);
    }
}
