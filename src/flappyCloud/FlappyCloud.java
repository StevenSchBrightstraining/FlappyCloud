package flappyCloud;

import javax.swing.*;

public class FlappyCloud {

    //Fields
    public static FlappyCloud flappyCloud;
    public final int WIDTH = 800; //WIDTH in Großbuchstaben, da bei Finalen Variablen laut Konvention so vorgeschrieben
    public final int HEIGHT = 800;

    //Constructor
    public FlappyCloud(){
        JFrame jframe = new JFrame();   //Neues JFrame objekt instanziieren

        jframe.setSize(WIDTH, HEIGHT);  //Größe des jframes festlegen
        jframe.setVisible(true);        //Sichtbarkeit herstellen

    }

    //Main
    public static void main(String[] args) {
        flappyCloud = new FlappyCloud();    //Alternativ: FlappyCloud flappycloud = new FlappyCloud(); dafür das Field flappycloud löschen
    }
}
