import java.util.ArrayList;

public class PlaneTiler2 {
    public ArrayList<Tile2> _tiles;
    public Plane2 _plane;

    public PlaneTiler2() {
        _plane = new Plane2(3, 3);
        _tiles = new ArrayList<>();
    }

    public PlaneTiler2(int width, int height) {
        if (width < 3) {
            width = 3;
        }
        
        if (height < 3) {
            height = 3;
        }

        _plane = new Plane2(width, height);
        _tiles = new ArrayList<>();
    }

    public boolean add(Tile2 tile) {
        return _tiles.add(tile);
    }
}
