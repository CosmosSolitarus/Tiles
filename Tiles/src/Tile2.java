public class Tile2 {
    public int _id;
    public boolean _isHorizontal;
    public boolean _isVertical;
    public boolean _isFrontSlash;
    public boolean _isBackSlash;

    public Tile2() {
        _id = -1;
        init();
    }

    public Tile2(int id) {
        _id = id;
        init();
    }

    public Tile2(Tile2 that) {
        this._id = that._id;
        this._isHorizontal  =   that._isHorizontal;
        this._isVertical    =   that._isVertical;
        this._isFrontSlash  =   that._isFrontSlash;
        this._isBackSlash   =   that._isBackSlash;
    }

    private void init() {
        _isHorizontal   =   false;
        _isVertical     =   false;
        _isFrontSlash   =   false;
        _isBackSlash    =   false; 
    }

    public boolean connectsH(Tile2 that) {
        return this._isHorizontal   ==  that._isHorizontal  ||  that._id == -1;
    }

    public boolean connectsV(Tile2 that) {
        return this._isVertical     ==  that._isVertical    ||  that._id == -1;
    }
    
    public boolean connectsF(Tile2 that) {
        return this._isFrontSlash   ==  that._isFrontSlash  ||  that._id == -1;
    }

    public boolean connectsB(Tile2 that) {
        return this._isBackSlash    ==  that._isBackSlash   ||  that._id == -1;
    }

    public boolean equals(Tile2 that) {
        return  this._isHorizontal  ==  that._isHorizontal   &&
                this._isVertical    ==  that._isVertical     &&
                this._isFrontSlash  ==  that._isFrontSlash   &&
                this._isBackSlash   ==  that._isBackSlash;
    }

    /**
     * Rotates the tile by 90 degrees clockwise
     */
    public void rotate() {
        boolean temp = _isHorizontal;

        _isHorizontal = _isVertical;
        _isVertical = temp;

        temp = _isBackSlash;

        _isBackSlash = _isFrontSlash;
        _isFrontSlash = temp;
    }

    /**
     * Mirrors the tile over the y-axis
     */
    public void mirror() {
        boolean temp = _isBackSlash;

        _isBackSlash = _isFrontSlash;
        _isFrontSlash = temp;
    }

    public String toString() {
        // Convert booleans and id to String
        String isBackSlashStr = String.valueOf(_isBackSlash);
        String isVerticalStr = String.valueOf(_isVertical);
        String isFrontSlashStr = String.valueOf(_isFrontSlash);
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
