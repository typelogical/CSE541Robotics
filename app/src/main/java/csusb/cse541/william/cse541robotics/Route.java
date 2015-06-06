package csusb.cse541.william.cse541robotics;

/**
 * Created by William on 6/3/2015.
 */
/***
    This class represents a path or route that a robot travels along.
    A route is composed of nodes. Each node specifies the
 */
import java.util.ArrayList;
public class Route {

    /* Get the current location returns the node number */
    public RouteNode getLocation (int location) {
        if (location < routeData.size ()) {
            return routeData.get (location);
        }
        return null;
    }

    /* Gets the number of nodes */
    public int getPathLength () {
        return routeData.size ();
    }
    private ArrayList<RouteNode> routeData;
}
