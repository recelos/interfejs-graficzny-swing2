import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainWindow extends JFrame {

    Point whereToDraw = new Point(300,300);

    public MainWindow(){
        this("");
    }

    public MainWindow(String title){

        setTitle(title);
        setupFrame();

        JPanel panel = new JPanel();
        panel.setBounds(0,0,600,600);
        panel.setLayout(null);
        implementKeyDrawing();

        setContentPane(panel);

        JButton runningButton = new JButton("run");
        implementRunningButton(runningButton);
        runningButton.setBounds(300,300,70,20);


        JLabel locationLabel = new JLabel("Lokalizacja w której pojawi się figura:");
        JTextArea locationArea = new JTextArea();
        locationArea.setText(String.format("x: %d, y: %d",(int)whereToDraw.getX(),(int)whereToDraw.getY()));



        Box locationBox = Box.createHorizontalBox();
        locationBox.add(locationLabel);
        locationBox.add(locationArea);


        implementLocationChangeOnClick(panel, locationArea);

        JLabel hintLabel = new JLabel("o - koło k - kwadrat");

        add(runningButton);
        panel.add(locationBox);
        panel.add(hintLabel);
        locationBox.setBounds(175,575,300,20);
        hintLabel.setBounds(275,600,200,20);
    }

    /**
     * Naciśnięcie przycisku myszy zmieni lokalizacje, w której pojawi się następna narysowana figura.
     * Zaktualizuje baner pokazujący zaznaczoną lokalizacje.
     * @param panel panel do którego dodawany jest słuchacz
     * @param area pole z tekstem aktualizowane po wciśnięciu myszy
     */
    private void implementLocationChangeOnClick(JPanel panel, JTextArea area) {
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                whereToDraw.setLocation(e.getPoint());
                area.setText(String.format("x: %d, y: %d",(int)whereToDraw.getX(),(int)whereToDraw.getY()));
            }
        });
    }

    /**
     * Po naciśnięciu klawisza (k dla kwadratu i o dla koła) na panelu zostanie narysowana
     * odpowiednia figura
     */
    private void implementKeyDrawing() {
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if(e.getKeyChar() == 'o' || e.getKeyChar() =='O'){
                    drawCircle(whereToDraw);
                }
                else if(e.getKeyChar() == 'k' || e.getKeyChar() == 'K'){
                    drawSquare(whereToDraw);
                }
            }
        });
    }

    /**
     * Implementacja logiki uciekającego przycisku.
     *
     * W przypadku najechania myszą na przycisk, ten pojawi się w losowym
     * miejscu.
     *
     * Możliwe jest najechanie na przycisk z lewego górnego boku (wtedy nie ucieknie).
     *
     * @param runningButton uciekający przycisk
     */
    private void implementRunningButton(JButton runningButton) {
        runningButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                if(getMousePosition().getY() > runningButton.getY() + (float)runningButton.getWidth()/2 + 5  || getMousePosition().getX() > runningButton.getX() + (float)runningButton.getHeight()/2 + 10){
                    super.mouseEntered(e);
                    int x = (int)Math.floor(Math.random()*501);
                    int y = (int)Math.floor(Math.random()*501);
                    runningButton.setLocation(x,y);
                }
            }
        });
    }

    /**
     * Ustawia parametry okna
     */
    private void setupFrame() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(700,700);
        setLocationRelativeTo(null);
        setResizable(false);
        setFocusable(true);
    }


    /**
     * Rysuje koło w określonym punkcie
     * @param p punkt określający lokalizacje do rysowania
     */
    private void drawCircle(Point p){
        Graphics2D g2d = (Graphics2D) getGraphics().create();
        g2d.fillOval((int)p.getX(),(int)p.getY(),30,30);
    }
    /**
     * Rysuje kwadrat w określonym punkcie
     * @param p punkt określający lokalizacje do rysowania
     */
    private void drawSquare(Point p){
        Graphics2D g2d = (Graphics2D) getGraphics().create();
        g2d.fillRect((int)p.getX(),(int)p.getY(),30,30);
    }

    @Override
    public void paintComponents(Graphics g) {
        super.paintComponents(g);
    }
}
