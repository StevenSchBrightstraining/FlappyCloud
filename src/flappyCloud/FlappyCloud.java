package flappyCloud;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.Timer;

public class FlappyCloud implements ActionListener {

    //Fields
    public static FlappyCloud flappyCloud;
    public final int WIDTH = 800; //WIDTH in Großbuchstaben, da bei Finalen Variablen laut Konvention so vorgeschrieben
    public final int HEIGHT = 800;

    public Renderer renderer; //############################

    public Rectangle cloud; // Field für unsere Cloud/Spielfigur

    public ArrayList<Rectangle> obstacles;   //Arraylist vom Datentyp Rectangle

    public Random random;

    //Fields Spieler
    public int ticks;
    public int yMotion;


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
        jframe.setTitle("Flappy Cloud");    //Titel festlegen

        obstacles = new ArrayList<Rectangle>(); //Arraylist für Hindernisse erstellen
        cloud = new Rectangle(WIDTH / 2 - 10, HEIGHT / 2 - 10 , 50, 30); //Spielerobjekt erstellen und Größe festlegen + Startkoordinaten

        random = new Random();


        addObstacles(true);
        addObstacles(true);
        addObstacles(true);
        addObstacles(true);

        timer.start();

    }


    /**
     * Methode zum Erstellen der Hindernisse
     * @param g
     * @param obstacle
     */
    public void paintObstacles(Graphics g, Rectangle obstacle){
        g.setColor(Color.green.darker());   //Farbe der Hindernisse festlegen
        g.fillRect(obstacle.x, obstacle.y, obstacle.width, obstacle.height);    //Hindernisse befüllen
    }

    public void addObstacles(boolean start){
        int gap = 300; //Abstand zwischen den Hindernissen
        int width = 100; //Breite der Hindernisse
        int height = 50 + random.nextInt(300); //Höhe der Hindernisse festlegen. Minimum 50, Maximum 300. Random erzeugt, innerhalb des Bereichs

        if(start){
            /**
             * Hinzufügen eines neuen Elements in die ArrayList "obstacles".
             * Video Minute 27, 31
             */
            obstacles.add(new Rectangle(WIDTH + width + obstacles.size() * 300, HEIGHT - height - 120, width, height)); //Unteres Hindernis, Min 27
            obstacles.add(new Rectangle(WIDTH + width + (obstacles.size() -1) * 300, 0, width, HEIGHT - height - gap)); //Oberes Hindernis
        }else{
            obstacles.add(new Rectangle(obstacles.get(obstacles.size() -1).x + 600, HEIGHT -height - 120, width, height)); // Min 31
            obstacles.add(new Rectangle(obstacles.get(obstacles.size() -1).x, 0, width, HEIGHT - height - gap)); // Min 31
        }

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
        g.setColor(Color.pink);    //Bodenfarbe festlegen
        g.fillRect(0, HEIGHT - 150, WIDTH, 150); //Bodenmaße definieren und mit Farbe füllen

        /**
         * Grasfläche
         */

        g.setColor(Color.GREEN);    //Bodenfarbe festlegen
        g.fillRect(0, HEIGHT - 150, WIDTH, 20); //Bodenmaße definieren und mit Farbe füllen


    }
    //###################################################

    //Main
    public static void main(String[] args) {
        flappyCloud = new FlappyCloud();    //Alternativ: FlappyCloud flappycloud = new FlappyCloud(); dafür das Field flappycloud löschen
    }


    //Erstellte Methode durch Implementieren des Actionlisteners
    @Override
    public void actionPerformed(ActionEvent e) {

        /**
         * Fallmechanismus
         */
        ticks++;

        if(ticks % 2 == 0 && yMotion < 15){
            yMotion += 2;
        }
        cloud.y += yMotion;

        renderer.repaint();

    }
}
