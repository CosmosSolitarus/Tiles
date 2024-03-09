import java.util.ArrayList;

public class PlaneTiler {
    public ArrayList<Tile> _tiles;
    public Tile _universal;
    public int _width;
    public int _height;

    public PlaneTiler(int width, int height) {
        _tiles = new ArrayList<>();
        _universal = new Tile();

        if (height <= 1) {
            _height = 3;
        } else {
            _height = height + 2;
        }

        if (width <= 1) {
            _width = 3;
        } else {
            _width = width + 2;
        }
    }

    public boolean add(Tile tile) {
        _universal.addAll(tile);
        return _tiles.add(tile);
    }

    public ArrayList<Integer> adjIntersect(PlaneNode pn) {
        return adjSect( adjSect(adjSect(pn._TL._tile._BRadj, pn._TM._tile._BMadj), 
                                adjSect(pn._TR._tile._BLadj, pn._MR._tile._MLadj)), 
                        adjSect(adjSect(pn._BR._tile._TLadj, pn._BM._tile._TMadj), 
                                adjSect(pn._BL._tile._TRadj, pn._ML._tile._MRadj)));
    }

    /**
     * Assumes adj1 and adj2 elements are in ascending order
     * @param adj1
     * @param adj2
     * @return
     */
    private ArrayList<Integer> adjSect(ArrayList<Integer> adj1, ArrayList<Integer> adj2) {
        int n = adj1.size();
        int m = adj2.size();

        // adj1 is universal, so return adj2
        if (n == _universal._BLadj.size()) {
            // System.out.println("adj1 is univ");
            return adj2;
        }

        // adj2 is universal, so return adj1
        if (m == _universal._BLadj.size()) {
            // System.out.println("adj2 is univ");
            return adj1;
        }

        // System.out.println("no univ");

        int i = 0;
        int j = 0;

        ArrayList<Integer> intersection = new ArrayList<>();

        while (i < n && j < m) {
            if (adj1.get(i) < adj2.get(j)) {
                i++;
            } else if (adj1.get(i) > adj2.get(j)) {
                j++;
            } else {
                intersection.add(adj1.get(i));
                i++;
                j++;
            }
        }

        return intersection;
    }

    private ArrayList<Integer> inAnotB(ArrayList<Integer> a, ArrayList<Integer> b) {
        ArrayList<Integer> result = new ArrayList<>();
        int i = 0, j = 0;
        
        while (i < a.size() && j < b.size()) {
            if (a.get(i).equals(b.get(j))) {
                // If the current elements in both lists are equal, move to the next element in both lists
                i++;
                j++;
            } else if (a.get(i) < b.get(j)) {
                // If the current element in A is less than the current element in B,
                // it means the element is unique to A and should be added to the result.
                // Then move to the next element in A.
                result.add(a.get(i));
                i++;
            } else {
                // If the current element in A is greater than the current element in B,
                // it means we should move to the next element in B to find a match or a greater element.
                j++;
            }
        }
        
        // If there are remaining elements in A after the above loop,
        // add all of them to the result as they are not in B.
        while (i < a.size()) {
            result.add(a.get(i));
            i++;
        }
        
        return result;
    }

    public ArrayList<String> tiler() {
        Plane pn = new Plane(_width, _height);
        pn.setAll(_universal);

        return tiler(pn, 0, 0, new ArrayList<String>(), new ArrayList<Integer>(), System.currentTimeMillis(), (long) 0);
    }

    private ArrayList<String> tiler(Plane plane, int x, int y, ArrayList<String> validPlanes, ArrayList<Integer> usedTiles, long startTime, long ops) {
        // need to account for tiles already used (another intersection call with a arraylist of _used?)
        // need to account for translations, mirrors, and rotations (otherwise there are 96x more solutions)
        // add a "planeStr" or equivalent

        ops++;

        /*
        if (ops <= 20 && !validPlanes.isEmpty()) {
            System.out.println(validPlanes);
        }

        if (ops <= 20) {
            System.out.println(plane.toString());
        }

        // diagnostics
        if (_ops % 100 == 0) {
            System.out.println("\nPlanes Checked: " + _ops);
            System.out.println("Solutions Found: " + _validPlanes.size());
            System.out.println("Seconds Taken: " + (System.currentTimeMillis() - start) / 1000.0);
            System.out.println("Planes Per Second: " + (double) _ops / ((System.currentTimeMillis() - start) / 1000.0));
            System.out.println(plane.toString());
            // System.out.println("x, y: \n" + plane.get(x, y)._tile._BLadj.toString());
            System.out.println("x: " + x + " | y: " + y);
        }
        */

        // no tiles available and plane full (x = _width - 2 and y = _height - 2)
        if (plane.get(_width-1, _height-1)._tile != _universal) {
            for (String str : validPlanes) {
                if (str.equals(plane.toString())) {
                    return validPlanes;
                }
            }

            validPlanes.add(plane.toString());
            return validPlanes;
        }

        // System.out.println("x, y: \n" + plane.get(x, y)._tile._BLadj.toString());
        ArrayList<Integer> ids = inAnotB(adjIntersect(plane.get(x, y)), usedTiles);
        //System.out.println("ids: " + ids.toString());
        //System.out.println("Plane: " + plane.toString());


        // no tiles available and plane not full (x != _width - 2 or y != _height - 2)
        if (ids.isEmpty()) {
            return validPlanes;
        }

        int nextX = x;
        int nextY = y;

        if (x < _width - 1) {
            nextX++;
        } else {
            nextX = 0;
            nextY++;
        }

        for (Integer id : ids) {
            plane.set(x, y, _tiles.get(id));
            usedTiles.add(id);

            validPlanes = tiler(plane, nextX, nextY, validPlanes, usedTiles, startTime, ops);

            plane.set(x, y, _universal);
            usedTiles.remove(usedTiles.size() - 1);
        }

        return validPlanes;
    }
}
