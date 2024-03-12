public class Tile {
    public int _id;
    public boolean _isPlaced;
    public boolean _isHorizontal;
    public boolean _isVertical;
    public boolean _isForwardSlash;
    public boolean _isBackslash;

    public Tile() {
        _id = -1;
        init();
    }

    public Tile(int id) {
        _id = id;
        init();
    }

    public Tile(Tile that) {
        this._id = that._id;
        this._isPlaced      =   that._isPlaced;
        this._isHorizontal  =   that._isHorizontal;
        this._isVertical    =   that._isVertical;
        this._isForwardSlash  =   that._isForwardSlash;
        this._isBackslash   =   that._isBackslash;
    }

    private void init() {
        _isPlaced       =   false;
        _isHorizontal   =   false;
        _isVertical     =   false;
        _isForwardSlash   =   false;
        _isBackslash    =   false; 
    }

    public boolean connectsH(Tile that) {
        return this._isHorizontal   ==  that._isHorizontal  ||  that._id == -1;
    }

    public boolean connectsV(Tile that) {
        return this._isVertical     ==  that._isVertical    ||  that._id == -1;
    }
    
    public boolean connectsF(Tile that) {
        return this._isForwardSlash   ==  that._isForwardSlash  ||  that._id == -1;
    }

    public boolean connectsB(Tile that) {
        return this._isBackslash    ==  that._isBackslash   ||  that._id == -1;
    }

    public boolean equals(Tile that) {
        return  this._isHorizontal  ==  that._isHorizontal   &&
                this._isVertical    ==  that._isVertical     &&
                this._isForwardSlash  ==  that._isForwardSlash   &&
                this._isBackslash   ==  that._isBackslash;
    }

    /**
     * Rotates the tile by 90 degrees clockwise
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
     * Mirrors the tile over the y-axis
     */
    public void mirror() {
        boolean temp = _isBackslash;

        _isBackslash = _isForwardSlash;
        _isForwardSlash = temp;
    }

    public String toString() {
        // Convert booleans and id to String
        String isBackSlashStr = String.valueOf(_isBackslash);
        String isVerticalStr = String.valueOf(_isVertical);
        String isFrontSlashStr = String.valueOf(_isForwardSlash);
        String isHorizontalStr = String.valueOf(_isHorizontal);
        String idStr = String.valueOf(_id);
        
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
