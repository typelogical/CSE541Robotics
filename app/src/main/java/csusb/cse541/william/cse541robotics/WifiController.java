package csusb.cse541.william.cse541robotics;

import android.app.Activity;
import android.app.AlertDialog;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Created by William on 5/20/2015.
 *
 */
public class WifiController {
    private Socket soc;
    //private InputStream in;
    private OutputStream out;

    public WifiController () {}
    /* Connect to a remote client using specified ip address and port */
    public void connect (String ipAddress, int port, Activity act) throws Exception {
        soc = new Socket(ipAddress, port);
        out = soc.getOutputStream();
    }
    public void disconnect () throws Exception{
        soc.close ();
    }
    /* Send the specified msg to the connected remote client */
    public void send (String msg) throws Exception {
        if (soc == null) {
            throw new Exception ();
        }

        out.write (msg.getBytes());
    }
    /* Get a message if available from the remote client */
    @SuppressWarnings("unused")
    public String recieve () throws Exception {
           // String.parseI(inS.rr
        return "";
     }
    /* Listen for any incoming messages from the remote client */
    @SuppressWarnings("unused")
    public void listen () {
        /*while (true) {

            //System.out.println (recieve ());
           // if (out.
        }*/
    }
}
