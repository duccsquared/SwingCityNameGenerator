package components;

import main.App;

import javax.swing.*;

public class TextArea extends JTextArea {
    public TextArea(App app, String text,  double x1, double y1, double x2, double y2) {
        super(text);
        this.setBounds((int)x1,(int)y1,(int)(x2-x1),(int)(y2-y1));
        app.add(this);
    }
}
