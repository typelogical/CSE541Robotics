package csusb.cse541.william.cse541robotics;

import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;
import android.widget.Button;

public class MainActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        setContentView(R.layout.activity_main);

        //SeekBar skbrSpeed = (SeekBar) findViewById(R.id.skbrSpeedBar);
        //skbrSpeed.setOnSeekBarChangeListener(oclChangeSpeed);


    }


    /* Connect to the arduino robot */
    private void createButtons() {
        ClickListener ocl = new ClickListener(this);
        Button btn_changeip = (Button) findViewById(R.id.btn_changeip);
        btn_changeip.setOnClickListener(ocl);

        Button btn_forward = (Button) findViewById(R.id.forwardButton);
        btn_forward.setOnClickListener(ocl);

        Button btn_backward = (Button) findViewById(R.id.backwardButton);
        btn_backward.setOnClickListener(ocl);

        Button btn_right = (Button) findViewById(R.id.rightButton);
        btn_right.setOnClickListener(ocl);

        Button btn_left = (Button) findViewById(R.id.leftButton);
        btn_left.setOnClickListener(ocl);

        Button btn_stop = (Button) findViewById(R.id.stopButton);
        btn_stop.setOnClickListener(ocl);

        Button btn_connect = (Button) findViewById(R.id.connectButton);
        btn_connect.setOnClickListener(ocl);

        Button btn_disconnect = (Button) findViewById(R.id.disconnectButton);
        btn_disconnect.setOnClickListener(ocl);

        Button recordButton = (Button) findViewById(R.id.recordButton);
        recordButton.setOnClickListener(ocl);
    }


/*    SeekBar.OnSeekBarChangeListener oclChangeSpeed = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            String newSpeed = "k";
            SeekBar skbrSpeed = (SeekBar) findViewById(R.id.skbrSpeedBar);
            int _spd = skbrSpeed.getProgress();
            char spd = (char) _spd;
            newSpeed += spd;
            try {
                wifiCtrl.send(newSpeed);    // This should be a single digit to avoid fast speed changes
                speed = newSpeed;
            } catch (Exception e) {
                sendEHandler();
            }
        }
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }
    };

    View.OnClickListener oclRecord = new View.OnClickListener()  {
        @Override
        public void onClick (View v) {
            if (recording) {
                recording = false;
                msgBox("Record", "Stopped Recording.");
            } else {
                recording = true;
                timer = new Timer();
                timer.start();
                route = new Route ();
                msgBox("Record", "Started Recording.");
            }

        }

    };*/
     /* Send */


}
