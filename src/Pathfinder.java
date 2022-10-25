import java.lang.IndexOutOfBoundsException;
import java.lang.IllegalArgumentException;

/**
 * Pathfinder uses A* search to find a near optimal path
 * between to locations with given terrain.
 */

public class Pathfinder {

    /**
     * PFNode will be the key for MinPQ (used in computePath())
     */
    private class PFNode implements Comparable<PFNode> {
        // loc: the location of the PFNode
        // fromNode: how did we get here? (linked list back to start)
        public PFNode(Coord loc, PFNode fromNode) {
           Coord location=loc;
           PFNode previous=fromNode;
           int cost=0;
        }

        // compares this with that, used to find minimum cost PFNode
        public int compareTo(PFNode that) {
            return this.compareTo(that);
        }

        // returns the cost to travel from starting point to this
        // via the fromNode chain
        public float getCost(float heuristic) {
            return 0;
        }

        // returns if this PFNode is still valid
        public boolean isValid() {
            return false;
        }

        // invalidates the PFNode
        public void invalidate() {
        }

        // returns if the PFNode has been used
        public boolean isUsed() {
            return true;
        }

        // uses the PFNode
        public void use() { }

        // returns an Iterable of PFNodes that surround this
        public Iterable<PFNode> neighbors() {
            Stack<PFNode> s = new Stack<>();
            s.push(new PFNode(null, null));
            return s;
        }
    }

    public Pathfinder(Terrain terrain) {
    }
    Coord pathStart=null;
    Coord pathEnd=null;
    float heuristic;
    boolean pathFound=false;
    public void setPathStart(Coord loc) {
        if(loc==null) throw new IllegalArgumentException("Illegal Arguement");
        pathStart=loc;
    }

    public Coord getPathStart() {
        return pathStart;
    }

    public void setPathEnd(Coord loc) {
        if(loc==null) throw new IllegalArgumentException("Illegal Arguement");
        pathEnd=loc;
    }

    public Coord getPathEnd() {
        return pathEnd;
    }

    public void setHeuristic(float v) {
        heuristic=v;
    }

    public float getHeuristic() {
        return heuristic;
    }

    //cleans out the queues ??
    public void resetPath() {
    }

    public void computePath() {
    }

    public boolean foundPath() {
        return pathFound;
    }

    public float getPathCost() {
        return 0;
    }

    public int getSearchSize() {
        return 0;
    }

    public Iterable<Coord> getPathSolution() {
        return null;
    }

    public boolean wasSearched(Coord loc) {
        return false;
    }
}
