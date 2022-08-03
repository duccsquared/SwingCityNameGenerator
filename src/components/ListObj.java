package components;

import main.App;

import javax.swing.*;
import java.util.ArrayList;

public class ListObj extends JList<String> {
    ArrayList<String> dataList = new ArrayList<>();
    public ListObj(App app, String[] listData, double x1, double y1, double x2, double y2) {
        super(listData);
        for(String item: listData) {
            dataList.add(item);
        }
        this.setBounds((int)x1,(int)y1,(int)(x2-x1),(int)(y2-y1));
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportView(this);
        this.setLayoutOrientation(JList.VERTICAL);
        scrollPane.setBounds((int)x1,(int)y1,(int)(x2-x1),(int)(y2-y1));
        app.add(scrollPane);
    }
    public void setDataList(ArrayList<String> dataList) {
        this.dataList = (ArrayList<String>) dataList.clone();
        updateComponent();
    }
    public void add(String val) {
        dataList.add(val);
        updateComponent();
    }
    public void remove(String val) {
        dataList.remove(val);
        updateComponent();
    }
    public String get(int index) {
        return dataList.get(index);
    }
    public String getSelected() {
        return this.get(this.getSelectedIndex());
    }
    public boolean isSelected() {
        return this.getSelectedIndex()!=-1;
    }
    private void updateComponent() {
        this.setListData(dataList.toArray(new String[0]));
    }
}
