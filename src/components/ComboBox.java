package components;

import main.App;
import javax.swing.*;

public class ComboBox<T> extends JComboBox<T> {
    public ComboBox(App app, T[] items, double x1, double y1, double x2, double y2) {
        super(items);
        this.setBounds((int)x1,(int)y1,(int)(x2-x1),(int)(y2-y1));
        app.add(this);
    }
}
