package flappyCloud;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Random;

public class Obstacles {

    //Fields
    private ArrayList<Rectangle> obstacles;
    private Random random;

    //Constructor
    public Obstacles() {
        obstacles = new ArrayList<Rectangle>();
        random = new Random();
        addObstacles(true);
        addObstacles(true);
        addObstacles(true);
        addObstacles(true);
    }

    /**
     * Methode zum Erstellen der Hindernisse
     * @param g
     * @param obstacle
     */
    public void paintObstacles(Graphics g, Rectangle obstacle) {
        g.setColor(Color.YELLOW.darker()); // Farbe der Hindernisse festlegen
        g.fillRect(obstacle.x, obstacle.y, obstacle.width, obstacle.height); // Hindernisse befüllen
    }

    public void addObstacles(boolean start) {
        int gap = 300; // Abstand zwischen den Hindernissen
        int width = 100; // Breite der Hindernisse
        int height = 50 + random.nextInt(300); // Höhe der Hindernisse festlegen. Minimum 50, Maximum 300. Random erzeugt, innerhalb des Bereichs

        if (start) {
            /**
             * Hinzufügen eines neuen Elements in die ArrayList "obstacles".
             * Video Minute 27, 31
             */
            obstacles.add(new Rectangle(800 + width + obstacles.size() * 300,
                    800 - height - 120, width, height)); // Unteres Hindernis, Wird außerhalb der Spielfeldbreite erzeugt. Min 27
            obstacles.add(new Rectangle(800 + width + (obstacles.size() - 1) * 300, 0, width,
                    800 - height - gap)); // Oberes Hindernis
        } else {
            obstacles.add(new Rectangle(obstacles.get(obstacles.size() - 1).x + 600, 800 - height - 120,
                    width, height)); // Min 31
            obstacles.add(new Rectangle(obstacles.get(obstacles.size() - 1).x, 0, width,
                    800 - height - gap)); // Min 31
        }

    }

    public ArrayList<Rectangle> getObstacles() {
        return obstacles;
    }
}
