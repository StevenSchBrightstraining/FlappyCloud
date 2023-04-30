package flappyCloud;

import java.awt.*;

public class Cloud {

    public Rectangle cloud;
    public int xPos = 800 / 2 - 10;
    public int yPos = 800 / 2 - 10;

    public final int WIDTH = 50;
    public final int HEIGHT = 30;

    public Cloud(){
        cloud = new Rectangle(xPos, yPos, WIDTH, HEIGHT);
    }

    public int getxPos() {
        return xPos;
    }

    public int getyPos() {
        return yPos;
    }

    public int getWIDTH() {
        return WIDTH;
    }

    public int getHEIGHT() {
        return HEIGHT;
    }
}
