package csusb.cse541.william.cse541robotics;

/**
 * Created by William on 6/8/2015.
 */

public class Timer {
    public Timer () {
    }
    public void start () {
        startTime = System.currentTimeMillis ();
    }
    /* Return the time duration from last call in seconds */
    long getDuration () {
        short milliToSecsFactor = 1000;
        endTime = System.currentTimeMillis();
        long time = endTime - startTime;
        //startTime = endTime;
        return time;// / milliToSecsFactor ;
    }

    private long startTime;
    private long endTime;

}
