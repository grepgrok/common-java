// Fun fact: java automatically imports any `util` folders
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import gui.DrawString;
import gui.util.Direction;
import gui.util.Justify;

public class Runner extends JPanel {
    public static void main(String[] args) {
        JFrame fr = new JFrame("Tests");
        Runner rn = new Runner();
        fr.add(rn);
        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fr.pack();
        fr.setVisible(true);
    }

    private Dimension dim;
    private JTextArea testArea;

    public Runner() {
        dim = new Dimension(800, 600);
        testArea = new JTextArea();
        testArea.setBounds(350, 20, 100, 20);
        add(testArea);
        setLayout(null);
    }

    public Dimension getPreferredSize() {
        return dim;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        DrawString ds = new DrawString(g);

        ds.positionText(Direction.DOWN, Justify.START, "This is some text\n"
        + "with newlines\nand\ttabs\n"
        + "even\bbackspace\n"
        + "of course\\backslash and\'single quote and \"double quote\n"
        + "but does\fformfeed work?-Yes\nand form feed continues from the original beginning\n"
        + "overwrite this text it is a lot of text\rhow about carriage return?"
        , testArea);
    }
}
