import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class RandomCircles extends Frame {
    private Random rnd;
    private Runner r;

    public class Runner extends Thread {
        private Graphics gr;

        public Runner(Graphics g) {
            this.gr  =g;
        }

        public void setGraphics(Graphics g) {
            this.gr = g;
        }

        public void run() {
            try {
                while(true) {
                    Thread.sleep(30);
                    paint(this.gr);
                }
            } catch(Exception ex) {
                System.out.println("Exception occurred : " + ex.toString());
            }
        }
    }

    public void paint(Graphics g) {
        g.setColor(this.getRandomColor());
        int x1 = this.rnd.nextInt(this.getWidth());
        int y1 = this.rnd.nextInt(this.getHeight());

        int r = this.rnd.nextInt(100);

        g.drawOval(x1,y1,r,r);
        g.fillOval(x1,y1,r,r);
        
    }

    private Color getRandomColor() {
        return new Color(this.rnd.nextInt(255),this.rnd.nextInt(255),this.rnd.nextInt(255));
    }

    public RandomCircles() {
        this.rnd = new Random();

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });

        this.r = new Runner(this.getGraphics());
    }

    public void startAnimation() {
        this.r.setGraphics(this.getGraphics());
        this.r.start();
    }

    public static void main(String[] args) {
        RandomCircles rc = new RandomCircles();

        rc.setTitle("Random Circles");
        rc.setSize(800,600);
        rc.setVisible(true);

        rc.startAnimation();
    }
}