import java.util.ArrayList;

public class PlaneTiler {
    public ArrayList<Plane> _solutions;
    public ArrayList<Tile> _tiles;
    public int _width;
    public int _height;
    public long start;
    public long iterations;

    public PlaneTiler() {
        this(1, 1);
    }

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
        start = -1;
        iterations = 0;
    }

    public boolean add(Tile tile) {
        return _tiles.add(tile);
    }

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

    public void tiler() {
        start = System.currentTimeMillis();
        tiler(new Plane(_width, _height), 1, 1);
    }

    private void tiler(Plane plane, int x, int y) {
        //iterations++;

        //if (iterations > 6) return;
        
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

        // diagnostics
        /*
        if (iterations % 100000 == 0) {
            System.out.println("Seconds since start: " + ((System.currentTimeMillis() - start) / 1000));
            System.out.println("Iterations since start: " + iterations);
            System.out.println("Average iterations per second: " + (iterations / ((System.currentTimeMillis() - start) / 1000)));
        }
        */

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

            //System.out.println("Placed " + candidate.ascii() + " at (" + x + ", " + y + ")\nNew Board: ");
            //System.out.println(plane.toString());

            tiler(plane, nextX, nextY);

            candidate._isPlaced = false;
        }

        plane.planarSet(x, y, new Tile());

        return;
    }

    public String printSolutions() {
        String out = "%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%\n";
        
        for (Plane plane : _solutions) {
            out += plane.toString();
            out += "%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%\n";
        }

        return out;
    }
}
