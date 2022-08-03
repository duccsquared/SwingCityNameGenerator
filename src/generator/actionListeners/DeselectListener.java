package generator.actionListeners;

import components.ListObj;
import main.App;

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
        ListObj lstSelected = app.getLstSelected();
        ListObj lstPossible = app.getLstPossible();
        if(lstSelected.isSelected()) {
            String item = lstSelected.getSelected();
            lstSelected.remove(item);
            lstPossible.add(item);
            try {
                app.getFuncs().removeCountry(item);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }
}
