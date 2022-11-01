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
    Terrain map;
    int N;
    boolean[][] wasSearchedA = new boolean[N][N];
    Coord pathStart=null;
    PFNode start = null;

    Coord pathEnd=null;
    PFNode end = null;
    float heuristic = 1;
    boolean pathFound=false;
    int searchSize = 0;
    public Pathfinder(Terrain terrain) {
        map = terrain;
        N = map.getN();
        for(int i = 0; i < N; i ++){
            for(int j = 0; j < N; j++) {
                wasSearchedA[i][j] = false;
            }
        }
    }
    public void setPathStart(Coord loc) {
        if(loc==null) throw new IllegalArgumentException("Illegal Arguement");
        pathStart=loc;
        start = new PFNode(pathStart, null, 0);
    }

    public Coord getPathStart() { // this function is a little redundant if we
                                  // just have a global var for it anyway...
        return pathStart;
    }

    public void setPathEnd(Coord loc) {
        if(loc==null) throw new IllegalArgumentException("Illegal Arguement");
        pathEnd=loc;
        end = new PFNode(pathEnd, null, 0);

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
        //Terrain map = new Terrain("maze232_0.png.emap");
        MinPQ<PFNode> PQ = new MinPQ<>();
        PQ.insert(start);
        searchSize++;           //when something is inserted, increase this var
        wasSearchedA[start.location.getI()][start.location.getJ()] = true;
        while (!pathFound) {
            PFNode location = PQ.delMin();

            if (location == null) return;          // location must exist

            if (location.location == pathEnd) {    //if the location is the end
                pathFound = true;
                return;
            }

            Coord[] neighbourList = checkNeighbour(location.location);  //give all the neighbours

            float previousCost = location.cost;
            for (int i = 0; i < 4; i++) {
                float cost = map.computeTravelCost(location.location, neighbourList[i]);
                // if neighbour is invalid, move on to the next neighbour
                if(!neighbourEdgeCases(location, neighbourList[i])) {
                    continue;  //check neighbour edge cases
                }
                PFNode temp = new PFNode(neighbourList[i], location, cost);
                PQ.insert(temp);
                searchSize++;           //when something is inserted, increase this var
                wasSearchedA[temp.location.getI()][temp.location.getJ()] = true;
            }
        }
    }

    private Coord[] checkNeighbour(Coord loc){
//        int x = loc.getI();   //row
//        int y = loc.getJ();   //col

        Coord[] neighbourList = new Coord[4];
        neighbourList[0] = loc.add(0, 1);   //up
        neighbourList[1] = loc.add(-1, 0);  //left
        neighbourList[2] = loc.add(0, -1);  //down
        neighbourList[3] = loc.add(1, 0);   //right

        //edge cases in other function

        return neighbourList;

    }

    private boolean neighbourEdgeCases(PFNode loc, Coord neighbour){
        // neighbour can't be...

        // off the board
        int N = map.getN(); // this is the board size
        if(neighbour.getI() >= N || neighbour.getI() < 0 ||         // I has to be within the board range
            neighbour.getJ() >= N || neighbour.getJ() < 0) {        // J has to be within the board range
            return false;
        }
        // a previously searched node
        return !(wasSearched(neighbour)); // we don't want a node that was seen before
    }

    public boolean foundPath() {
        return pathFound;
    }

    public float getPathCost() {
        return end.cost;
    }

    public int getSearchSize() {
        return searchSize;
    }

    public Iterable<Coord> getPathSolution() {
        return null;        //from starting position to end
    }

    public boolean wasSearched(Coord loc) {
        return (wasSearchedA[loc.getI()][loc.getJ()]);     // return true if was searched
    }
}
