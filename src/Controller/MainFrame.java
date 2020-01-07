package Controller;

import Model.TimePointer;
import View.SwingClockDisplay;
import javax.swing.JFrame;

public class MainFrame extends JFrame{
    
    SwingClockDisplay display;
    
    public MainFrame() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(700, 700);
        setTitle("Clock");
        setLocationRelativeTo(null);
        TimePointer seconds = new TimePointer(1000);
        TimePointer minutes = new TimePointer(60000);
        TimePointer hours = new TimePointer(720000);
        display = new SwingClockDisplay(seconds, minutes, hours);
        seconds.add(display);
        
        getContentPane().add(display);
    }
    
    public void execute() {
        setVisible(true);
    }
}
