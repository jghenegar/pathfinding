import java.lang.IndexOutOfBoundsException;
import java.lang.IllegalArgumentException;

/**
 * Pathfinder uses A* search to find a near optimal path
 * between to locations with given terrain.
 */
//hiiiiiiiiiiii
    //this is Makayla...
public class Pathfinder {

    /**
     * PFNode will be the key for MinPQ (used in computePath())
     */
    private class PFNode implements Comparable<PFNode> {
        // loc: the location of the PFNode
        // fromNode: how did we get here? (linked list back to start)
        Coord location;
        PFNode previous;
        float cost;
        public PFNode(Coord loc, PFNode fromNode, float newCost) {
           location=loc;
           previous=fromNode;
           cost=newCost;
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
            s.push(new PFNode(null, null, 0));
            return s;
        }
    }

    public Pathfinder(Terrain terrain) {
    }
    Coord pathStart=null;
    Coord pathEnd=null;
    float heuristic = 1;
    boolean pathFound=false;
    public void setPathStart(Coord loc) {
        if(loc==null) throw new IllegalArgumentException("Illegal Arguement");
        pathStart=loc;
    }

    public Coord getPathStart() { // this function is a little redundant if we
                                  // just have a global var for it anyway...
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
        // make the priorety queue
        Terrain map = new Terrain("maze232_0.png.emap");
        MinPQ<PFNode> PQ = new MinPQ<>();
        PFNode start = new PFNode(pathStart, null, 0);
        PQ.insert(start);
        while (!pathFound) {
            PFNode location = PQ.delMin();

            if (location.location == pathEnd) {    //if the location is the end
                pathFound = true;
                return;
            }

            if (location == null) return;

            Coord[] neighbourList = checkNeighbour(location.location);  //give all the neighbours
            float previousCost = location.cost;
            for (int i = 0; i < 4; i++) {
                float cost = map.computeTravelCost(location.location, neighbourList[i]);
                PFNode temp = new PFNode(neighbourList[i], location, cost);
                PQ.insert(temp);
            }
        }
    }

    private Coord[] checkNeighbour(Coord loc){
        int x = loc.getI();   //row
        int y = loc.getJ();   //col

        Coord[] neighbourList = new Coord[4];
        neighbourList[0] = loc.add(0, 1);   //up
        neighbourList[1] = loc.add(-1, 0);  //left
        neighbourList[2] = loc.add(0, -1);  //down
        neighbourList[3] = loc.add(1, 0);   //right

        //edge cases

        return neighbourList;

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
