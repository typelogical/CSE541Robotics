package csusb.cse541.william.cse541robotics;

import android.app.Activity;
import android.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

/**
 * Created by William on 6/8/2015.
 *
 */

public class ClickListener implements View.OnClickListener {
    private WifiController wifiCtrl = null;
    private String WifiIP = Constants.REMOTE_IP_ADDRESS;
    private String speed;
    Activity act = null;
    Timer timer;
    Route route;
    private boolean recording;
    public ClickListener (Activity a) {
        super ();
        act = a;
    }
    public ClickListener (WifiController wifCtrl, Activity a) {
        super ();
        this.wifiCtrl = wifCtrl;
        act = a;
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
                    msgBox ("Not Connected", msg);
                }
            }
            case R.id.forwardButton:
            break;
            case R.id.leftButton:
                break;
            case R.id.rightButton:
                break;
            case R.id.backwardButton:
                break;
            case R.id.stopButton:
            case R.id.recordButton:
                break;
        }

     }
    public void send (String msg) {
        if (recording) {
            long time = this.timer.getDuration();
            RouteNode node = new RouteNode(msg, this.speed, (int) time);
            Log.d ("RouteInfo: ", "Node " +  route.getPathLength() + ", Direction " + msg + ", Speed" + Integer.parseInt(this.speed) +
                    ", Time: " + time + "secs");
            route.addToRoute(node);
        }

        try {
            wifiCtrl.send (msg);
        } catch (Exception e) {
            sendEHandler();
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
}

