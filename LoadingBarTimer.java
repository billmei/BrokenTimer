import java.util.Date;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class LoadingBarTimer extends JFrame {

    private static final int WIDTH = 200;
    private static final int HEIGHT = 25;
    private MainPanel mainPanel = new MainPanel();
    private Timer globalTimer = new Timer(1000, new TimerListener()); // updates every second
    private JProgressBar theBar = new JProgressBar(0, 1); // maximum value is overridden later

    public LoadingBarTimer () {
        int numberOfHours = Integer.parseInt(JOptionPane.showInputDialog(null, "Input the number of hours:"));
        int numberOfMinutes = Integer.parseInt(JOptionPane.showInputDialog(null, "Input the number of minutes:"));
        int numberOfSeconds = (numberOfMinutes * 60) + (numberOfHours * 3600);

        long startTime = System.currentTimeMillis();
        long endTime = startTime + (1000 * numberOfSeconds);
        int timeDelta = (int) (endTime - startTime); // milliseconds

        theBar.setMaximum(timeDelta);
        theBar.setStringPainted(true);

        System.out.println(startTime);
        System.out.println(endTime);
        System.out.println(timeDelta);

        add(mainPanel);
        mainPanel.setLayout(new GridLayout(0,1)); // gridlayout fills out the entire window
        mainPanel.add(theBar);
        mainPanel.setPreferredSize(new Dimension(WIDTH, HEIGHT));

        globalTimer.start();

        setAlwaysOnTop(true);
        setTitle("Timer");
        setDefaultCloseOperation(EXIT_ON_CLOSE); 
        pack(); 
        setVisible(true); 
    }

    public class TimerListener implements ActionListener {
        public void actionPerformed (ActionEvent e) {
            long currentTime = System.currentTimeMillis();
            System.out.println(currentTime);
            repaint();
        }

        public int returnNewTimeDelta () {
            return 2; // placeholder
        }
    }

    public class MainPanel extends JPanel {
        public void paintComponent (Graphics gc) {
            super.paintComponent(gc);
        }
    }

    public static void main(String[] args) {
        new LoadingBarTimer();
    }
}