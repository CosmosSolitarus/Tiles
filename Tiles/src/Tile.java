/* A Tile defines the connection rules to other Tiles and records whether or not
 * it is placed in a Plane. Tiles can connect to another Tile if both Tiles share
 * the same line, or neither Tile has the line. All Tiles can connect to the 
 * universally connecting Tile of ID -1.
 * 
 * @Author  Jack Roberts
 * 14 March 2024
 */
public class Tile {
    public int _id;
    public boolean _isPlaced;
    public boolean _isHorizontal;
    public boolean _isVertical;
    public boolean _isForwardSlash;
    public boolean _isBackslash;

    /**
     * Default constructor.
     */
    public Tile() {
        this(-1);
    }

    /**
     * Overloaded constructor. Assumes user knows
     * that an ID of -1 is treated as a universally
     * connecting Tile.
     * @param id    the ID of the Tile
     */
    public Tile(int id) {
        _id = id;
        init();
    }

    /**
     * Copy constructor. Deep copies another Tile exactly.
     * @param that  the Tile being copied.
     */
    public Tile(Tile that) {
        this._id = that._id;
        this._isPlaced = that._isPlaced;
        this._isHorizontal = that._isHorizontal;
        this._isVertical = that._isVertical;
        this._isForwardSlash = that._isForwardSlash;
        this._isBackslash = that._isBackslash;
    }

    /**
     * Initializes a Tile so that it is not placed and
     * has no lines.
     */
    private void init() {
        _isPlaced = false;
        _isHorizontal = false;
        _isVertical = false;
        _isForwardSlash = false;
        _isBackslash = false; 
    }

    /**
     * Determines if this Tile can connect to another
     * Tile horizontally (i.e. this Tile is to the
     * left or right of the other Tile).
     * @param that  the other Tile
     * @return      whether the Tiles connect
     */
    public boolean connectsH(Tile that) {
        return this._isHorizontal == that._isHorizontal || that._id == -1;
    }

    /**
     * Determines if this Tile can connect to another
     * Tile vertically (i.e. this Tile is above or
     * below the other Tile).
     * @param that  the other Tile
     * @return      whether the Tiles connect
     */
    public boolean connectsV(Tile that) {
        return this._isVertical == that._isVertical || that._id == -1;
    }
    
    /**
     * Determines if this Tile can connect to another
     * Tile diagonally equivalent to a forward slash
     * (i.e. this Tile is to the bottom left or top 
     * right of the other Tile).
     * @param that  the other Tile
     * @return      whether the Tiles connect
     */
    public boolean connectsF(Tile that) {
        return this._isForwardSlash == that._isForwardSlash || that._id == -1;
    }

    /**
     * Determines if this Tile can connect to another
     * Tile diagonally equivalent to a backslash (i.e.
     * this Tile is to the top left or bottom right of
     * the other Tile).
     * @param that  the other Tile
     * @return      whether the Tiles connect
     */
    public boolean connectsB(Tile that) {
        return this._isBackslash == that._isBackslash || that._id == -1;
    }

    /**
     * Determines if two Tiles are equal. A Tile
     * equals another Tile if both connect in the
     * same ways to other Tiles.
     * @param that  the other Tile
     * @return      whether the Tiles are equal
     */
    public boolean equals(Tile that) {
        return  this._isHorizontal == that._isHorizontal &&
                this._isVertical == that._isVertical &&
                this._isForwardSlash == that._isForwardSlash &&
                this._isBackslash == that._isBackslash;
    }

    /**
     * Rotates the Tile by 90 degrees clockwise.
     */
    public void rotate() {
        boolean temp = _isHorizontal;

        _isHorizontal = _isVertical;
        _isVertical = temp;

        temp = _isBackslash;

        _isBackslash = _isForwardSlash;
        _isForwardSlash = temp;
    }

    /**
     * Mirrors the Tile over the y-axis.
     */
    public void mirror() {
        boolean temp = _isBackslash;

        _isBackslash = _isForwardSlash;
        _isForwardSlash = temp;
    }

    /**
     * Converts the Tile's connection properties into
     * a two-character ascii representation. The conversion 
     * is as follows:
     * -- First Character --
     * The symbol '/' is equivalent to a forward diagonal.
     * The symbol '\' is equivalent to a backward diagonal.
     * The symbol 'X' is equivalent to both a forward
     *  diagonal and a backward diagonal.
     * 
     * -- Second Character --
     * The symbol '-' is equivalent to a horizontal line.
     * The symbol '|' is equivalent to a vertical line.
     * The symbol '+' is equivalent to both a horizontal
     *  and vertical line.
     * 
     * The symbol ' ' is used as a placeholder if one of
     *  the characters is entirely empty.
     * The symbols ' *' are used if both characters are
     *  entirely empty.
     * @return  the ascii representation
     */
    public String ascii() {
        if (_id == -1) return "  ";

        String out = "";

        if (_isBackslash && _isForwardSlash) {
            out += "X";
        } else if (_isBackslash) {
            out += "\\";
        } else if (_isForwardSlash) {
            out += "/";
        } else {
            out += " ";
        }

        if (_isHorizontal && _isVertical) {
            out += "+";
        } else if (_isHorizontal) {
            out += "-";
        } else if (_isVertical) {
            out += "|";
        } else {
            out += " ";
        }

        if (out.equals("  ")) {
            out = " *";
        }

        return out;
    }

    /**
     * toString method for testing purposes. Prints the
     * ascii representation of the Tile and true or false
     * whether the Tile has a line in a specific direction. 
     * Generated mostly by ChatGPT.
     */
    public String toString() {
        // Convert booleans and id to String
        String isBackSlashStr = String.valueOf(_isBackslash);
        String isVerticalStr = String.valueOf(_isVertical);
        String isFrontSlashStr = String.valueOf(_isForwardSlash);
        String isHorizontalStr = String.valueOf(_isHorizontal);
        String idStr = ascii();
        
        // Find the maximum length
        int maxLength = Math.max(isBackSlashStr.length(), Math.max(isVerticalStr.length(),
                        Math.max(isFrontSlashStr.length(), Math.max(isHorizontalStr.length(), 
                        idStr.length()))));
        
        // Calculate the total length for the dashes
        // Assuming 3 spaces for padding on each side and 3 for separators
        int totalLength = maxLength * 3 + 3 * 4 - 2;
        
        // Create a string of dashes
        String dashes = "-".repeat(totalLength);
        
        // Generate the output with dynamically sized dashes
        String out = "";
        out += dashes + "\n";
        out += String.format("| %-" + maxLength + "s | %-" + maxLength + "s | %-" + maxLength + "s |\n",
                             isBackSlashStr, isVerticalStr, isFrontSlashStr);
        out += dashes + "\n";
        out += String.format("| %-" + maxLength + "s | %-" + maxLength + "s | %-" + maxLength + "s |\n",
                             isHorizontalStr, idStr, isHorizontalStr);
        out += dashes + "\n";
        out += String.format("| %-" + maxLength + "s | %-" + maxLength + "s | %-" + maxLength + "s |\n",
                             isFrontSlashStr, isVerticalStr, isBackSlashStr);
        out += dashes + "\n";
        
        return out;
    }
}
