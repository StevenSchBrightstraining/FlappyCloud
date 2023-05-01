package flappyCloud;

import java.awt.*;

public class Cloud {

    public Rectangle container;
    public int xPos = 800 / 2 - 10;
    public int yPos = 800 / 2 - 10;

    public final int WIDTH = 20;
    public final int HEIGHT = 20;

    public Cloud(){
        container = new Rectangle(xPos, yPos, WIDTH, HEIGHT);
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

    public Rectangle getContainer() {
        return container;
    }

    public void setxPos(int xPos) {
        this.xPos = xPos;
    }

    public void setyPos(int yPos) {
        this.yPos = yPos;
    }
}
