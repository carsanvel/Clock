package Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Observer;
import java.util.Timer;
import java.util.TimerTask;


public class TimePointer {

    private final List<Observer> observers = new ArrayList<>();
    private final static double dt = 0.1;
    private final static double dAngle = (Math.PI*2) / 60;
    private double angle;

    public TimePointer() {
        angle = 0.0;
        Timer timer  = new Timer();
        timer.schedule(task(), 0, 1000);
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
        System.out.println(angle);
    }
    
    private void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(null, null);
        }
    }
    
    
}
