package csusb.cse541.william.cse541robotics;
/*


 */
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


import java.io.IOException;
import java.util.Scanner;

public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    /* Connect to the arduino robot */
    public void connect (View view) {
        wifiCtrl.connect (Constants.REMOTE_IP_ADDRESS, Constants.REMOTE_PORT);
    }
    /* Disconnect from the arduino robot */
    public void disconnect (View view) {
        try {
            wifiCtrl.disconnect ();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /* Move the robot forward */
    public void forward (View view) {
        try {
            wifiCtrl.send(Constants.FORWARD_SIG);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /* Move the robot backward */
    public void backward (View view) {
        try {
            wifiCtrl.send (Constants.BACKWARD_SIG);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /* Move the robot left */
    public void left (View view) {
        try {
            wifiCtrl.send (Constants.LEFT_SIG);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /* Move the robot right */
    public void right (View view) {
        try {
            wifiCtrl.send (Constants.RIGHT_SIG);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /* Stop the robot */
    public void stop (View view) {
        try {
            wifiCtrl.send (Constants.STOP_SIG);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void testConnection () {

    }
    private WifiController wifiCtrl;
}
