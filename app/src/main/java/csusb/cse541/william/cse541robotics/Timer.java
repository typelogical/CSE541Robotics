package csusb.cse541.william.cse541robotics;

/**
 * Created by William on 6/8/2015.
 *
 */

public class Timer {
    public Timer () {}
    public void start () {
        startTime = System.currentTimeMillis ();
    }

    /* Return the time duration from last call in milliseconds */
    long getDuration () {
        long endTime = System.currentTimeMillis();
        return endTime - startTime;
    }

    private long startTime;

}
