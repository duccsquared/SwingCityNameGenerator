package main;

import components.*;
import components.Button;
import components.Label;
import components.TextArea;
import components.TextField;
import generator.Global;
import generator.actionListeners.DeselectListener;
import generator.actionListeners.SelectListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class App extends JFrame {
    private int width;
    private int height;
    private ListObj lstPossible;
    private ListObj lstSelected;
    private TextField txtCityNum;
    private TextArea txaOutput;

    public ListObj getLstPossible() {return lstPossible;}
    public ListObj getLstSelected() {return lstSelected;}
    public TextField getTxtCityNum() {return txtCityNum;}
    public TextArea getTxaOutput() {return txaOutput;}

    @Override
    public int getWidth() {return width;}
    @Override
    public int getHeight() {return height;}


    public App(String title, int width, int height) {
        super(title);
        this.width = width;
        this.height = height;
        // JFrame functions
        this.setFocusable(true);
        this.setResizable(false);
        this.setSize(this.width,this.height); // +16, + 39
        //this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.start();
    }

    private void start() {
        Label lblTitle = new Label(this,"City Name Generator",0,20,20,580,80);
        lblTitle.setFont(new Font(Font.SERIF, Font.PLAIN,  40));
        Label lblPossible = new Label(this,"Possible Countries",0,20,120,280,180);
        lblPossible.setFont(new Font(Font.SERIF, Font.PLAIN,  20));
        lstPossible = new ListObj(this,new String[] {},80,180,220,400);

        Label lblSelected = new Label(this,"Selected Countries",0,320,120,580,180);
        lblSelected.setFont(new Font(Font.SERIF, Font.PLAIN,  20));
        lstSelected = new ListObj(this,new String[] {},380,180,520,400);

        Button btnSelect = new Button(this,">>>>>",250,250,350,270);
        Button btnDeselect = new Button(this,"<<<<<",250,310,350,330);

        Label lblCityNum = new Label(this,"Number of cities",Label.RIGHT,50,450,200,470);
        txtCityNum = new TextField(this,"10",220,450, 280,470);
        Button btnGenerate = new Button(this,"Generate",180,500,280,520);

        txaOutput = new TextArea(this, "",300,420,550,550);

        this.showApp();

        // -----------------------------------------------------------------------
        HashMap<String,String> countryCodeHash = null;
        try {
            countryCodeHash = Global.importCountryCodeHash();
        } catch(IOException e) {e.printStackTrace();System.exit(-1);}
        ArrayList<String> items = new ArrayList<>();
        items.addAll(countryCodeHash.values());
        lstPossible.setDataList(items);
        // -----------------------------------------------------------------------

        btnSelect.addActionListener(new SelectListener(this));
        btnDeselect.addActionListener(new DeselectListener(this));

    }
    private void showApp() {
        this.setLayout(null);
        this.setVisible(true);
    }
}
