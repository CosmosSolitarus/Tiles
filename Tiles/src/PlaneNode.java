public class PlaneNode {
    public Tile _tile;
    public int _x;
    public int _y;
    public PlaneNode _TL;
    public PlaneNode _TM;
    public PlaneNode _TR;
    public PlaneNode _ML;
    public PlaneNode _MR;
    public PlaneNode _BL;
    public PlaneNode _BM;
    public PlaneNode _BR;

    public PlaneNode(int x, int y) {
        _x = x;
        _y = y;
        _tile = new Tile();
        init();
    }

    public PlaneNode(int x, int y, Tile tile) {
        _x = x;
        _y = y;
        _tile = tile;
        init();
    }

    private void init() {
        _TL = null;
        _TM = null;
        _TR = null;
        _ML = null;
        _MR = null;
        _BL = null;
        _BM = null;
        _BR = null;
    }

    public String toString() {
        String out = "";

        out += _TL._tile._name + " | " + _TM._tile._name + " | " + _TR._tile._name + "\n";
        out += "----------------------\n";
        out += _ML._tile._name + " | " + _tile._name + " | " + _MR._tile._name + "\n";
        out += "----------------------\n";
        out += _BL._tile._name + " | " + _BM._tile._name + " | " + _BR._tile._name + "\n";

        return out;
    }
}