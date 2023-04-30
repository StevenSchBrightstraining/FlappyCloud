package flappyCloud;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

public class FlappyCloud implements ActionListener {

    //Fields
    public static FlappyCloud flappyCloud;
    public final int WIDTH = 800; //WIDTH in Großbuchstaben, da bei Finalen Variablen laut Konvention so vorgeschrieben
    public final int HEIGHT = 800;

    public Renderer renderer; //############################

    //Constructor
    public FlappyCloud(){

        /**
         * JFrame Instanziierung und Grundeinstellungen
         */
        JFrame jframe = new JFrame();   //Neues JFrame objekt instanziieren
        Timer timer = new Timer(20, this);

        //###########################################
        renderer = new Renderer();
        jframe.add(renderer);
        //###########################################

        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //Festlegen, dass das Programm beim Schließen beendet wird. (X)
        jframe.setResizable(false); //Festlegen, dass die Fenstergröße nachtrglich nicht mehr geändert werden kann.
        jframe.setSize(WIDTH, HEIGHT);  //Größe des jframes festlegen
        jframe.setVisible(true);        //Sichtbarkeit herstellen

        timer.start();

    }

    //Automatisch erstellt aus "Renderer.java"
    //###################################################
    public void repaint(Graphics g) {
    }
    //###################################################

    //Main
    public static void main(String[] args) {
        flappyCloud = new FlappyCloud();    //Alternativ: FlappyCloud flappycloud = new FlappyCloud(); dafür das Field flappycloud löschen
    }


    //Erstellte Methode durch implementieren des Actionlisteners
    @Override
    public void actionPerformed(ActionEvent e) {
        renderer.repaint();
    }
}
