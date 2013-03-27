import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class LoadingBarTimer extends JFrame {

    private static final int TIME_WARP = 1; // e.g. 2 = 2x speed
    private static final int WIDTH = 200;
    private static final int HEIGHT = 25;
    private MainPanel mainPanel = new MainPanel();
    private TimerListener clockCounter = new TimerListener();
    private Timer timer = new Timer(1000 / TIME_WARP, clockCounter);
    private long theCurrentTime = System.currentTimeMillis();
    private JProgressBar theBar = new JProgressBar();

    public LoadingBarTimer () {
        long numberOfHours = Long.parseLong(JOptionPane.showInputDialog(null, "Input the number of hours:"));
        long numberOfMinutes = Long.parseLong(JOptionPane.showInputDialog(null, "Input the number of minutes:"));
        long numberOfSeconds = (numberOfMinutes * 60) + (numberOfHours * 3600);

        long timeYouStarted = System.currentTimeMillis();

        theBar.setMaximum((int) (timeYouStarted + numberOfSeconds));

        timer.addActionListener(clockCounter);

        add(mainPanel, BorderLayout.CENTER);
        mainPanel.add(theBar);

        System.out.println(theCurrentTime);

        mainPanel.setPreferredSize(new Dimension(WIDTH, HEIGHT));

        setTitle("Timer");
        setDefaultCloseOperation(EXIT_ON_CLOSE); 
        pack(); 
        setVisible(true); 
    }

    public class TimerListener implements ActionListener {
        public void actionPerformed (ActionEvent e) {
            theBar.setValue((int) theCurrentTime*1000);
            repaint();
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