package View;


public interface ClockDisplay {

    void display(double[] angles);
    void addListener(Listener listener);
    
    public interface Listener {
        void movePointer(double newAngle, String pointerName);
    }
    
}
