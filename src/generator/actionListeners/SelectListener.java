package generator.actionListeners;

import components.ListObj;
import main.App;
import main.Country;
import main.CountryListObj;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class SelectListener implements ActionListener {
    private App app;
    public SelectListener(App app) {
        this.app = app;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        CountryListObj lstPossible = app.getLstPossible();
        CountryListObj lstSelected = app.getLstSelected();
        if(lstPossible.isSelected()) {
            Country item = lstPossible.getSelected();
            lstPossible.remove(item);
            lstSelected.add(item);
            try {
                app.getFuncs().addCountry(item.getCountryCode());
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }

    }
}
