import java.util.ArrayList;

public class PlaneTiler {
    public ArrayList<Plane> _solutions;
    public ArrayList<Tile> _tiles;
    public int _width;
    public int _height;

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
    }

    public boolean add(Tile tile) {
        return _tiles.add(tile);
    }

    private ArrayList<Tile> candidates(Plane plane, int x, int y) {
        ArrayList<Tile> candidates = new ArrayList<>();

        for (Tile tile : _tiles) {
            if (!tile._isPlaced &&
                tile.connectsB(plane._plane[y-1][x-1]) && tile.connectsB(plane._plane[y+1][x+1]) &&
                tile.connectsF(plane._plane[y-1][x+1]) && tile.connectsF(plane._plane[y+1][x-1]) &&
                tile.connectsH(plane._plane[y][x-1]) && tile.connectsH(plane._plane[y][x+1]) &&
                tile.connectsV(plane._plane[y-1][x]) && tile.connectsV(plane._plane[y+1][x])) {        
                candidates.add(tile);
            }
        }
        
        return candidates;
    }

    public void tiler() {
        tiler(new Plane(_width, _height), 1, 1, System.currentTimeMillis());
    }

    private void tiler(Plane plane, int x, int y, long start) {
        // base case 1 - plane is full
        if (plane._plane[_height-2][_width-2]._id != -1) {
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
        if (x == 2 && y == 2) {
            System.out.println("Seconds since start: " + ((System.currentTimeMillis() - start) / 1000));
            System.out.println("Next Tile: " + candidates.get(0)._id);
        }

        // NEED TO ACCOUNT FOR PLANAR X AND Y
        int nextX = x;
        int nextY = y;

        if (x < _width - 2) {
            nextX++;
        } else {
            nextX = 1;
            nextY++;
        }

        for (Tile candidate : candidates) {
            plane.planarSet(nextX, nextY, candidate);

            candidate._isPlaced = true;

            tiler(plane, nextX, nextY, start);

            candidate._isPlaced = false;
        }

        plane.planarSet(nextX, nextY, new Tile());

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
