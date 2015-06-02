package csusb.cse541.william.cse541robotics;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;

import java.io.IOException;

public class MainActivity extends Activity {
    private WifiController wifiCtrl = null;
    private String WifiIP = Constants.REMOTE_IP_ADDRESS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        setContentView(R.layout.activity_main);

        wifiCtrl = new WifiController();

        Button btn_changeip = (Button) findViewById(R.id.btn_changeip);
        btn_changeip.setOnClickListener(ocl_changeip);

        Button btn_forward = (Button) findViewById(R.id.forwardButton);
        btn_forward.setOnClickListener(ocl_forward);

        Button btn_backward = (Button) findViewById(R.id.backwardButton);
        btn_backward.setOnClickListener(ocl_backward);

        Button btn_right = (Button) findViewById(R.id.rightButton);
        btn_right.setOnClickListener(ocl_right);

        Button btn_left = (Button) findViewById(R.id.leftButton);
        btn_left.setOnClickListener(ocl_left);

        Button btn_stop = (Button) findViewById(R.id.stopButton);
        btn_stop.setOnClickListener(ocl_stop);

        Button btn_connect = (Button) findViewById(R.id.connectButton);
        btn_connect.setOnClickListener(ocl_connect);

        Button btn_disconnect = (Button) findViewById(R.id.disconnectButton);
        btn_disconnect.setOnClickListener(ocl_disconnect);

        SeekBar skbrSpeed = (SeekBar) findViewById(R.id.skbrSpeedBar);
        skbrSpeed.setOnSeekBarChangeListener(oclChangeSpeed);


    }

    /* Disconnect from the arduino robot */
    View.OnClickListener ocl_disconnect = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            try {
                wifiCtrl.disconnect();
            } catch (IOException e) {
                sendEHandler();
            }
        }
    };

    /* Connect to the arduino robot */
    View.OnClickListener ocl_connect = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            wifiCtrl.connect(WifiIP, Constants.REMOTE_PORT, MainActivity.this);
        }
    };

    View.OnClickListener ocl_forward = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            try {
                wifiCtrl.send(Constants.FORWARD_SIG);
            } catch (Exception e) {
                sendEHandler();
            }
        }
    };

    View.OnClickListener ocl_backward = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            try {
                wifiCtrl.send(Constants.BACKWARD_SIG);
            } catch (Exception e) {
                sendEHandler();
            }
        }
    };

    View.OnClickListener ocl_right = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            try {
                wifiCtrl.send(Constants.RIGHT_SIG);
            } catch (Exception e) {
                sendEHandler();
            }
        }
    };

    View.OnClickListener ocl_left = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            try {
                wifiCtrl.send(Constants.LEFT_SIG);
            } catch (Exception e) {
                sendEHandler();
            }
        }
    };

    View.OnClickListener ocl_stop = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            try {
                wifiCtrl.send(Constants.STOP_SIG);
            } catch (Exception e) {
                sendEHandler();
            }
        }
    };

    View.OnClickListener ocl_changeip = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            EditText et_changeip = (EditText) findViewById(R.id.et_changeip);
            WifiIP = et_changeip.getText().toString();
        }
    };

    SeekBar.OnSeekBarChangeListener oclChangeSpeed = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            SeekBar skbrSpeed = (SeekBar) findViewById(R.id.skbrSpeedBar);
            String newSpeed = ((Integer) skbrSpeed.getProgress()).toString();
            try {
                wifiCtrl.send(newSpeed);    // This should be a single digit to avoid fast speed changes
            } catch (Exception e) {
                new AlertDialog.Builder(MainActivity.this).setTitle(R.string.connectionErrDialogTitle)
                        .setMessage(R.string.noConnMsg).setCancelable(true).setPositiveButton(R.string.posBtnTxt, null)
                        .create().show();
            }
        }

    };

    private void sendEHandler () {
        new AlertDialog.Builder (MainActivity.this).setTitle (R.string.connectionErrDialogTitle)
                .setMessage (R.string.noConnMsg).setCancelable(true).setPositiveButton(R.string.posBtnTxt, null)
                .create ().show ();
    }
}

   
