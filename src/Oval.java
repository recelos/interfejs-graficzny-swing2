import javax.swing.*;
import java.awt.*;

public class Oval extends JFrame{

    private final int x;
    private final int y;

    public Oval(int x, int y){
        this.x = x;
        this.y = y;
    }
    @Override
    public void paint(Graphics g){
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.fillOval(x, y, 30,30);
    }

}
