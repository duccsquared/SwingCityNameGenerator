package main;

import components.*;
import components.Button;
import components.Label;
import components.TextArea;
import components.TextField;

import javax.swing.*;
import java.awt.*;

public class App extends JFrame {
    private int width;
    private int height;

    @Override
    public int getWidth() {return width;}
    @Override
    public int getHeight() {
        return height;
    }


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
        Label title = new Label(this,"Insert title here",0,20,20,580,80);
        title.setFont(new Font(Font.SERIF, Font.PLAIN,  40));
        new Button(this,"push me",300,300,400,320);
        new ComboBox<>(this,new String[] {"A","B","C"},200,200,280,220);
        new TextField(this,"Welcome to Javatpoint.",50,100, 250,130);
        new TextField(this,"AWT Tutorial",50,150, 250,180);
        new ListObj<>(this,new String[] {"D", "E", "F","G","H","I"},400,400,480,460);
        new TextArea(this, "yes yes",400,200,550,350);
        this.showApp();
    }
    private void showApp() {
        this.setLayout(null);
        this.setVisible(true);
    }
}
