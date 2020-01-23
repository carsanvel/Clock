package ViewSwingImplementation;

import Model.TimePointer;
import View.ClockDisplay;
import View.FileImageLoader;
import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JPanel;

public class SwingClockDisplay extends JPanel implements Observer, ClockDisplay{

    private List<Listener> listeners;
    private double[] angles;
    private final int[] lengths = {50, 100, 150};
    private final int[] widths = {1, 3, 5};
    private final String[] names = {"Seconds", "Minutes", "Hours"};
    private final BufferedImage image;
    private final float w;
    private final float h;
    
    public SwingClockDisplay(double[] angles) {
        listeners = new ArrayList();
        this.angles = angles;
        image = new FileImageLoader().load("reloj.jpg");
        w = (float)(image.getWidth());
        h = (float)(image.getHeight());
        repaint();
        CompleteMouseListener mouseListener  = new CompleteMouseListener();
        addMouseListener(mouseListener);
        addMouseMotionListener(mouseListener);
    }
    
    @Override
    public void display(double[] angles) {
        this.angles = angles;
        repaint();
    }
    
    @Override
    public void paint(Graphics g) {
        g.clearRect(0, 0, getWidth(), getHeight());
        g.drawImage(image, 0, 0, newImageWidth(), newImageHeigth(), null);
        Graphics2D g2 = (Graphics2D)g;
        for (int i = 0; i < angles.length; i++) {
            g2.setStroke(new BasicStroke(widths[i]));
            g2.drawLine(oX(), oY(), oX() + getCorX(angles[i], lengths[i]), oY() + getCorY(angles[i], lengths[i]));
        }
    }
    
    public int oX() {
        return getWidth()/2;
    }
    
    public int oY() {
        return getHeight()/2;
    }
    
    private int getCorX(double angle, int length) {
        int val = (int)((w * (getWidth()/w)) / 2) - length;
        return (int)(val * Math.cos(angle));
    }
    
    private int getCorY(double angle, int length) {
        int val = (int)((w * (getWidth()/w)) / 2) - length;
        return (int)(val * Math.sin(angle));
    }

    @Override
    public void update(Observable o, Object arg) {
        repaint();
    }

    private int newImageWidth() {
        return (int)(w * (getWidth()/w));
    }

    private int newImageHeigth() {
        return  (int)(h * (getHeight()/h));
    }
    
    private class CompleteMouseListener implements MouseListener, MouseMotionListener {

        private boolean pressed = false;
        private int mouseXPosition;
        private int mouseYPosition;
        int pressedPointer;
        
        @Override
        public void mouseClicked(MouseEvent e) {
        }

        @Override
        public void mousePressed(MouseEvent e) {
            for (int i = angles.length - 1; i >= 1; i--) {
                if(isInRange(e.getX(), e.getY(), i)) {
                    pressed = true;
                    mouseXPosition = e.getX();
                    mouseYPosition = e.getY();
                    pressedPointer = i;
                    break;
                }
            }
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            pressed = false;
        }

        @Override
        public void mouseEntered(MouseEvent e) {
        }

        @Override
        public void mouseExited(MouseEvent e) {
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            if(pressed) {
                int length = (int) Math.sqrt(Math.pow(e.getX() - oX(), 2) + Math.pow(e.getY() - oY(), 2) - 1);
                double newAngle;
                if(e.getY() <= oY()) {
                    newAngle = Math.acos((double)(oX() - e.getX())/ length);
                } else {
                    newAngle = 2*Math.PI - Math.acos((double)(oX() - e.getX())/ length);
                }
                for (Listener listener : listeners) {
                    listener.movePointer(newAngle + Math.PI, names[pressedPointer]);
                }
            }
        }

        @Override
        public void mouseMoved(MouseEvent e) {
        }
        
    }
    
    private boolean isInRange(int x, int y, int index) {
        if(angles[index] == 3*Math.PI/2) {
            if(x < oX() + 5 && x > oX() - 5 && y < oY() && y >= oY() + getCorY(angles[index], lengths[index])) {
                return true;
            }
        }
        if(angles[index] == Math.PI/2) {
            if(x < oX() + 5 && x > oX() - 5 && y > oY() && y <= oY() + getCorY(angles[index], lengths[index])) {
                return true;
            }
        }
        double m = ((double)getCorY(angles[index], lengths[index]) / (double)getCorX(angles[index], lengths[index]));
        int estimedY = (int)(oY() + m * (x - oX()));
        System.out.println(y);
        System.out.println(estimedY);
        if(y < estimedY + 5 && y > estimedY - 5) {
            return true;
        }
        return false;
    }
    
    @Override
    public void addListener(Listener listener) {
        listeners.add(listener);
    }
}
