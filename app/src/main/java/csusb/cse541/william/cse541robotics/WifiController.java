package csusb.cse541.william.cse541robotics;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.io.IOException;
import java.net.UnknownHostException;
/**
 * Created by William on 5/20/2015.
 */
public class WifiController {
    private Socket soc;
    private InputStream inS;
    private OutputStream outS;
    private final String ipAddress = "10.0.1.53";  // needs to be replaced with the real ip address
    private final short port = 23;

    public WifiController () {

    }
    /* Connect to a remote client using specified ip address and port */
    public void connect (String ipAddress, short port) {
        try {
            soc = new Socket(ipAddress, port);
            inS = soc.getInputStream();
            outS = soc.getOutputStream();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void disconnect () throws IOException{
        soc.close ();
    }
    /* Send the specified msg to the connected remote client */
    public void send (String msg) throws IOException {
        outS.write (msg.getBytes());
    }
    /* Get a message if available from the remote client */
    public String recieve () throws Exception {
           // String.parseI(inS.rr
        return "";
     }
    /* Listen for any incoming messages from the remote client */
    public void listen () {
        while (true) {

            //System.out.println (recieve ());
           // if (out.
        }
    }
}
