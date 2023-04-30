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

    public boolean gameOver;
    public boolean hasGameStarted = true;


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
        g.setColor(Color.YELLOW.darker());   //Farbe der Hindernisse festlegen
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
            obstacles.add(new Rectangle(WIDTH + width + obstacles.size() * 300, HEIGHT - height - 120, width, height)); //Unteres Hindernis, Wird außerhalb der Spielfeldbreite erzeugt. Min 27
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

        /**
         *  Hindernisse in der ArrayList einfärben, iteration durch die ArrayList
         */
        for (Rectangle obstacles : obstacles) {
            paintObstacles(g, obstacles);
        }

        /**
         * Game Over Darstellung
         */
        g.setColor(Color.white);
        g.setFont(new Font("Georgia", 1, 100));

        if(gameOver){
            g.drawString("Game Over", 75, HEIGHT / 2 - 50);
        }

    }
    //###################################################

    //Main
    public static void main(String[] args) {
        flappyCloud = new FlappyCloud();    //Alternativ: FlappyCloud flappycloud = new FlappyCloud(); dafür das Field flappycloud löschen
    }


    //Erstellte Methode durch Implementieren des Actionlisteners
    @Override
    public void actionPerformed(ActionEvent e) {

        int speed = 10;
        ticks++;

        if(hasGameStarted){
            /**
             *  Bewegung der Hindernisse
             */
            for (int i = 0; i < obstacles.size(); i++) {
                Rectangle obstacle = obstacles.get(i);
                obstacle.x -= speed; // X-Koordinate des Hindernisses wird bei jedem Durchlauf um 10 reduziert
            }
            //---------------------------------------
            /**
             * Hindernisse nach Durchlaufen des Spielfeldes aus der ArrayList entfernen
             * Min 41
             */
            for (int i = 0; i < obstacles.size(); i++) {
                Rectangle obstacle = obstacles.get(i);

                if(obstacle.x + obstacle.width < 0){
                    obstacles.remove(obstacle);

                    if(obstacle.y == 0){
                        addObstacles(false);
                    }
                }
            }


            /**
             * Fallmechanismus Spieler
             */
            if(ticks % 2 == 0 && yMotion < 15){
                yMotion += 2;
            }
            cloud.y += yMotion;

            /**
             * Kollisionserkennung, direkt nach Bewegung der Spielfigur (cloud.y += yMotion), gameOver-Fälle definieren
             */
            for (Rectangle obstacle : obstacles) {
                if(obstacle.intersects(cloud)){
                    gameOver = true;
                }
            }
            if(cloud.y > HEIGHT - 120 || cloud.y < 0){
                gameOver = true;
            }

        }
        renderer.repaint();
    }
}
