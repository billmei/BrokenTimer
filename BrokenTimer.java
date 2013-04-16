import java.util.Date;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class BrokenTimer extends JFrame {
    private static final int WIDTH = 200;
    private static final int HEIGHT = 25;
    private MainPanel mainPanel = new MainPanel();
    private Timer globalTimer = new Timer(1000, new TimerListener()); // 1000 milliseconds = updates every second
    private JProgressBar theBar = new JProgressBar(0, 1); // maximum value is overridden later
    private long startTime = System.currentTimeMillis();
    private boolean alreadyAlerted = false; // changed to true once the timer beeps, so the alert pane only shows the first time.

    public BrokenTimer () {
        int numberOfHours = Integer.parseInt(JOptionPane.showInputDialog(null, "Input the number of hours:"));
        int numberOfMinutes = Integer.parseInt(JOptionPane.showInputDialog(null, "Input the number of minutes:"));
        int numberOfSeconds = (numberOfMinutes * 60) + (numberOfHours * 3600);

        long endTime = startTime + (1000 * numberOfSeconds);
        int timeDelta = (int) (endTime - startTime); // milliseconds

        theBar.setMaximum(timeDelta);
        theBar.setStringPainted(true);

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
            int currentDelta = (int) (currentTime - startTime);
            theBar.setValue(currentDelta);
            // check if we've reached 100%
            if (currentDelta > theBar.getMaximum()) {
                Toolkit.getDefaultToolkit().beep(); // operating system default beep sound
                if (!alreadyAlerted) {
                    // from http://stackoverflow.com/a/2125020
                    JOptionPane alertPane = new JOptionPane("Your time is up!", JOptionPane.ERROR_MESSAGE);
                    JDialog alertDialog = alertPane.createDialog("Your time is up!");
                    alertDialog.setAlwaysOnTop(true);
                    alertDialog.setVisible(true);
                    alreadyAlerted = true;
                }
            }
            repaint();
        }
    }

    public class MainPanel extends JPanel {
        public void paintComponent (Graphics gc) {
            super.paintComponent(gc);
        }
    }

    public static void main(String[] args) {
        new BrokenTimer();
    }
}