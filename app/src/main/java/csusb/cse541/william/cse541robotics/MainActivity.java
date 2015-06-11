package csusb.cse541.william.cse541robotics;

import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;
import android.widget.Button;
import android.widget.SeekBar;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        setContentView(R.layout.activity_main);
        createButtons();
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

        Button playButton = (Button) findViewById(R.id.playButton);
        playButton.setOnClickListener(ocl);

        SeekBar skbrSpeed = (SeekBar) findViewById(R.id.skbrSpeedBar);
        skbrSpeed.setOnSeekBarChangeListener(ocl);
    }
}
