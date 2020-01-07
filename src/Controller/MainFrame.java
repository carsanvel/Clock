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
        TimePointer seconds = new TimePointer();
        display = new SwingClockDisplay(seconds);
        seconds.add(display);
        
        getContentPane().add(display);
    }
    
    public void execute() {
        setVisible(true);
    }
}
