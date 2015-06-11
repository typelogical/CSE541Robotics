package csusb.cse541.william.cse541robotics;

/**
 * Created by William on 6/3/2015.
 *
 * This class represents a path or route that a robot travels along.
 * A route is composed of nodes. Each node specifies the
 */
import java.util.ArrayList;
public class Route {
    public Route () {
        routeNodes = new ArrayList<>();
    }
    /* Add to route */
    public void addToRoute (RouteNode node) {
        routeNodes.add (node);
    }
    /* Get the current location returns the node number */
    public RouteNode getLocation (int location) {
        if (location < routeNodes.size ()) {
            return routeNodes.get (location);
        }
        return null;
    }

    /* Gets the number of nodes */
    public int getPathLength () {
        return routeNodes.size ();
    }
    private ArrayList<RouteNode> routeNodes;
}
