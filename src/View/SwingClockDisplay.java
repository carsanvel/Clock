package View;

import Model.TimePointer;
import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JPanel;

public class SwingClockDisplay extends JPanel implements Observer{

    private TimePointer seconds;
    private TimePointer minutes;
    private TimePointer hours;
    private final BufferedImage image;
    private final float w;
    private final float h;
    private final int ox;
    private final int oy;
    
    public SwingClockDisplay(TimePointer seconds, TimePointer minutes, TimePointer hours) {
        this.seconds = seconds;
        this.minutes = minutes;
        this.hours = hours;
        image = FileImageLoader.load("reloj.jpg");
        w = (float)(image.getWidth());
        h = (float)(image.getHeight());
        ox = getWidth();
        oy = getHeight();
        repaint();
    }
    
    @Override
    public void paint(Graphics g) {
        float w = (float)(image.getWidth());
        float h = (float)(image.getHeight());
        g.clearRect(0, 0, getWidth(), getHeight());
        g.drawImage(image, 0, 0, (int)(w * (getWidth()/w)), (int)(h * (getHeight()/h)), null);
        g.drawLine(getWidth()/2, getHeight()/2, getWidth()/2 + getXSec(), getHeight()/2 + getYSec());
        Graphics2D g2 = (Graphics2D)g;
        g2.setStroke(new BasicStroke(3));
        g.drawLine(getWidth()/2, getHeight()/2, getWidth()/2 + getXMin(), getHeight()/2 + getYMin());
        g2.setStroke(new BasicStroke(5));
        g.drawLine(getWidth()/2, getHeight()/2, getWidth()/2 + getXHour(), getHeight()/2 + getYHour());
    }
    
    private int getXSec() {
        int val = (int)((w * (getWidth()/w)) / 2) - 50;
        return (int)(val * Math.cos(seconds.getAngle()));
    }
    
    private int getYSec() {
        int val = (int)((w * (getWidth()/w)) / 2) - 50;
        return (int)(val * Math.sin(seconds.getAngle()));
    }
    
    private int getXMin() {
        int val = (int)((w * (getWidth()/w)) / 2) - 100;
        return (int)(val * Math.cos(minutes.getAngle()));
    }
    
    private int getYMin() {
        int val = (int)((w * (getWidth()/w)) / 2) - 100;
        return (int)(val * Math.sin(minutes.getAngle()));
    }
    
    private int getXHour() {
        int val = (int)((w * (getWidth()/w)) / 2) - 150;
        return (int)(val * Math.cos(hours.getAngle()));
    }
    
    private int getYHour() {
        int val = (int)((w * (getWidth()/w)) / 2) - 150;
        return (int)(val * Math.sin(hours.getAngle()));
    }

    @Override
    public void update(Observable o, Object arg) {
        repaint();
    }
}
