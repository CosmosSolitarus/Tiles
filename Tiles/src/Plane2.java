public class Plane2 {
    public int _width;
    public int _height;
    public Tile2[][] _plane;

    public Plane2() {
        _width = 0;
        _height = 0;
        init();
    }

    public Plane2(int width, int height) {
        if (width < 0) {
            _width = 0;
        } else {
            _width = width;
        }

        if (height < 0) {
            _height = 0;
        } else {
            _height = height;
        }

        init();
    }

    public Plane2(Plane2 that) {
        this(that._width, that._height);

        for (int x = 0; x < _width; x++) {
            for (int y = 0; y < _height; y++) {
                this._plane[y][x] = new Tile2(that._plane[y][x]);
            }
        }
    }

    private void init() {
        _plane = new Tile2[_height][_width];

        // create blank tiles in the plane
        for (int x = 0; x < _width; x++) {
            for (int y = 0; y < _height; y++) {
                _plane[y][x] = new Tile2();
            }
        }
    }

    private void checkBounds(int x, int y) {
        if (x < 0 || x >= _width || y < 0 || y >= _height) {
            throw new IndexOutOfBoundsException("Invalid coordinates: (" + x + ", " + y + "). 0 <= x <= " + (_width-1) + " and 0 <= y <= " + (_height-1) + ".");
        } 
    }

    private void checkPlanarBounds(int x, int y) {
        if (x < 1 || x >= _width-1 || y < 1 || y >= _height-1) {
            throw new IndexOutOfBoundsException("Invalid coordinates (Planar): (" + x + ", " + y + "). 1 <= x <= " + (_width-2) + " and 1 <= y <= " + (_height-2) + ".");
        } 
    }

    public Tile2 set(int x, int y, Tile2 tile) {
        checkBounds(x, y);

        Tile2 prev = _plane[y][x];

        _plane[y][x] = tile;

        return prev;
    }

    public void setAll(Tile2 tile) {
        for (int x = 0; x < _width; x++) {
            for (int y = 0; y < _height; y++) {
                set(x, y, tile);
            }
        }
    }

    public Tile2 planarSet(int x, int y, Tile2 tile) {
        checkPlanarBounds(x, y);
        
        Tile2 prev = set(x, y, tile);
        
        // add tiles to right and left edges of plane
        if (x == 1) {
            set(_width-1, y, tile);       // right
        }
        
        if (x == _width - 2) {
            set(0, y, tile);            // left
        }

        // add tiles to top and bottom edges and corners of plane
        if (y == 1) {
            set(x, _height-1, tile);                // bottom edge

            if (x == 1) {
                set(_width-1, _height-1, tile);     // bottom right corner
            }
            
            if (x == _width - 2) {
                set(0, _height-1, tile);            // bottom left corner
            }
        }

        if (y == _height - 2) {
            set(x, 0, tile);                        // top edge

            if (x == 1) {
                set(_width-1, 0, tile);             // top right corner
            }
            
            if (x == _width - 2) {
                set(0, 0, tile);                // top left corner
            }
        }

        return prev;
    }

    public boolean equals(Plane2 that) {
        if (_width != that._width) return false;
        if (_height != that._height) return false;
        
        for (int x = 0; x < _width; x++) {
            for (int y = 0; y < _height; y++) {
                if (!_plane[y][x].equals(that._plane[y][x])) {
                    return false;
                }
            }
        }

        return true;
    }

    public boolean isomorphic(Plane2 that) {
        if (_width != that._width) return false;
        if (_height != that._height) return false;
        
        if (translate(that)) return true;

        rotate();
        System.out.println("After rotation:\n" + toString());
        if (translate(that)) return true;

        rotate();
        if (translate(that)) return true;

        rotate();
        if (translate(that)) return true;

        rotate();
        mirror();
        if (translate(that)) return true;
        
        rotate();
        if (translate(that)) return true;
        
        rotate();
        if (translate(that)) return true;

        rotate();
        if (translate(that)) return true;

        // return plane to original state
        rotate();
        mirror();

        return false;
    }

    private boolean translate(Plane2 that) {
        for (int x = 0; x < _width; x++) {
            for (int y = 0; y < _height; y++) {
                if (equals(that)) return true;

                shiftDown();
            }

            shiftRight();
        }

        return false;
    }

    private void shiftRight() {
        Tile2[] temp = new Tile2[_height];

        for (int y = 0; y < _height; y++) {
            temp[y] = new Tile2(_plane[y][_width-1]);
        }

        for (int x = _width - 1; x > 0; x--) {
            for (int y = 0; y < _height; y++) {
                _plane[y][x] = new Tile2(_plane[y][x-1]);
            }
        }

        for (int y = 0; y < _height; y++) {
            _plane[y][0] = new Tile2(temp[y]);
        }
    }

    private void shiftDown() {
        Tile2[] temp = new Tile2[_width];

        for (int x = 0; x < _width; x++) {
            temp[x] = new Tile2(_plane[_height-1][x]);
        }

        for (int x = 0; x < _width; x++) {
            for (int y = 1; y < _height; y++) {
                _plane[y][x] = new Tile2(_plane[y-1][x]);
            }
        }

        for (int x = 0; x < _width; x++) {
            _plane[0][x] = new Tile2(temp[x]);
        }
    }

    /**
     * Rotates the plane and each tile by 90 degrees clockwise.
     * Only allows n*n planes.
     */
    private void rotate() {
        if (_width != _height) return;

        mirror();

        Tile2 temp;

        for (int x = 0; x < _width; x++) {
            for (int y = x; y < _height; y++) {
                temp = new Tile2(_plane[y][x]);
                _plane[y][x] = new Tile2(_plane[y][x]);
                _plane[y][x] = new Tile2(temp);

                _plane[y][x].rotate();
                _plane[y][x].rotate();
            }
        }
    }

    /**
     * Mirrors the plane and each tile over the y-axis
     */
    private void mirror() {
        Tile2 temp;

        // mirror plane
        for (int x = 0; x < _width / 2; x++) {
            for (int y = 0; y < _height; y++) {
                temp = new Tile2(_plane[y][x]);
                _plane[y][x] = new Tile2(_plane[y][_width-x-1]);
                _plane[_width-x-1][y] = new Tile2(temp);
            }
        }

        // mirror tiles
        for (int x = 0; x < _width; x++) {
            for (int y = 0; y < _height; y++) {
                _plane[y][x].mirror();
            }
        }
    }

    public String toString() {
        // Determine the maximum ID length
        int maxLength = 1; // Start with a minimum length to accommodate single-digit IDs
        for (int y = 0; y < _height; y++) {
            for (int x = 0; x < _width; x++) {
                if (_plane[y][x] != null) {
                    int idLength = String.valueOf(_plane[y][x]._id).length();
                    maxLength = Math.max(maxLength, idLength);
                }
            }
        }
    
        StringBuilder out = new StringBuilder();
        // Calculate the total length for the dashes
        int totalLength = (maxLength + 3) * _width - 1;
        String dashes = "-".repeat(totalLength + 2);
        String cellFormat = "| %-" + maxLength + "s "; // Dynamic padding based on maxLength
    
        out.append(dashes).append("\n");
        
        for (int y = 0; y < _height; y++) {
            for (int x = 0; x < _width; x++) {
                if (_plane[y][x] != null) {
                    out.append(String.format(cellFormat, _plane[y][x]._id));
                } else {
                    // Handle null Tiles within the plane
                    out.append(String.format(cellFormat, " "));
                }
            }
            out.append("|\n");
            out.append(dashes).append("\n");
        }
        
        return out.toString();
    }

    public String toStringAround(int x, int y) {
        checkBounds(x, y);
        
        // First, determine the maximum ID length, starting from 1 as the minimum for one-digit IDs
        int maxLength = 1;
        for (int dy = -1; dy <= 1; dy++) {
            for (int dx = -1; dx <= 1; dx++) {
                int nx = x + dx;
                int ny = y + dy;
                if (nx >= 0 && nx < _width && ny >= 0 && ny < _height) {
                    int idLength = String.valueOf(_plane[nx][ny]._id).length();
                    maxLength = Math.max(maxLength, idLength);
                }
            }
        }
    
        StringBuilder out = new StringBuilder();
        // Calculate the total length for the dashes based on the maxLength
        int totalLength = (maxLength + 3) * 3 - 1; // Adjust for each cell's padding and separator
        String dashes = "-".repeat(totalLength);
        String cellFormat = "| %-" + maxLength + "s "; // Dynamic padding based on maxLength
    
        out.append(dashes).append("\n");
        
        for (int dy = -1; dy <= 1; dy++) {
            for (int dx = -1; dx <= 1; dx++) {
                int nx = x + dx;
                int ny = y + dy;
                if (nx >= 0 && nx < _width && ny >= 0 && ny < _height) {
                    out.append(String.format(cellFormat, _plane[nx][ny]._id));
                } else {
                    // Handle potential edge cases without assuming null values
                    out.append(String.format(cellFormat, " "));
                }
            }
            out.append("|\n"); // Close the row
            out.append(dashes).append("\n");
        }
        
        return out.toString();
    }
}
