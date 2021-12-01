import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.ImageObserver;
import java.text.AttributedCharacterIterator;

public class MainWindow extends JFrame {

    public static void main(String[] args){
        new MainWindow().setVisible(true);
    }

    Point whereToDraw;

    public MainWindow(){
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(700,700);
        setLocationRelativeTo(null);

        setFocusable(true);

        JPanel panel = new JPanel();
        panel.setBounds(0,0,600,600);

        setContentPane(panel);
        whereToDraw = new Point(300,300);
        JButton runningButton = new JButton("run");
        KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
        manager.addKeyEventDispatcher(new KeyEventDispatcher() {
            @Override
            public boolean dispatchKeyEvent(KeyEvent e) {
                return false;
            }
        });

        runningButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                int x = (int)Math.floor(Math.random()*501);
                int y = (int)Math.floor(Math.random()*501);
                runningButton.setLocation(x,y);
            }
        });
        JTextArea area = new JTextArea(String.format("%f %f",whereToDraw.getX(),whereToDraw.getY()));

        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                whereToDraw.setLocation(e.getPoint());
                area.setText(String.format("%f %f",whereToDraw.getX(),whereToDraw.getY()));
            }
        });


        addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if(e.getKeyChar() == 'o' || e.getKeyChar() == 'O'){
                    new Oval(whereToDraw.x,whereToDraw.y).setVisible(true);
                }
            }
        });

        panel.add(area);
        panel.add(runningButton);
        panel.setLayout(new GridBagLayout());

    }


}
