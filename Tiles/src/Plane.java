public class Plane {
    public int _height;
    public int _width;
    public PlaneNode[][] _plane;

    public Plane() {
        _height = 3;
        _width = 3;
        init();
    }

    public Plane(int height, int width) {
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

        init();
    }

    private void init() {
        _plane = new PlaneNode[_width][_height];
        
        // create all TileNodes in the plane
        for (int i = 0; i < _width; i++) {
            for (int j = 0; j < _height; j++) {
                _plane[i][j] = new PlaneNode(i, j);
            }
        }

        // ---- connect TileNodes together ----
        // center TileNodes
        for (int x = 1; x < _width - 1; x++) {
            for (int y = 1; y < _height - 1; y++) {
                _plane[x][y]._TL = _plane[x-1][y-1];
                _plane[x][y]._TM = _plane[x][y-1];
                _plane[x][y]._TR = _plane[x+1][y-1];
                _plane[x][y]._ML = _plane[x-1][y];
                _plane[x][y]._MR = _plane[x+1][y];
                _plane[x][y]._BL = _plane[x-1][y+1];
                _plane[x][y]._BM = _plane[x][y+1];
                _plane[x][y]._BR = _plane[x+1][y+1];
            }
        }

        // top center TileNodes
        for (int x = 1; x < _width - 1; x++) {
            _plane[x][0]._ML = _plane[x-1][0];
            _plane[x][0]._MR = _plane[x+1][0];
            _plane[x][0]._BL = _plane[x-1][1];
            _plane[x][0]._BM = _plane[x][1];
            _plane[x][0]._BR = _plane[x+1][1];
        }

        // bottom center TileNodes
        for (int x = 1; x < _width - 1; x++) {
            _plane[x][_height-1]._TL = _plane[x-1][_height-2];
            _plane[x][_height-1]._TM = _plane[x][_height-2];
            _plane[x][_height-1]._TR = _plane[x+1][_height-2];
            _plane[x][_height-1]._ML = _plane[x-1][_height-1];
            _plane[x][_height-1]._MR = _plane[x+1][_height-1];
        }

        // left center TileNodes
        for (int y = 1; y < _height - 1; y++) {
            _plane[0][y]._TM = _plane[0][y-1];
            _plane[0][y]._TR = _plane[1][y-1];
            _plane[0][y]._MR = _plane[1][y];
            _plane[0][y]._BM = _plane[0][y+1];
            _plane[0][y]._BR = _plane[1][y+1];
        }

        // right center TileNodes
        for (int y = 1; y < _height - 1; y++) {
            _plane[_width-1][y]._TL = _plane[_width-2][y-1];
            _plane[_width-1][y]._TM = _plane[_width-1][y-1];
            _plane[_width-1][y]._ML = _plane[_width-2][y];
            _plane[_width-1][y]._BL = _plane[_width-2][y+1];
            _plane[_width-1][y]._BM = _plane[_width-1][y+1];
        }
        
        // top left TileNode
        _plane[0][0]._MR = _plane[1][0];
        _plane[0][0]._BM = _plane[0][1];
        _plane[0][0]._BR = _plane[1][1];

        // top right TileNode
        _plane[_width-1][0]._ML = _plane[_width-2][0];
        _plane[_width-1][0]._BL = _plane[_width-2][1];
        _plane[_width-1][0]._BM = _plane[_width-1][1];        
        
        // bottom left TileNode
        _plane[0][_height-1]._TM = _plane[0][_height-2];
        _plane[0][_height-1]._TR = _plane[1][_height-2];
        _plane[0][_height-1]._MR = _plane[1][_height-1];        
        
        // bottom right TileNode
        _plane[_width-1][_height-1]._TL = _plane[_width-2][_height-2];
        _plane[_width-1][_height-1]._TM = _plane[_width-1][_height-2];
        _plane[_width-1][_height-1]._ML = _plane[_width-2][_height-1];
    }

    public PlaneNode get(int x, int y) {
        checkBounds(x, y);
        return _plane[x+1][y+1];
    }

    private void checkBounds(int x, int y) {
        if (x < 0 || x >= _width - 2 || y < 0 || y >= _height - 2) {
            throw new IndexOutOfBoundsException("Invalid coordinates: (" + x + ", " + y + "). 0 <= x <= " + (_width-3) + " and 0 <= y <= " + (_height-3) + ".");
        } 
    }

    public PlaneNode set(int x, int y, Tile tile) {
        checkBounds(x, y);

        PlaneNode prev = _plane[x+1][y+1];

        _plane[x+1][y+1]._tile = tile;

        // add tiles to right and left edges of plane
        // TOP NEEDS TO BE UNIVERSAL
        if (x == 0) {
            _plane[_width-1][y+1]._tile = tile; // right
        }
        
        if (x == _width - 3) {
            _plane[0][y+1]._tile = tile;        // left
        }

        // add tiles to top and bottom edges and corners of plane
        if (y == 0) {
            _plane[x+1][_height-1]._tile = tile;            // bottom edge

            if (x == 0) {
                _plane[_width-1][_height-1]._tile = tile;   // bottom right corner
            }
            
            if (x == _width - 3) {
                _plane[0][_height-1]._tile = tile;          // bottom left corner
            }
        }

        if (y == _height - 3) {
            _plane[x+1][0]._tile = tile;

            if (x == 0) {
                _plane[_width-1][0]._tile = tile;           // top right corner
            }
            
            if (x == _width - 3) {
                _plane[0][0]._tile = tile;                  // top left corner
            }
        }

        return prev;
    }

    public void setAll(Tile universal) {
        for (int x = 0; x < _width - 2; x++) {
            for (int y = 0; y < _height - 2; y++) {
                set(x, y, universal);
            }
        }
    }

    public PlaneNode clear(int x, int y) {
        return set(x, y, new Tile());
    }

    public boolean equals(Plane other) {
        Plane bigPlane = new Plane(_height, _width);

        for (int x = 0; x < _width; x++) {
            for (int y = 0; y < _height; y++) {
                bigPlane.set(x, y, _plane[x][y]._tile);
            }
        }


        return false;
    }

    public String toString() {
        String out = "";

        for (int y = 0; y < _height; y++) {
            out += "--------------------------------------\n";

            for (int x = 0; x < _width; x++) {
                if (_plane[x][y]._tile._name == null) {
                    out += " | .";
                } else {
                    out += " | " + _plane[x][y]._tile._name;
                }
            }

            out += " |\n";
        }

        out += "--------------------------------------\n";

        return out;
    }
}
