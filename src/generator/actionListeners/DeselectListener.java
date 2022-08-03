package generator.actionListeners;

import components.ListObj;
import main.App;
import main.Country;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class DeselectListener implements ActionListener {
    private App app;
    public DeselectListener(App app) {
        this.app = app;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        ListObj<Country> lstSelected = app.getLstSelected();
        ListObj<Country> lstPossible = app.getLstPossible();
        if(lstSelected.isSelected()) {
            Country item = lstSelected.getSelected();
            lstSelected.remove(item);
            lstPossible.add(item);
            try {
                app.getFuncs().removeCountry(item.getCountryCode());
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }
}
