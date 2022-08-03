package generator.actionListeners;

import components.TextArea;
import main.App;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

public class GenerateListener implements ActionListener {
    private App app;
    public GenerateListener(App app) {
        this.app = app;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        components.TextField txtCityNum = app.getTxtCityNum();
        TextArea txaOutput = app.getTxaOutput();
        int cityNum = Integer.parseInt(txtCityNum.getText());
        String result = "";
        for(int i = 0; i < cityNum; i++) {
            try {
                result += app.getFuncs().generateCity() + "\n";
            } catch (IOException ioException) {ioException.printStackTrace();}
        }
        txaOutput.setText(result);
    }
}
