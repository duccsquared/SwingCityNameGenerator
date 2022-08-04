package generator.actionListeners;

import components.TextField;
import main.App;
import main.CountryListObj;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class FilterListener implements DocumentListener {
    private CountryListObj countryListObj;
    private TextField txtFilter;
    public FilterListener(CountryListObj countryListObj, TextField txtFilter) {
        this.countryListObj = countryListObj;
        this.txtFilter = txtFilter;
    }

    @Override
    public void insertUpdate(DocumentEvent e) {this.update();}

    @Override
    public void removeUpdate(DocumentEvent e) {this.update();}

    @Override
    public void changedUpdate(DocumentEvent e) {}

    private void update() {
        String filterStr = txtFilter.getText();
        countryListObj.setFilterStr(filterStr);
        countryListObj.updateComponent();
    }
}
