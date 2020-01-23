package Clock;

import Model.TimePointer;
import Presenter.ClockPresenter;
import ViewSwingImplementation.SwingClockDisplay;
import java.util.List;
import javax.swing.JFrame;

public class MainFrame extends JFrame {
    
    SwingClockDisplay display;
    List<TimePointer> pointers;
    
    
    public MainFrame() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(700, 700);
        setTitle("Clock");
        setLocationRelativeTo(null);
        TimePointer seconds = new TimePointer(1000);
        TimePointer minutes = new TimePointer(60000);
        TimePointer hours = new TimePointer(720000);
        double[] angles = new double[3];
        angles[0] = seconds.getAngle();
        angles[1] = minutes.getAngle();
        angles[2] = hours.getAngle();
        display = new SwingClockDisplay(angles);
        ClockPresenter presenter = new ClockPresenter(display);
        presenter.addPointer(seconds, "Seconds");
        presenter.addPointer(minutes, "Minutes");
        presenter.addPointer(hours, "Hours");
        seconds.add(presenter);
        minutes.add(presenter);
        hours.add(presenter);
        
        getContentPane().add(display);
    }
    
    public void execute() {
        setVisible(true);
    }


}
