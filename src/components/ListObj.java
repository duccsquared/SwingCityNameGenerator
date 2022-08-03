package components;

import main.App;

import javax.swing.*;

public class ListObj<T> extends JList<T> {
    public ListObj(App app, T[] listData, double x1, double y1, double x2, double y2) {
        super(listData);
        this.setBounds((int)x1,(int)y1,(int)(x2-x1),(int)(y2-y1));
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportView(this);
        this.setLayoutOrientation(JList.VERTICAL);
        scrollPane.setBounds((int)x1,(int)y1,(int)(x2-x1),(int)(y2-y1));
        app.add(scrollPane);
    }
}
