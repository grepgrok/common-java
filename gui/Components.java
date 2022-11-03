package gui;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Components {
    /**
     * Sets up a new JTextField
     * @param bounds      The bounds of the field
     * @param parent    The parent container (e.g. the JPanel)
     * @return The initialized and set up field
     */
    public static JTextField textField(Rectangle bounds, Container parent) {
        JTextField res = new JTextField();
        res.setBounds(bounds);
        parent.add(res);
        return res;
    }

    /**
     * Sets up a new JButton. For simplicity, when calling from `class MyClass
     * extends JPanel implements ActionListener`, you can use Components.button("My
     * Name", [bounds], this, this);
     * 
     * @param name     What to label the button
     * @param bounds   The bounds
     * @param parent   The parent container (e.g. the JPanel)
     * @param listener What listens to the buttons actions
     * @return Initialized and set up button.
     */
    public static JButton button(String name, Rectangle bounds, Container parent, ActionListener listener) {
        JButton res = new JButton(name);
        res.setBounds(bounds);
        res.addActionListener(listener);
        parent.add(res);
        return res;
    }

    /**
     * Sets up a scrollable JTextArea. To get the text area use the following (or similar);
     * ```
     * JScrollPane myPane = Components.scrollPane("initial text", new Dimension(200, 250), <bounds>, this);
     * JTextArea myTextArea = (JTextArea) Components.paneComponent(myPane);
     * meTextArea.setText("new text");
     * ```
     * @param text      Initial text for the text area
     * @param dim       Dimensions of the text area as Columns x Rows 
     * @param bounds    The bounds
     * @param parent    The parent container (e.g. the JPanel)
     * @return Initialized and set up scrollPane
     * @see paneComponent needs explicit cast `(JTextArea)`
     */
    public static JScrollPane scrollPane(String text, Dimension dim, Rectangle bounds, Container parent) {
        JTextArea textArea = new JTextArea(dim.height, dim.width);
        textArea.setText(text);

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBounds(bounds);

        parent.add(scrollPane);

        return scrollPane;
    }

    public static Component paneComponent(JScrollPane pane) {
        return pane.getViewport().getView();
    }
}