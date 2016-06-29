package fisher.snakegame;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class SnakeGame extends JPanel implements ActionListener{

    public static final int SCALE = 32;
    public static final int WIDTH = 20;
    public static final int HEIGHT = 20;
    public static final int SPEED = 5;

    Apple a = new Apple((int) Math.random()*WIDTH, (int) Math.random()*HEIGHT);
    Snake s = new Snake(10, 10, 9, 10);
    Timer t = new Timer(1000/SPEED, this);

    public SnakeGame() {
        t.start();
        addKeyListener(new Keyboard());
        setFocusable(true);
    }


    public void paint (Graphics g) {
        g.setColor(color (15, 15, 70));
        g.fillRect(0, 0, WIDTH*SCALE, HEIGHT*SCALE);
        g.setColor(color (15, 15, 90));

        for (int i = 0; i < WIDTH*SCALE; i+=SCALE) {
            g.drawLine(i, 0, i, HEIGHT*SCALE);
        }
        for (int j = 0; j < HEIGHT*SCALE; j+=SCALE) {
            g.drawLine(0, j, WIDTH*SCALE , j);
        }
        for (int i = 0; i < s.length; i++) {
            g.setColor(color(15, 15, 170));
            g.fillRect(s.snakeX[i]*SCALE+1, s.snakeY[i]*SCALE+1, SCALE-1, SCALE-1);
        }
        g.setColor(color(255, 0, 0));
        g.fillRect(a.posX*SCALE+1, a.posY*SCALE+1, SCALE-1, SCALE-1);
    }

    public Color color(int red, int green, int blue) {
        return new Color(red, blue, green);
    }

    public static void main(String[] args) {
        JFrame f = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setResizable(false);
        f.setSize(WIDTH*SCALE + 8, HEIGHT*SCALE + 32);
        f.setLocationRelativeTo(null);
        f.add(new SnakeGame());
        f.setVisible(true);
    }

    public void actionPerformed(ActionEvent arg0) {
        s.move();
        if ((s.snakeX[0] == a.posX) & (s.snakeY[0] == a.posY)) {
            a.setRandomPosition();
            s.length++;
        }
        for (int i = 1; i < s.length; i++) {
            if ((s.snakeX[i] == a.posX) & (s.snakeY[i] == a.posY)) {
                a.setRandomPosition();
            }
        }
        repaint();
    }

    private class Keyboard extends KeyAdapter {
        public void keyPressed(KeyEvent kEvt) {
            int key = kEvt.getKeyCode();

            if ((key == KeyEvent.VK_RIGHT) & s.direction != 2) s.direction = 0;
            if ((key == KeyEvent.VK_DOWN) & s.direction != 3) s.direction = 1;
            if ((key == KeyEvent.VK_LEFT) & s.direction != 0) s.direction = 2;
            if ((key == KeyEvent.VK_UP) & s.direction != 1) s.direction = 3;

        }
    }
}
