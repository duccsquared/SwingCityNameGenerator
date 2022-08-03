package components;

import main.App;

import javax.swing.*;
import java.util.ArrayList;

public class ListObj<T> extends JList<T> {
    ArrayList<T> dataList = new ArrayList<>();
    public ListObj(App app, T[] listData, double x1, double y1, double x2, double y2) {
        super(listData);
        for(T item: listData) {
            dataList.add(item);
        }
        this.setBounds((int)x1,(int)y1,(int)(x2-x1),(int)(y2-y1));
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportView(this);
        this.setLayoutOrientation(JList.VERTICAL);
        scrollPane.setBounds((int)x1,(int)y1,(int)(x2-x1),(int)(y2-y1));
        app.add(scrollPane);
    }
    public void setDataList(ArrayList<T> dataList) {
        this.dataList = (ArrayList<T>) dataList.clone();
        updateComponent();
    }
    public void add(T val) {
        dataList.add(val);
        updateComponent();
    }
    public void remove(T val) {
        dataList.remove(val);
        updateComponent();
    }
    public T get(int index) {
        return dataList.get(index);
    }
    public T getSelected() {
        return this.get(this.getSelectedIndex());
    }
    public boolean isSelected() {
        return this.getSelectedIndex()!=-1;
    }
    private void updateComponent() {
        this.setListData((T[]) dataList.toArray(new Object[0]));
    }
}
