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

    public Rectangle cloud; // Field für unsere Cloud/Spielfigur

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

        cloud = new Rectangle(WIDTH / 2 - 10, HEIGHT / 2 - 10 , 50, 30); //Spielerobjekt erstellen und Größe festlegen + Startkoordinaten

        timer.start();

    }

    //Automatisch erstellt aus "Renderer.java"
    //###################################################
    public void repaint(Graphics g) {
        /**
         * Hintergrund
         */
        g.setColor(Color.gray); //Hintergrundfarbe festlegen
        g.fillRect(0, 0, WIDTH, HEIGHT); //Hintergrundfarbe anwenden, Gesamtgröße (WIDTH, HEIGHT) übernehmen


        /**
         * Spielfigur
         */
        g.setColor(Color.orange);   //Farbe der Spielfigur festlegen
        g.fillRect(cloud.x, cloud.y, cloud.width, cloud.height);    //Spielfigur mit Farbe füllen, Koordinaten der Spielfigur

        /**
         * Boden
         */
        g.setColor(Color.GREEN);    //Bodenfarbe festlegen
        g.fillRect(0, HEIGHT - 150, WIDTH, 150); //Bodenmaße definieren und mit Farbe füllen

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
