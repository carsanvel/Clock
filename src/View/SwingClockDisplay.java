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
    private final int ox = getWidth()/2;
    private final int oy = getHeight()/2;
    
    public SwingClockDisplay(TimePointer seconds) {
        this.seconds = seconds;
        image = FileImageLoader.load("reloj.jpg");
        w = (float)(image.getWidth());
        h = (float)(image.getHeight());
        repaint();
    }
    
    @Override
    public void paint(Graphics g) {
        float w = (float)(image.getWidth());
        float h = (float)(image.getHeight());
        g.clearRect(0, 0, getWidth(), getHeight());
        g.drawImage(image, 0, 0, (int)(w * (getWidth()/w)), (int)(h * (getHeight()/h)), null);
        g.drawLine(getWidth()/2, getHeight()/2, coordinateX(), coordinateY());
    }
    
    private int coordinateX() {
        int val = (int)((w * (getWidth()/w)) / 2);
        return (int)(val * Math.cos(seconds.getAngle()));
    }
    
    private int coordinateY() {
        int val = (int)((w * (getWidth()/w)) / 2);
        return (int)(val * Math.sin(seconds.getAngle())) + 25;
    }

    @Override
    public void update(Observable o, Object arg) {
        repaint();
    }
}
