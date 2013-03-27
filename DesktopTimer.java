import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class DesktopTimer extends JFrame {

    private static final int TIME_WARP = 1; // e.g. 2 = 2x speed
    private static final int WIDTH = 200;
    private static final int HEIGHT = 25;
    private MainPanel mainPanel = new MainPanel();
    private TimerListener clockCounter = new TimerListener();

    private Bar theBar = new Bar(1, 0, 1, WIDTH);

    public DesktopTimer () {
        int numberOfHours = Integer.parseInt(JOptionPane.showInputDialog(null, "Input the number of hours:"));
        int numberOfMinutes = Integer.parseInt(JOptionPane.showInputDialog(null, "Input the number of minutes:"));
        int numberOfSeconds = (numberOfMinutes * 60) + (numberOfHours * 3600);

        long timeYouStarted = System.currentTimeMillis();

        theBar.setStartTime(timeYouStarted);
        theBar.setEndTime(numberOfSeconds);

        timer.addActionListener(clockCounter);

        add(mainPanel, BorderLayout.CENTER);
        mainPanel.setPreferredSize(new Dimension(WIDTH, HEIGHT));

        setTitle("Timer");
        setDefaultCloseOperation(EXIT_ON_CLOSE); 
        pack(); 
        setVisible(true); 
        timer.start();
    }


    public class TimerListener implements ActionListener {
        public void actionPerformed (ActionEvent e) {
            theBar.updateCurrentTime();
            repaint();
        }
    }

    public class MainPanel extends JPanel {
        public void paintComponent (Graphics gc) {
            super.paintComponent(gc);
            theBar.draw(gc);
        }
    }

    public static void main(String[] args) {
        new DesktopTimer();
    }
}