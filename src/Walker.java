import java.util.Iterator;

/**
 * Walker takes an Iterable of Coords and simulates an individual
 * walking along the path over the given Terrain
 */
public class Walker {

    // terrain: the Terrain the Walker traverses
    // path: the sequence of Coords the Walker follows
    Terrain map;
    Iterable<Coord> ourPath;
    Coord startLoc = null;
    Coord endLoc = null;


    int pathLocs = 0;
    public Walker(Terrain terrain, Iterable<Coord> path) {
        map = terrain;
        ourPath = path;
        int idx = 0;
        for( Coord c : ourPath) {
            if(idx == 0) startLoc = c;
            endLoc = c;
        }
    }


    // returns the Walker's current location
    public Coord getLocation() {        //????
        int idx = 0;
        for( Coord c : ourPath) {
            if(idx == pathLocs) return c;
        }
        return null;
    }

    // returns true if Walker has reached the end Coord (last in path)
    public boolean doneWalking() {
        int idx = 0;
        for( Coord c : ourPath) {
            idx++;
        }
        return idx == pathLocs;
    }

    // advances the Walker along path
    // byTime: how long the Walker should traverse (may be any non-negative value)
    public void advance(float byTime) {
        float totalTime = 0;
        for( Coord c : ourPath) {
            pathLocs++;
                         // ourPath.cost;
            totalTime += map.computeTravelCost(startLoc, c) + map.computeDistance(c, endLoc);

            if(totalTime >= byTime) return;
        }
    }

}
