package View;

import Model.TimePointer;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JPanel;

public class SwingClockDisplay extends JPanel implements Observer{

    private TimePointer seconds;
    private final BufferedImage image;
    private final float w;
    private final float h;
    private final int ox;
    private final int oy;
    
    public SwingClockDisplay(TimePointer seconds) {
        this.seconds = seconds;
        image = FileImageLoader.load("reloj.jpg");
        w = (float)(image.getWidth());
        h = (float)(image.getHeight());
        ox = getWidth();
        oy = getHeight();
        repaint();
    }
    
    @Override
    public void paint(Graphics g) {
        System.out.println(seconds.getAngle());
        float w = (float)(image.getWidth());
        float h = (float)(image.getHeight());
        g.clearRect(0, 0, getWidth(), getHeight());
        g.drawImage(image, 0, 0, (int)(w * (getWidth()/w)), (int)(h * (getHeight()/h)), null);
        g.drawLine(getWidth()/2, getHeight()/2, getWidth()/2 + coordinateX(), getHeight()/2+ coordinateY());
    }
    
    private int coordinateX() {
        int val = (int)((w * (getWidth()/w)) / 2) - 50;
        return (int)(val * Math.cos(seconds.getAngle()));
    }
    
    private int coordinateY() {
        int val = (int)((w * (getWidth()/w)) / 2) - 50;
        return (int)(val * Math.sin(seconds.getAngle()));
    }

    @Override
    public void update(Observable o, Object arg) {
        repaint();
    }
}
