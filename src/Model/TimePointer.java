package Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Observer;
import java.util.Timer;
import java.util.TimerTask;


public class TimePointer {

    private final List<Observer> observers = new ArrayList<>();
    private final int interval;
    private final static double dAngle = (Math.PI*2) / 60;
    private double angle;

    public TimePointer(int interval) {
        this.interval = interval;
        angle = 3*Math.PI/2;
        Timer timer  = new Timer();
        timer.schedule(task(), 0, interval);
    }
    
    public double getAngle() {
        return angle;
    }
    
    public void add(Observer observer) {
        observers.add(observer);
    }

    private TimerTask task() {
        return new TimerTask() {

            @Override
            public void run() {
                increaseAngle();
                notifyObservers();
            }

            
        };
    }
    
    private void increaseAngle() {
        angle = (angle + dAngle) % (Math.PI*2);
    }
    
    private void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(null, null);
        }
    }
    
    
}
