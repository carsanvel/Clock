package Presenter;

import Model.TimePointer;
import View.ClockDisplay;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

public class ClockPresenter implements Observer{

    private final ClockDisplay clockDisplay;
    private final Map<String, TimePointer> pointers;

    public ClockPresenter(ClockDisplay clockDisplay) {
        this.clockDisplay = clockDisplay;
        pointers = new HashMap<>();
        clockDisplay.addListener(new ClockDisplay.Listener() {

            @Override
            public void movePointer(double newAngle, String pointerName) {
                pointers.get(pointerName).setAngle(newAngle);
                double[] angles = new double[3];
                angles[0] = pointers.get("Seconds").getAngle();
                angles[1] = pointers.get("Minutes").getAngle();
                angles[2] = pointers.get("Hours").getAngle();
                clockDisplay.display(angles);
            }
        });
    }
    
    @Override
    public void update(Observable o, Object arg) {
        double[] angles = new double[3];
        angles[0] = pointers.get("Seconds").getAngle();
        angles[1] = pointers.get("Minutes").getAngle();
        angles[2] = pointers.get("Hours").getAngle();
        clockDisplay.display(angles);
    }
    
    public void addPointer(TimePointer pointer, String name) {
        pointers.put(name, pointer);
    }
    
}
