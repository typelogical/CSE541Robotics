package csusb.cse541.william.cse541robotics;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.SeekBar;

/**
 * Created by William on 6/8/2015.
 *
 */

public class ClickListener implements View.OnClickListener, SeekBar.OnSeekBarChangeListener {
    private WifiController wifiCtrl = null;
    private String WifiIP = Constants.REMOTE_IP_ADDRESS;
    private int speed = 255;
    Activity act = null;
    Timer timer;
    Timer play_timer;
    Route route;
    private boolean recording = false;
    private boolean playing   = false;
    public ClickListener (Activity a) {
        super ();
        act = a;
        timer = new Timer();
        play_timer = new Timer();
        route = new Route();
        Log.i("W", "w");
    }
    public ClickListener (WifiController wifCtrl, Activity a) {
        super ();
        this.wifiCtrl = wifCtrl;
        act = a;
        timer = new Timer();
        play_timer = new Timer();
        route = new Route();
        Log.i("W", "w");
    }
    @Override
     public void onClick (View v){
        switch (v.getId ()) {
            case R.id.btn_changeip: {
                EditText et_changeip = (EditText) act.findViewById(R.id.et_changeip);
                WifiIP = et_changeip.getText().toString();
            }
            case R.id.connectButton: {
                final String msg = "IP: " + WifiIP;
                try {
                    wifiCtrl.connect(WifiIP, Constants.REMOTE_PORT, act);
                    msgBox ("Connected", msg);
                } catch (Exception e) {
                    msgBox ("Not Connected. Try To Reconnect?", msg, new AlertDialog.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            View v = act.findViewById(R.id.connectButton);
                            ClickListener.this.onClick(v);
                        }
                    });
                }
            }
            case R.id.forwardButton:
                send(Constants.FORWARD_SIG);
            break;

            case R.id.leftButton:
                send(Constants.LEFT_SIG);
                break;

            case R.id.rightButton:
                send(Constants.RIGHT_SIG);
                break;

            case R.id.backwardButton:
                send(Constants.BACKWARD_SIG);
                break;

            case R.id.stopButton:
                send(Constants.STOP_SIG);
                break;

            case R.id.playButton:
                playing ^= true;
                playBack ();
                break;

            case R.id.recordButton:
                recording ^= true;
                break;
        }
     }
    private void send (String msg) {
        if (recording) {
            record ();
        }
        try {
            wifiCtrl.send(msg);
        } catch (Exception e) {
            sendEHandler();
        }
    }
    
    private void record () {
            long time = this.timer.getDuration();
            RouteNode node = new RouteNode(msg, this.speed, (int) time);
            Log.d ("RouteInfo: ", "Node " +  route.getPathLength() + ", Direction " + msg + ", Speed " + Integer.toString(this.speed) +
                    ", Time: " + Long.toString(time) + "secs");
            route.addToRoute(node);
    }
    
    private void playBack () {
        // Should play on it's own thread.
        for (long k=0, startTime = System.currentTimeMillis(); k<route.getPathLength(), ++k) {
            long routeTime = route.getLocation(k).getTime() * 1000;
            String signal = route.getLocation(k).getDirection();
            char spd = (char)route.getLocation(k).getSpeed();
            signal += spd;

            try {
                wifiCtrl.send(signal);
            } catch (Exception e) {
                sendEHandler();
            }
            // Sleep for the duration of the current node time
            while (System.currentTimeMillis - startTime < routeTime);
            Log.i("Playback Count", Integer.toString(k));
        }
    }
    private void sendEHandler () {
        new AlertDialog.Builder (act).setTitle (R.string.connectionErrDialogTitle)
                .setMessage (R.string.noConnMsg).setCancelable(true).setPositiveButton(R.string.posBtnTxt, null)
                .create ().show ();
    }
    public void msgBox (String title, String msg) {
        new AlertDialog.Builder(act).setTitle(title).setMessage(msg).setPositiveButton("Okay", null).create().show();
    }

    public void msgBox (String title, String msg, AlertDialog.OnClickListener ocl) {
        new AlertDialog.Builder(act).setTitle(title).setMessage(msg).setNegativeButton("Cancel", null).setPositiveButton("Okay", ocl).create().show();
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        String newSpeed = "k";
        SeekBar skbrSpeed = (SeekBar) act.findViewById(R.id.skbrSpeedBar);
        speed = skbrSpeed.getProgress();
        char spd = (char) speed;
        newSpeed += spd;
        try {
            wifiCtrl.send(newSpeed);    // This should be a single digit to avoid fast speed changes
        } catch (Exception e) {
            sendEHandler();
        }
    }
}

