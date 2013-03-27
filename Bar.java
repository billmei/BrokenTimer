import java.awt.*;
import javax.swing.*;

public class Bar {
    protected int currentTimeVariable; // current time counts in seconds
    protected int startTimeVariable;
    protected int endTimeVariable;
    protected int widthOfWindowVariable;

    public Bar (int currentTime, int startTime, int endTime, int widthOfWindow) {
        currentTimeVariable = currentTime;
        startTimeVariable = startTime;
        endTimeVariable = endTime;
        // widthOfWindowVariable = widthOfWindow;
    }

    public void draw(Graphics gc) {
        gc.fillRect(0, 0, findAppropriateWidth(currentTimeVariable, startTimeVariable, endTimeVariable, widthOfWindowVariable), 25);
        System.out.println(currentTimeVariable);
    }

    public void updateCurrentTime () {
        
    }

    public void setStartTime(long timeYouStarted) {
        startTimeVariable = (int) timeYouStarted;
    }

    public void setEndTime(int totalNumberOfSeconds) {
        endTimeVariable = totalNumberOfSeconds;
    }



    public int findAppropriateWidth (int currentTime, int startTime, int endTime, int widthOfWindow) {
        // int duration = endTime - startTime;
        // double percentFull = (double) currentTime / (double) duration; // percent of the bar that's should be full.

        // System.out.println(percentFull);

        // if ((percentFull / 0.05) > 1) {
        //     return (int) Math.ceil(widthOfWindow * percentFull);
        // } else {
        //     return 0;
        // }

        int duration = endTime - startTime;

        return currentTime * (widthOfWindow / duration);
    }

}