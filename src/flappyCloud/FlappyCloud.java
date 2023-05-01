
package flappyCloud;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.Timer;

public class FlappyCloud implements ActionListener, MouseListener, KeyListener {

    //Fields
    public static FlappyCloud flappyCloud;
    public final int WIDTH = 800; //WIDTH in Großbuchstaben, da bei Finalen Variablen laut Konvention so vorgeschrieben
    public final int HEIGHT = 800;

    public Renderer renderer; //############################

    public Rectangle cloud; // Field für unsere Cloud/Spielfigur, Datentyp Rectangle

    public ArrayList<Rectangle> obstacles;   //Arraylist vom Datentyp Rectangle für die Hindernisse

    public Random random;

    //Fields Spieler
    public int ticks;
    public int yMotion;

    public int score;

    public boolean gameOver;
    public boolean hasGameStarted = false;


    //Constructor
    public FlappyCloud(){

        /**
         * JFrame Instanziierung und Grundeinstellungen für das Spielfeld
         */

        JFrame jframe = new JFrame();   //Neues JFrame objekt instanziieren
        Timer timer = new Timer(20, this);  //Timer erzeugen

        //###########################################
        renderer = new Renderer();                //#
        jframe.add(renderer);                     //#
        //###########################################

        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //Festlegen, dass das Programm beim Schließen beendet wird.
        jframe.setResizable(false); //Festlegen, dass die Fenstergröße Nachtraeglich nicht mehr verändert werden kann.
        jframe.setSize(WIDTH, HEIGHT);  //Größe des Spielfeldes zuweisen
        jframe.setVisible(true);        //Sichtbarkeit des Spielfeldes
        jframe.setTitle("Flappy Cloud");    //Titel festlegen
        jframe.addMouseListener(this);     //Mouselistener zum jframe hinzugügen. "this" bezieht sich auf den in dieser Klasse erstelltem Listener
        jframe.addKeyListener(this);    //KeyListener zum jframe hinzufügen

        obstacles = new ArrayList<Rectangle>(); //Arraylist für Hindernisse erstellen

        cloud = new Rectangle(390, 390, 50, 30); //Spielerobjekt erstellen und Größe, sowie Koordinaten festlegen

        random = new Random();  //Random instanziieren (Wird später zum Erzeugen von Hindernissen benötigt)

        addObstacles(); //Methode zum Erzeugen von Hindernispaaren
        addObstacles();
        addObstacles();
        addObstacles();

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

    public void addObstacles(){
        int gap = 300; //Abstand zwischen den Hindernissen
        int width = 80; //Breite der Hindernisse
        int height = 50 + random.nextInt(300); //Höhe der Hindernisse festlegen. Minimum 50, Maximum 300. Random erzeugt, innerhalb des Bereichs

        //if(start){
            /**
             * Hinzufügen eines neuen Elements in die ArrayList "obstacles".
             * Video Minute 27, 31
             */
            obstacles.add(new Rectangle(WIDTH + width + obstacles.size() * 300, HEIGHT - height - 120, width, height)); //Unteres Hindernis, Wird außerhalb der Spielfeldbreite erzeugt. Min 27
            obstacles.add(new Rectangle(WIDTH + width + (obstacles.size() -1) * 300, 0, width, HEIGHT - height - gap)); //Oberes Hindernis

        //}else{
        //    obstacles.add(new Rectangle(obstacles.get(obstacles.size() -1).x + 600, HEIGHT -height - 120, width, height)); // Min 31
         //   obstacles.add(new Rectangle(obstacles.get(obstacles.size() -1).x, 0, width, HEIGHT - height - gap)); // Min 31
        //}

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
         * Game Over Darstellung, Spielbeginn
         */
        g.setColor(Color.white);
        g.setFont(new Font("Georgia", 1, 100));

        if(gameOver){
            g.drawString("Game Over", 75, HEIGHT / 2 - 50);
        }

        if(!hasGameStarted){
            g.drawString("Click to start", 100, HEIGHT / 2 - 50);
        }

        //Score als String auf die Spieloberfläche drawen
        g.setColor(Color.black);
        g.setFont(new Font("Arial", 1, 20));
        if(!gameOver && hasGameStarted){
            g.drawString("Points: " + String.valueOf(score), 10, 100);
        }

    }
    //###################################################

    //Main
    public static void main(String[] args) {
        flappyCloud = new FlappyCloud();    //Alternativ: FlappyCloud flappycloud = new FlappyCloud(); dafür das Field flappycloud löschen
    }

    public void jump(){

        if(gameOver){

            cloud = new Rectangle(WIDTH / 2 -10, HEIGHT / 2 - 10, 50, 30);
            obstacles.clear();
            yMotion = 0;
            score = 0;

            addObstacles();
//            //addObstacles(true);
//            addObstacles(true);
//            addObstacles(true);

            gameOver = false;
        }

        if(!hasGameStarted){
            hasGameStarted = true;
        }else if (!gameOver){
            if(yMotion > 0){
                yMotion = 0;
            }

            yMotion -= 10;
        }

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
                        addObstacles();
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

                //Score um 1 erhöhen
                if(obstacle.y == 0 && cloud.x + cloud.width / 2 > obstacle.x + obstacle.width / 2 - 5 && cloud.x + cloud.width / 2 < obstacle.x + obstacle.width / 2 + 10){  ///// MIN 54
                    score++;
                }

                //KP
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

    //Mouselistener-Methoden
    @Override
    public void mouseClicked(MouseEvent e) {
        jump(); //Wenn die Mouse zum Beginn des Spiels geklickt wird, um das Spiel zu starten, wird die Methode jump() erstmals aufgerufen
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    //Keylistener-Methoden
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    //Beim loslassen von Space soll die Methode Jump ebenfalls ausgeführt werden, muss anschließend im Jframe noch hinzugefügt werden
    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_SPACE){
            jump();
        }
    }
}

//-----------------------------
//
//package flappyCloud;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.*;
//import java.util.ArrayList;
//import java.util.Random;
//import javax.swing.Timer;
//
//public class FlappyCloud implements ActionListener, MouseListener, KeyListener {
//
//    //Fields
//    public static FlappyCloud flappyCloud;
//    public final int WIDTH = 800;
//    public final int HEIGHT = 800;
//
//    public Renderer renderer;
//
//    public Rectangle cloud;
//
//    public ArrayList<Rectangle> obstacles;
//
//    public Random random;
//
//
//    public int ticks,yMotion,score;
//
//    public boolean gameOver,hasGameStarted=false;
//
//
//    //Constructor
//    public FlappyCloud(){
//
//        JFrame jframe = new JFrame();
//        Timer timer = new Timer(20, this);
//
//        renderer=new Renderer();
//        jframe.add(renderer);
//
//        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        jframe.setResizable(false);
//        jframe.setSize(WIDTH, HEIGHT);
//        jframe.setVisible(true);
//        jframe.setTitle("Flappy Cloud");
//
//        obstacles=new ArrayList<>();
//
//        cloud=new Rectangle(WIDTH/2-10,HEIGHT/2-10 ,50,30);
//
//        random=new Random();
//
//        addObstacles(true);
//        addObstacles(true);
//        addObstacles(true);
//        addObstacles(true);
//
//        jframe.addMouseListener(this);
//        jframe.addKeyListener(this);
//
//        timer.start();
//
//    }
//
//
//    public void paintObstacles(Graphics g, Rectangle obstacle){
//        g.setColor(Color.YELLOW.darker());
//        g.fillRect(obstacle.x, obstacle.y, obstacle.width, obstacle.height);}
//
//
//    public void addObstacles(boolean start){
//        int gap=300,width=100,height=50+random.nextInt(300);
//
//        if(start){
//            obstacles.add(new Rectangle(WIDTH + width + obstacles.size() * 300,
//                    HEIGHT - height - 120, width, height));
//            obstacles.add(new Rectangle(WIDTH + width + (obstacles.size() -1) * 300,
//                    0, width, HEIGHT - height - gap));
//        }else{
//            obstacles.add(new Rectangle(obstacles.get(obstacles.size() -1).x + 600,
//                    HEIGHT-height-120,width,height));
//            obstacles.add(new Rectangle(obstacles.get(obstacles.size() -1).x,
//                    0,width,HEIGHT-height-gap));
//        }
//    }
//
//    public void repaint(Graphics g) {
//
//        g.setColor(Color.gray);
//        g.fillRect(0, 0, WIDTH, HEIGHT);
//
//        g.setColor(Color.orange);
//        g.fillRect(cloud.x , cloud.y ,cloud.width ,cloud.height);
//
//        g.setColor(Color.pink);
//        g.fillRect(0 ,HEIGHT-150 ,WIDTH ,150);
//
//
//        g.setColor(Color.GREEN);
//        g.fillRect(0 ,HEIGHT-150 ,WIDTH ,20);
//
//
//        for(Rectangle obstacles:obstacles){
//            paintObstacles(g,obstacles);}
//
//        if(gameOver){g.drawString("Game Over",75,(int)(HEIGHT/2.5));
//        }else if(!hasGameStarted){g.drawString("Click to start",100,(int)(HEIGHT/2.5));}
//
//        if(!gameOver && hasGameStarted){g.drawString(String.valueOf(score),WIDTH /2-25,(int)(100));}
//
//    }
//
//
//    public static void main(String[] args) {flappyCloud = new FlappyCloud(); }
//
//
//    public void jump(){
//
//        if(gameOver){
//
//            cloud=new Rectangle(WIDTH /2-10 ,
//                    HEIGHT /2-10 ,
//                    (int)(50),
//                    (int)(30));
//
//            obstacles.clear();
//
//            yMotion=score=0;
//
//            addObstacles(true);
//            addObstacles(true);
//            addObstacles(true);
//            addObstacles(true);
//
//            gameOver=false;
//        }
//
//        if(!hasGameStarted){hasGameStarted=true;}
//        else if (!gameOver){
//            if(yMotion>0){yMotion=0;}
//            yMotion-=10;
//        }
//    }
//
//
//    public void actionPerformed(ActionEvent e) {
//
//        int speed=10;
//        ticks++;
//
//        if(hasGameStarted){
//
//            for(int i=0;i<obstacles.size();i++){
//                Rectangle obstacle = obstacles.get(i);
//                obstacle.x -= speed;}
//
//            for (int i = 0; i < obstacles.size(); i++) {
//                Rectangle obstacle = obstacles.get(i);
//
//                if(obstacle.x + obstacle.width < 0){
//                    obstacles.remove(obstacle);
//
//                    if(obstacle.y == 0){
//                        addObstacles(false);
//                    }
//                }
//            }
//
//            if(ticks %2== 0 && yMotion <15){yMotion +=2;}
//
//            cloud.y+=yMotion;
//
//            for(Rectangle obstacle:obstacles){
//
//                if(obstacle.y == 0 && cloud.x + cloud.width /2 > obstacle.x + obstacle.width /2 -5
//                        && cloud.x + cloud.width /2 < obstacle.x+obstacle.width/2+10)
//                    score++;
//
//
//                if(obstacle.intersects(cloud)){gameOver=true;}}
//
//            if(cloud.y > HEIGHT-120 || cloud.y< 0 ){gameOver=true;}
//
//            renderer.repaint();
//        }}
//
//
//    public void mouseClicked(MouseEvent e) {jump();}
//    public void mousePressed(MouseEvent e){}
//    public void mouseReleased(MouseEvent e){}
//    public void mouseEntered(MouseEvent e){}
//    public void mouseExited(MouseEvent e){}
//
//
//    public void keyTyped(KeyEvent e){}
//
//
//    public void keyPressed(KeyEvent e) {}
//
//    public void keyReleased(KeyEvent e) {
//        if(e.getKeyCode() == KeyEvent.VK_SPACE){jump();}
//    }
//}
