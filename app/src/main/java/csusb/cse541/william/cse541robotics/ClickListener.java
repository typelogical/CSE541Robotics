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
    long total_time = 0;
    Activity act = null;
    Timer timer;
    Timer play_timer;
    Route route;
    private boolean recording = false;
    private boolean playing   = false;
    Thread t_play;

    public ClickListener (Activity a) {
        super ();
        act = a;
        timer = new Timer();
        play_timer = new Timer();
        route = new Route();
        wifiCtrl = new WifiController();
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
                if(playing) {
                    t_play = new Thread(new run_playbutton());
                    play_timer.start();
                }
                break;

            case R.id.recordButton:
                recording ^= true;
                if(recording)
                    timer.start();
                break;
        }

     }
    public void send (String msg) {
        if (recording) {
            long time = this.timer.getDuration();
            RouteNode node = new RouteNode(msg, this.speed, (int) time);
            Log.d ("RouteInfo: ", "Node " +  route.getPathLength() + ", Direction " + msg + ", Speed " + Integer.toString(this.speed) +
                    ", Time: " + Long.toString(time) + "msecs");
            total_time += time;
            route.addToRoute(node);
            timer.start();
        }

        try {
            wifiCtrl.send(msg);
        } catch (Exception e) {
            sendEHandler();
        }

    }

    class run_playbutton implements Runnable {
        @Override
        public void run() {
            if(playing && !recording && route.getPathLength()>0) {
                int k=0;
                while(k<route.getPathLength()) {
                    if(play_timer.getDuration() > route.getLocation(k).getTime()) {

                        String signal = route.getLocation(k).getDirection();
                        char spd = (char)route.getLocation(k).getSpeed();
                        signal += spd;

                        try {
                            wifiCtrl.send(signal);
                            Thread.sleep(10L);
                        } catch (Exception e) {
                            act.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    sendEHandler();
                                }
                            });

                        }

                        play_timer.start();
                        k++;
                        Log.i("Playback Count", Integer.toString(k));
                    }
                }
            }

            Log.i("Play Thread", "Done");
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

