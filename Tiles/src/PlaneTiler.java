/* A PlaneTiler finds arrangments of provided Tiles on a provided Plane that tile the plane.
 * To tile the plane, Tiles must connect to all Tiles immediately surrounding them (to the 
 * top left, directly above, top right, directly right, bottom right, directly below, bottom
 * left, and directly left). A border of one additional Tile than specified is used to utilize
 * the planarSet() method of the Plane class.
 * 
 * @Author  Jack Roberts
 * 15 March 2024
 */
import java.util.ArrayList;

public class PlaneTiler {
    public ArrayList<Plane> _solutions;
    public ArrayList<Tile> _tiles;
    public int _width;
    public int _height;

    /**
     * Default constructor.
     */
    public PlaneTiler() {
        this(1, 1);
    }

    /**
     * Overloaded constructor. Width and height must
     * be non-negative.
     * @param width     the width of the Plane
     * @param height    the height of the Plane
     */
    public PlaneTiler(int width, int height) {
        if (width < 1) {
            width = 1;
        }
        
        if (height < 1) {
            height = 1;
        }

        _solutions = new ArrayList<>();
        _tiles = new ArrayList<>();
        _width = width + 2;
        _height = height + 2;
    }

    /**
     * Adds the specified Tile to the list of Tiles
     * @param tile  the Tile being added
     * @return      true (as specified by Collections.add)
     */
    public boolean add(Tile tile) {
        return _tiles.add(tile);
    }

    /**
     * Returns the list of Tiles that have not already been
     * placed in the Plane and satisfy all connection
     * requirements of the eight Tiles immediately surrounding
     * the specified (x,y).
     * @param plane the current Plane
     * @param x     the x-coordinate in the Plane
     * @param y     the y-coordinate in the Plane
     * @return      the list of valid Tiles
     */
    private ArrayList<Tile> candidates(Plane plane, int x, int y) {
        ArrayList<Tile> candidates = new ArrayList<>();

        for (Tile tile : _tiles) {
            if (!tile._isPlaced &&
                tile.connectsB(plane.get(x-1, y-1)) && tile.connectsB(plane.get(x+1, y+1)) &&
                tile.connectsF(plane.get(x+1, y-1)) && tile.connectsF(plane.get(x-1, y+1)) &&
                tile.connectsH(plane.get(x-1, y)) && tile.connectsH(plane.get(x+1, y)) &&
                tile.connectsV(plane.get(x, y-1)) && tile.connectsV(plane.get(x, y+1))) {        
                candidates.add(tile);
            }
        }
        
        return candidates;
    }

    /**
     * Public facing method to call the private tiler().
     */
    public void tiler() {
        tiler(new Plane(_width, _height), 1, 1);
    }

    /**
     * A recursive approach to finding arrangements of Tiles
     * that tile the plane.
     * @param plane the current Plane
     * @param x     the x-coordinate of the next Tile
     * @param y     the y-coordinate of the next Tile
     */
    private void tiler(Plane plane, int x, int y) {
        // base case 1 - plane is full
        if (plane.get(_width-2, _height-2)._id != -1) {
            for (Plane solution : _solutions) {
                if (plane.isomorphic(solution)) {
                    return;
                }
            }

            _solutions.add(new Plane(plane));
            return;
        }

        ArrayList<Tile> candidates = candidates(plane, x, y);

        // base case 2 - plane not full but no valid tiles
        if (candidates.isEmpty()) {
            return;
        }

        // recursive call
        int nextX = x;
        int nextY = y;

        if (x < _width - 2) {
            nextX++;
        } else {
            nextX = 1;
            nextY++;
        }

        for (Tile candidate : candidates) {
            plane.planarSet(x, y, candidate);

            candidate._isPlaced = true;

            tiler(plane, nextX, nextY);

            candidate._isPlaced = false;
        }

        plane.planarSet(x, y, new Tile());

        return;
    }

    /**
     * Converts all solution Planes to a string and
     * returns them with formatting.
     * @return  all solution Planes with formatting
     */
    public String printSolutions() {
        String out = "%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%\n";
        
        for (Plane plane : _solutions) {
            out += plane.toString();
            out += "%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%\n";
        }

        return out;
    }
}
