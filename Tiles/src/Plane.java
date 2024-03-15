/* A plane is a n*m array of Tile objects.
 * 
 * @Author  Jack Roberts
 * 14 March 2024
 */
public class Plane {
    private int _width;
    private int _height;
    private Tile[][] _plane;

    /**
     * Default constructor.
     */
    public Plane() {
        _width = 0;
        _height = 0;
        init();
    }

    /**
     * Overloaded constructor. Width and height must
     * be non-negative.
     * @param width     the width of the plane
     * @param height    the height of the plane
     */
    public Plane(int width, int height) {
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

    /**
     * Copy constructor. Deep copies another plane exactly.
     * @param that  the plane being copied
     */
    public Plane(Plane that) {
        this(that._width, that._height);

        for (int x = 0; x < _width; x++) {
            for (int y = 0; y < _height; y++) {
                this._plane[y][x] = new Tile(that._plane[y][x]);
            }
        }
    }

    /**
     * Initializes a plane so that all Tiles are default tiles.
     */
    private void init() {
        _plane = new Tile[_height][_width];

        // create blank tiles in the plane
        for (int x = 0; x < _width; x++) {
            for (int y = 0; y < _height; y++) {
                _plane[y][x] = new Tile();
            }
        }
    }

    /**
     * Ensures (x,y) is a valid coordinate in the Plane.
     * Throws an IndexOutOfBoundsException if bounds violated.
     * @param x the x-coordinate
     * @param y the y-coordinate
     */
    private void checkBounds(int x, int y) {
        if (x < 0 || x >= _width || y < 0 || y >= _height) {
            throw new IndexOutOfBoundsException("Invalid coordinates: (" + x + ", " + y + "). 0 <= x <= " + (_width-1) + " and 0 <= y <= " + (_height-1) + ".");
        } 
    }

    /**
     * Ensures (x,y) is a valid coordinate in the Plane
     * for the purpose of using planarSet(). Throws an 
     * IndexOutOfBoundsException if bounds violated.
     * @param x the x-coordinate
     * @param y the y-coordinate
     */
    private void checkPlanarBounds(int x, int y) {
        if (x < 1 || x >= _width-1 || y < 1 || y >= _height-1) {
            throw new IndexOutOfBoundsException("Invalid coordinates (Planar): (" + x + ", " + y + "). 1 <= x <= " + (_width-2) + " and 1 <= y <= " + (_height-2) + ".");
        } 
    }

    /**
     * Sets (x,y) in the Plane to a specified Tile. Returns
     * the Tile that was in that location previously.
     * @param x the x-coordinate the Tile will be placed at
     * @param y the y-coordinate the Tile will be placed at
     * @param tile  the Tile being placed
     * @return  the previous Tile at (x,y)
     */
    public Tile set(int x, int y, Tile tile) {
        checkBounds(x, y);

        Tile prev = _plane[y][x];

        _plane[y][x] = tile;

        return prev;
    }

    /**
     * Sets all Tiles in the Plane to a specified Tile.
     * @param tile  the Tile being placed
     */
    public void setAll(Tile tile) {
        for (int x = 0; x < _width; x++) {
            for (int y = 0; y < _height; y++) {
                set(x, y, tile);
            }
        }
    }

    /**
     * Sets (x,y) in the Plane to a specified Tile. Assumes
     * the Plane is a torus, i.e. the top wraps around to the
     * bottom, the left to the right, etc. Used for the
     * purpose of checking if Tiles on one side connect with
     * Tiles on the opposite side.
     * @param x the x-coordinate the Tile will be placed at
     * @param y the y-coordinate the Tile will be placed at
     * @param tile  the Tile being placed
     * @return`the  the previous Tile at (x,y)
     */
    public Tile planarSet(int x, int y, Tile tile) {
        checkPlanarBounds(x, y);
        
        Tile prev = set(x, y, tile);
        
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

    /**
     * Returns a Tile at a specified (x,y)
     * @param x the x-coordinate of the Tile
     * @param y the y-coordinate of the Tile
     * @return  the Tile at (x,y)
     */
    public Tile get(int x, int y) {
        checkBounds(x, y);
        return _plane[y][x];
    }

    /**
     * Determines if two Planes are equal. A plane
     * equals another Plane if the Tiles at each location
     * in both Planes are equal.
     * @param that  the other Plane
     * @return  true if the Planes are equal; false otherwise
     */
    public boolean equals(Plane that) {
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

    /**
     * Determines if two Planes are isomorhic. A Plane is 
     * isomorphic to another Plane if the Planes are equal
     * following some series of rotation, translation, and
     * mirroring.
     * @param plane the other Plane
     * @return  true if the Planes are isomorphic; false otherwise
     */
    public boolean isomorphic(Plane plane) {
        if (_width != plane._width) return false;
        if (_height != plane._height) return false;
        if (_width != _height) return false;  // avoiding non-n*n planes for now

        // Plane gets altered by translation, rotation, and
        // mirror functions, so a copy is made and checked
        // for equality, leaving the original Plane unchanged.
        Plane that = new Plane(plane);

        if (translated(that)) return true;

        that.rotate();
        if (translated(that)) return true;

        that.rotate();
        if (translated(that)) return true;

        that.rotate();
        if (translated(that)) return true;

        that.rotate();
        that.mirror();
        if (translated(that)) return true;
        
        that.rotate();
        if (translated(that)) return true;
        
        that.rotate();
        if (translated(that)) return true;

        that.rotate();
        if (translated(that)) return true;

        return false;
    }

    /**
     * Helper method for isomorphic(). Determines if 
     * two Planes are equal by any sequence of 1 Tile 
     * shifts down or 1 Tile shift right. Alters the
     * given Plane 'that'.
     * @param that  the other Plane
     * @return  whether the two Planes are equal by some translation
     */
    private boolean translated(Plane that) {
        for (int x = 0; x < _width; x++) { 
            for (int y = 0; y < _height; y++) {                
                if (equals(that)) return true;
                that.shiftDown();
            }
            that.shiftRight();
        }
        return false;
    }

    /**
     * Helper method for translated(). Shifts all
     * Tiles in the Plane one to the right. Tiles
     * on the rightmost edge are placed on the
     * leftmost edge.
     */
    private void shiftRight() {
        Tile[] temp = new Tile[_height];

        for (int y = 0; y < _height; y++) {
            temp[y] = new Tile(_plane[y][_width-1]);
        }

        for (int x = _width - 1; x > 0; x--) {
            for (int y = 0; y < _height; y++) {
                _plane[y][x] = new Tile(_plane[y][x-1]);
            }
        }

        for (int y = 0; y < _height; y++) {
            _plane[y][0] = new Tile(temp[y]);
        }
    }

    /**
     * Helper method for translated(). Shifts all
     * Tiles in the Plane one down. Tiles on the
     * bottommost edge are placed on the top edge.
     */
    private void shiftDown() {
        Tile[] temp = new Tile[_width];

        for (int x = 0; x < _width; x++) {
            temp[x] = new Tile(_plane[_height-1][x]);
        }

        for (int x = 0; x < _width; x++) {
            for (int y = _height - 1; y >= 1; y--) {
                _plane[y][x] = new Tile(_plane[y-1][x]);
            }
        }

        for (int x = 0; x < _width; x++) {
            _plane[0][x] = new Tile(temp[x]);
        }
    }

    /**
     * Helper method for isomorphic(). Rotates the 
     * Plane and each Tile by 90 degrees clockwise.
     * Only allows n*n planes (currently handled by
     * isomorphic()).
     */
    private void rotate() {
        // if (_width != _height) return;

        Tile[][] plane = new Tile[_height][_width];
        Tile tile;

        for (int x = 0; x < _width; x++) {
            for (int y = 0; y < _height; y++) {
                tile = new Tile(_plane[x][y]);
                tile.rotate();
                plane[y][_width-x-1] = new Tile(tile);
            }
        }

        for (int x = 0; x < _width; x++) {
            for (int y = 0; y < _height; y++) {
                _plane[y][x] = new Tile(plane[y][x]);
            }
        }
    }

    /**
     * Helper method for isomorphic(). Mirrors the 
     * Plane and each Tile over the y-axis.
     */
    private void mirror() {
        Tile[][] plane = new Tile[_height][_width];
        Tile tile;

        for (int x = 0; x < _width; x++) {
            for (int y = 0; y < _height; y++) {
                tile = new Tile(_plane[y][x]);
                tile.mirror();
                plane[y][_width-x-1] = new Tile(tile);
            }
        }

        for (int x = 0; x < _width; x++) {
            for (int y = 0; y < _height; y++) {
                _plane[y][x] = new Tile(plane[y][x]);
            }
        }
    }

    /**
     * toString method for testing purposes. Prints the
     * entire Plane.
     */
    public String toString() {
        // Determine the maximum ID length
        int maxLength = 1; // Start with a minimum length to accommodate single-digit IDs
        for (int y = 0; y < _height; y++) {
            for (int x = 0; x < _width; x++) {
                if (_plane[y][x] != null) {
                    int idLength = _plane[y][x].ascii().length();
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
                    out.append(String.format(cellFormat, _plane[y][x].ascii()));
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

    /**
     * toString method for testing purposes. Prints the 
     * 
     */
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
