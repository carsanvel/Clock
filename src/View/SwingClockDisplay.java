package View;

import Model.TimePointer;
import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JPanel;

public class SwingClockDisplay extends JPanel implements Observer{

    private List<TimePointer> pointers;
    private TimePointer seconds;
    private TimePointer minutes;
    private TimePointer hours;
    private final BufferedImage image;
    private final float w;
    private final float h;
    
    public SwingClockDisplay(TimePointer seconds, TimePointer minutes, TimePointer hours) {
        this.seconds = seconds;
        this.minutes = minutes;
        this.hours = hours;
        pointers = new ArrayList<>();
        pointers.add(seconds);
        pointers.add(minutes);
        pointers.add(hours);
        image = FileImageLoader.load("reloj.jpg");
        w = (float)(image.getWidth());
        h = (float)(image.getHeight());
        repaint();
    }
    
    @Override
    public void paint(Graphics g) {
        int ox = getWidth()/2;
        int oy = getHeight()/2;
        g.clearRect(0, 0, getWidth(), getHeight());
        int newW = newImageWidth();
        int newH = newImageHeigth();
        g.drawImage(image, 0, 0, newW, newH, null);
        Graphics2D g2 = (Graphics2D)g;
        for (TimePointer pointer : pointers) {
            g2.setStroke(new BasicStroke(pointer.getWidth()));
            g.drawLine(ox, oy, ox + getCorX(pointer), oy + getCorY(pointer));
        }
    }
    
    private int getCorX(TimePointer pointer) {
        int val = (int)((w * (getWidth()/w)) / 2) - (200 - pointer.getLength());
        return (int)(val * Math.cos(pointer.getAngle()));
    }
    
    private int getCorY(TimePointer pointer) {
        int val = (int)((w * (getWidth()/w)) / 2) - (200 - pointer.getLength());
        return (int)(val * Math.sin(pointer.getAngle()));
    }

    @Override
    public void update(Observable o, Object arg) {
        repaint();
    }

    private int newImageWidth() {
        return (int)(w * (getWidth()/w));
    }

    private int newImageHeigth() {
        return (int)(h * (getHeight()/h));
    }
}
