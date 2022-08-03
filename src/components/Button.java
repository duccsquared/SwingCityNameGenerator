package components;

import main.App;

import javax.swing.*;

public class Button extends JButton {
    //public components.Button() {}
    //public components.Button(Icon icon) {super(icon);}
    public Button(App app, String text, double x1, double y1, double x2, double y2) {
        super(text);
        this.setBounds((int)x1,(int)y1,(int)(x2-x1),(int)(y2-y1));
        app.add(this);
    }
    //public components.Button(Action a) {super(a);}
    //public components.Button(String text, Icon icon) {super(text, icon);}
}
