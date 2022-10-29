package gui;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Rectangle;

import gui.util.Direction;
import gui.util.Justify;
import gui.util.Positional;

public class Positioner {
    public static final int WIDTH = 100;
    public static final int HEIGHT = 30;
    public static final int SPACER = 10;

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
                        System.out.println("ref.x: " + ref.x);
                        System.out.println("ref.width: " + ref.width);
                        System.out.println("dim.width: " + dim.width);
                        x = ref.x + ref.width / 2 - dim.width / 2;
                        System.out.println(x);
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

    public static Rectangle center(Rectangle rect, Dimension dim) {
        return new Rectangle(
            rect.x + rect.width / 2 - dim.width / 2,
            rect.y + rect.height / 2 - dim.height / 2,
            dim.width, dim.height
        );
    }
    public static Rectangle center(Rectangle rect, int width, int height) { return center(rect, new Dimension(width, height)); }
    public static Rectangle center(Dimension d) { return center(new Rectangle(0, 0, d.width, d.height), WIDTH, HEIGHT); }
}
