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
    Iterator <Coord> iterator;
    Coord current;
    float money = 0;

    int pathLocs = 0;
    public Walker(Terrain terrain, Iterable<Coord> path) {
        iterator = path.iterator();
        current =iterator.next();
        map = terrain;

    }


    // returns the Walker's current location
    public Coord getLocation() {        //????
        return current;
    }

    // returns true if Walker has reached the end Coord (last in path)
    public boolean doneWalking() {
        return !iterator.hasNext();
    }

    // advances the Walker along path
    // byTime: how long the Walker should traverse (may be any non-negative value)
    public void advance(float byTime) {
        money += byTime;
        if (!doneWalking()) {
            Coord next = iterator.next();
            float cost = map.computeTravelCost(current, next);
            if (cost <= money){
                money-=cost;
                current = next;
            }
        }
    }
}
