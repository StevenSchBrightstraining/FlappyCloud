package flappyCloud;

import javax.swing.JPanel;
import javax.swing.*;
import java.awt.*;

public class Renderer extends JPanel {
    private static final long serialVersionUID = 1L;

    //Video min. 13
    protected void paintComponent(Graphics g){
        super.paintComponent(g);

        FlappyCloud.flappyCloud.repaint(g);
    }

}
