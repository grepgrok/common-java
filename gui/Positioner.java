package gui;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Rectangle;

import gui.util.Direction;
import gui.util.Justify;
import gui.util.Positional;
import gui.util.Corner;

public class Positioner {
    public static final int WIDTH = 100;
    public static final int HEIGHT = 30;
    public static final int SPACER = 10;

    // * Position relative to things
    public static Rectangle positioned(Positional p, Rectangle ref, Dimension dim, int spacer) {
        int x = 0;
        int y = 0;
        switch (p.direction) {
            case UP:
                y = ref.y - dim.height - spacer;
                switch (p.justification) {
                    case START:
                        x = ref.x;
                        break;

                    case CENTER:
                        x = ref.x + ref.width/2 - dim.width/2;
                        break;
                    
                    case END:
                        x = ref.x + ref.width - dim.width;
                }
                break;
                
            case DOWN:
                switch (p.justification) {
                    case START:
                        x = ref.x;
                        break;

                    case CENTER:
                        x = ref.x + ref.width / 2 - dim.width / 2;
                        break;

                    case END:
                        x = ref.x + ref.width - dim.width;
                }
                y = ref.y + ref.height + spacer;
                break;

            case LEFT:
                x = ref.x - dim.width - spacer;
                switch (p.justification) {
                    case START:
                        y = ref.y;
                        break;

                    case CENTER:
                        y = ref.y + ref.height / 2 - dim.height / 2;
                        break;

                    case END:
                        y = ref.y + ref.height - dim.height;
                }
                break;

            case RIGHT:
                x = ref.x + ref.width + spacer;
                switch (p.justification) {
                    case START:
                        y = ref.y;
                        break;

                    case CENTER:
                        y = ref.y + ref.height / 2 - dim.height / 2;
                        break;

                    case END:
                        y = ref.y + ref.height - dim.height;
                }
                break;
        }
        return new Rectangle(x, y,dim.width, dim.height);
    }
    public static Rectangle positioned(Direction dir, Justify j, Rectangle ref, int width, int height, int spacer) {
        return positioned(new Positional(dir, j), ref, new Dimension(width, height), spacer);
    }
    public static Rectangle under(Component c) { return positioned(Direction.DOWN, Justify.START, c.getBounds(), WIDTH, HEIGHT, SPACER); }
    public static Rectangle under(Component c, int w, int h, int s) { return positioned(Direction.DOWN, Justify.START, c.getBounds(), w, h, s); }
    public static Rectangle over(Component c) { return positioned(Direction.UP, Justify.START, c.getBounds(), WIDTH, HEIGHT, SPACER); }
    public static Rectangle over(Component c, int w, int h, int s) { return positioned(Direction.UP, Justify.START, c.getBounds(), w, h, s); }
    public static Rectangle left(Component c) { return positioned(Direction.LEFT, Justify.START, c.getBounds(), WIDTH, HEIGHT, SPACER); }
    public static Rectangle left(Component c, int w, int h, int s) { return positioned(Direction.LEFT, Justify.START, c.getBounds(), w, h, s); }
    public static Rectangle right(Component c) { return positioned(Direction.RIGHT, Justify.START, c.getBounds(), WIDTH, HEIGHT, SPACER); }
    public static Rectangle right(Component c, int w, int h, int s) { return positioned(Direction.RIGHT, Justify.START, c.getBounds(), w, h, s); }

    // * Center in a reference
    // Center horizontally or vertically along a side of the 
    public static Rectangle center(Direction dir, Rectangle ref, Dimension dim, int spacer) {
        int x = ref.x;
        int y = ref.y;
        switch(dir) {
            case UP:
                x += ref.width / 2 - dim.width / 2;
                y += spacer;
                break;
            case RIGHT:
                x += ref.width - dim.width - spacer;
                y += ref.height / 2 - dim.height / 2;
                break;
            case DOWN:
                x += ref.width / 2 - dim.width / 2;
                y += ref.height - dim.height - spacer;
                break;
            case LEFT:
                x += spacer;
                y += ref.height / 2 - dim.height / 2;
                break;
            default:
                break;
        }
        return new Rectangle(x, y, dim.width, dim.height);
    }

    public static Rectangle centerLeft(Rectangle ref, Dimension dim) { return center(Direction.LEFT, ref, dim, SPACER); }
    public static Rectangle centerLeft(Dimension dim) { return center(Direction.LEFT, new Rectangle(0, 0, dim.width, dim.height), new Dimension(WIDTH, HEIGHT), SPACER); }
    public static Rectangle centerRight(Rectangle ref, Dimension dim) { return center(Direction.RIGHT, ref, dim, SPACER); }
    public static Rectangle centerRight(Dimension dim) { return center(Direction.RIGHT, new Rectangle(0, 0, dim.width, dim.height), new Dimension(WIDTH, HEIGHT), SPACER); }
    public static Rectangle centerTop(Rectangle ref, Dimension dim) { return center(Direction.UP, ref, dim, SPACER); }
    public static Rectangle centerTop(Dimension dim) { return center(Direction.UP, new Rectangle(0, 0, dim.width, dim.height), new Dimension(WIDTH, HEIGHT), SPACER); }
    public static Rectangle centerBottom(Rectangle ref, Dimension dim) { return center(Direction.DOWN, ref, dim, SPACER); }
    public static Rectangle centerBottom(Dimension dim) { return center(Direction.DOWN, new Rectangle(0, 0, dim.width, dim.height), new Dimension(WIDTH, HEIGHT), SPACER); }

    // Center horizontally and vertically
    public static Rectangle center(Rectangle ref, Dimension dim) {
        return new Rectangle(
            ref.x + ref.width / 2 - dim.width / 2,
            ref.y + ref.height / 2 - dim.height / 2,
            dim.width, dim.height
        );
    }
    public static Rectangle center(Rectangle ref, int width, int height) { return center(ref, new Dimension(width, height)); }
    public static Rectangle center(Dimension d) { return center(new Rectangle(0, 0, d.width, d.height), WIDTH, HEIGHT); }

    // * Place in corners of a reference
    public static Rectangle corner(Dimension dim, Rectangle ref, Corner corner, int spacerX, int spacerY) {
        // Already covering top left
        int x = ref.x + spacerX;
        int y = ref.y + spacerY;

        switch (corner) {
            case TOP_RIGHT:
                x = ref.x + ref.width - dim.width - spacerX;
                break;
            case BOTTOM_LEFT:
                y = ref.y + ref.height - dim.height - spacerY;
                break;
            case BOTTOM_RIGHT:
                x = ref.x + ref.width - dim.width - spacerX;
                y = ref.y + ref.height - dim.height - spacerY;
                break;
            default:
                break;
        }

        return new Rectangle(x, y, dim.width, dim.height);
    }

    public static Rectangle corner(Dimension dim, Rectangle ref, Corner cor, int spacer) {
        return corner(dim, ref, cor, spacer, spacer);
    }

    public static Rectangle topLeft(int width, int height, Rectangle ref) {
        return corner(new Dimension(width, height), ref, Corner.TOP_LEFT, SPACER);
    }
    public static Rectangle topRight(int width, int height, Rectangle ref) {
        return corner(new Dimension(width, height), ref, Corner.TOP_RIGHT, SPACER);
    }
    public static Rectangle bottomLeft(int width, int height, Rectangle ref) {
        return corner(new Dimension(width, height), ref, Corner.BOTTOM_LEFT, SPACER);
    }
    public static Rectangle bottomRight(int width, int height, Rectangle ref) {
        return corner(new Dimension(width, height), ref, Corner.BOTTOM_RIGHT, SPACER);
    }
}
