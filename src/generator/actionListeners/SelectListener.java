package generator.actionListeners;

import components.ListObj;
import main.App;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SelectListener implements ActionListener {
    private App app;
    public SelectListener(App app) {
        this.app = app;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        ListObj lstPossible = app.getLstPossible();
        ListObj lstSelected = app.getLstSelected();
        if(lstPossible.isSelected()) {
            String item = lstPossible.getSelected();
            lstPossible.remove(item);
            lstSelected.add(item);
        }

    }
}
