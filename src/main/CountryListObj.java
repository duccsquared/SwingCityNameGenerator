package main;

import components.ListObj;

import java.util.ArrayList;
import java.util.Locale;

public class CountryListObj extends ListObj<Country> {
    private String filterStr = "";
    private App app;
    private ArrayList<Country> baseArray = new ArrayList<>();
    private ArrayList<Country> processedArray = new ArrayList<>();

    public String getFilterStr() {return filterStr;}
    public void setFilterStr(String filterStr) {this.filterStr = filterStr;}

    public CountryListObj(App app, Country[] listData, double x1, double y1, double x2, double y2) {
        super(app, listData, x1, y1, x2, y2);
        this.app = app;
    }

    @Override
    public void setDataList(ArrayList<Country> dataList) {
        baseArray = (ArrayList<Country>) dataList.clone();
        this.updateComponent();
    }

    @Override
    public void add(Country val) {
        baseArray.add(val);
        super.add(val);
    }

    @Override
    public void remove(Country val) {
        baseArray.remove(val);
        super.remove(val);
    }

    @Override
    public Country get(int index) {
        return super.get(index);
    }

    @Override
    public Country getSelected() {
        return super.getSelected();
    }

    @Override
    public void updateComponent() {
        this.updateProcessedArray();
        this.dataList = (ArrayList<Country>) processedArray.clone();
        super.updateComponent();
    }
    protected void updateProcessedArray() {
        filterStr = filterStr.toLowerCase(Locale.ROOT);
        System.out.println(filterStr);
        if(!filterStr.equals("")) {
            processedArray.clear();
            for(Country country : baseArray) {
                System.out.println(country.toString());
                if(country.toString().toLowerCase(Locale.ROOT).contains(filterStr)) {
                    processedArray.add(country);
                }
            }
        }
        else {
            processedArray = (ArrayList<Country>) baseArray.clone();
        }
    }
}
