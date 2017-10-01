package de.innohacks.MoJ.tetris.gui;

import de.innohacks.MoJ.tetris.model.World;
import de.innohacks.MoJ.tetris.model.blocks.Block;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * Created by roman on 01.10.17.
 */
public class WorldRenderer extends JPanel {
    private World world;

    public WorldRenderer(World world) {
        this.world = world;
    }

    @Override
    public void paint(Graphics g) {
        g.clipRect(0, 0, getWidth(), getHeight());
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(0, 0, getWidth(), getHeight());


        Rectangle f = worldFrame();
        g.setColor(Color.WHITE);
        g.fillRect(f.x, f.y, f.width, f.height);
        List<Block> blocks = world.getBlocks();

        for (Block b: blocks) {
            Rectangle bounds = frame(b.getOrigin());

            //System.out.println("DRAW " + bounds);

            g.setColor(b.getColor());
            g.fillRect(bounds.x, bounds.y, bounds.width, bounds.height);
        }


        if (world.isGameOver()) {
            g.setColor(Color.BLACK);
            drawCenteredString(g, "Game Over", f, new Font(Font.MONOSPACED, Font.BOLD, 64));
        }
        else if (world.isPaused()) {
            g.setColor(Color.BLACK);
            drawCenteredString(g, "Pause", f, new Font(Font.MONOSPACED, Font.BOLD, 64));
        }
    }

    private void drawCenteredString(Graphics g, String text, Rectangle rect, Font font) {
        // Get the FontMetrics
        FontMetrics metrics = g.getFontMetrics(font);
        // Determine the X coordinate for the text
        int x = rect.x + (rect.width - metrics.stringWidth(text)) / 2;
        // Determine the Y coordinate for the text (note we add the ascent, as in java 2d 0 is top of the screen)
        int y = rect.y + ((rect.height - metrics.getHeight()) / 2) + metrics.getAscent();
        // Set the font
        g.setFont(font);
        // Draw the String
        g.drawString(text, x, y);
    }


    private int getBlockSize() {
        return Math.min(getWidth() / world.getWidth(), getHeight() / world.getHeight());
    }

    private Rectangle worldFrame() {
        int s = getBlockSize();

        int w = world.getWidth() * s;
        int h = world.getHeight() * s;

        int x = (getWidth() - w) / 2;
        int y = (getHeight() - h) / 2;

        return new Rectangle(x, y, w, h);
    }


    private Rectangle frame(Point point) {
        int s = getBlockSize();
        Rectangle f = worldFrame();

        return new Rectangle(f.x + point.x * s, f.y + point.y * s, s, s);
    }
}
