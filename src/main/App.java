package main;

import components.*;
import components.Button;
import components.Label;
import components.TextArea;
import components.TextField;
import generator.CommandLineMain;
import generator.Global;
import generator.actionListeners.DeselectListener;
import generator.actionListeners.FilterListener;
import generator.actionListeners.GenerateListener;
import generator.actionListeners.SelectListener;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class App extends JFrame {
    private CommandLineMain funcs = new CommandLineMain();
    private int width;
    private int height;
    private CountryListObj lstPossible;
    private CountryListObj lstSelected;
    private TextField txtCityNum;
    private TextArea txaOutput;
    private TextField txtFilterPossible;
    private TextField txtFilterSelected;
    private HashMap<String,Integer> dataSizeHash;

    public CountryListObj getLstPossible() {return lstPossible;}
    public CountryListObj getLstSelected() {return lstSelected;}
    public TextField getTxtCityNum() {return txtCityNum;}
    public TextArea getTxaOutput() {return txaOutput;}
    public CommandLineMain getFuncs() {return funcs;}

    public HashMap<String, Integer> getDataSizeHash() {return dataSizeHash;}

    @Override
    public int getWidth() {return width;}
    @Override
    public int getHeight() {return height;}


    public App(String title, int width, int height) throws IOException {
        super(title);
        this.width = width+16;
        this.height = height+39;
        // JFrame functions
        this.setFocusable(true);
        this.setResizable(false);
        this.setSize(this.width,this.height); // +16, + 39
        //this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.start();
    }

    private void start() throws IOException {
        dataSizeHash = Global.importDataSizeHash();

        Label lblTitle = new Label(this,"City Name Generator",0,20,20,580,80);
        lblTitle.setFont(new Font(Font.SERIF, Font.PLAIN,  40));
        Label lblPossible = new Label(this,"Possible Countries",0,20,100,220,120);
        lblPossible.setFont(new Font(Font.SERIF, Font.PLAIN,  20));
        lstPossible = new CountryListObj(this,new Country[] {},20,180,220,400);

        Label lblSelected = new Label(this,"Selected Countries",0,380,100,580,120);
        lblSelected.setFont(new Font(Font.SERIF, Font.PLAIN,  20));
        lstSelected = new CountryListObj(this,new Country[] {},380,180,580,400);

        Button btnSelect = new Button(this,">>>>>",250,250,350,270);
        Button btnDeselect = new Button(this,"<<<<<",250,310,350,330);

        Label lblCityNum = new Label(this,"Number of cities",Label.RIGHT,50,450,200,470);
        txtCityNum = new TextField(this,"5",220,450, 280,470);
        Button btnGenerate = new Button(this,"Generate",180,500,280,520);

        txaOutput = new TextArea(this, "",300,420,580,580);
        this.showApp();

        txtFilterPossible = new TextField(this,"",40,150,200,170);
        txtFilterPossible.getDocument().addDocumentListener(new FilterListener(lstPossible,txtFilterPossible));

        txtFilterSelected = new TextField(this,"",400,150,560,170);
        txtFilterSelected.getDocument().addDocumentListener(new FilterListener(lstSelected,txtFilterSelected));


        // -----------------------------------------------------------------------
        HashMap<String,String> countryCodeHash = null;
        try {
            countryCodeHash = Global.importCountryCodeHash();
        } catch(IOException e) {e.printStackTrace();System.exit(-1);}
        ArrayList<Country> countries = new ArrayList<>();
        for(String countryCode : countryCodeHash.keySet()) {
            countries.add(new Country(this,countryCode,countryCodeHash.get(countryCode)));
        }
        lstPossible.setDataList(countries);
        // -----------------------------------------------------------------------

        btnSelect.addActionListener(new SelectListener(this));
        btnDeselect.addActionListener(new DeselectListener(this));
        btnGenerate.addActionListener(new GenerateListener(this));

    }
    private void showApp() {
        this.setLayout(null);
        this.setVisible(true);
    }
}
