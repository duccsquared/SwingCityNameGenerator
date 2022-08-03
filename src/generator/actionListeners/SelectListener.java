package generator.actionListeners;

import components.ListObj;
import main.App;
import main.Country;

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
        ListObj<Country> lstPossible = app.getLstPossible();
        ListObj<Country> lstSelected = app.getLstSelected();
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
