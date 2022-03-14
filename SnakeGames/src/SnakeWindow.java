import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SnakeWindow extends JPanel implements ActionListener {
    public static JFrame jFrame;
    public static  int speed=10;
    public static final int SCALE = 32;
    public static final int WIDTH = 16;
    public static final int HEIGHT = 16;
    Timer timer=new Timer(1000/speed,this);
    Snake s = new Snake(5, 6, 5, 5);
public SnakeWindow(){
    timer.start();
    addKeyListener(new KeyBoard());
setFocusable(true);
}

    @Override
    public synchronized void addKeyListener(KeyListener l) {
        super.addKeyListener(l);
    }

    Apple apple=new Apple(Math.abs((int) (Math.random() * SnakeWindow.WIDTH - 1)),Math.abs((int) (Math.random() * SnakeWindow.HEIGHT - 1)));
    public void paint(Graphics g) {
        g.setColor(Color.ORANGE);
        g.fillRect(0, 0, WIDTH * SCALE, HEIGHT * SCALE);

        for (int x = 0; x < WIDTH * SCALE; x += SCALE) {
            g.setColor(Color.black);
            g.drawLine(x, 0, x, HEIGHT * SCALE);

        }

        for (int y = 0; y < HEIGHT * SCALE; y += SCALE) {

            g.setColor(Color.black);
            g.drawLine(0, y, WIDTH * SCALE, y);
        }
        for (int i = 0; i <s.lenght ; i++) {
           g.setColor(Color.red);
           g.fillOval(apple.posX*SCALE+1,apple.posY*SCALE+1,SCALE-1,SCALE-1);
            g.setColor(Color.green);
        g.fillRect(s.sX[i]*SCALE+1,s.sY[i]*SCALE+1,SCALE-1,SCALE-1);
        }
    }

    public static void main(String[] args) {
        jFrame = new JFrame("SnakeMasha");
        jFrame.add(new SnakeWindow());
        jFrame.setSize(WIDTH * SCALE + 16, HEIGHT * SCALE);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.setVisible(true);
        jFrame.setResizable(false);
        jFrame.setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        s.move();
        if((s.sX[0]==apple.posX)&&(s.sY[0]==apple.posY)){
            apple.setRandomPosition();
        s.lenght++;
        }
        repaint();
    }
public class KeyBoard extends KeyAdapter{
    public void keyPressed(KeyEvent event) {
        int key=event.getKeyCode();

    if(key==KeyEvent.VK_UP) s.direction=0;
        if(key==KeyEvent.VK_DOWN) s.direction=2;
        if(key==KeyEvent.VK_LEFT) s.direction=3;
        if(key==KeyEvent.VK_RIGHT) s.direction=1;
    }
}
}

