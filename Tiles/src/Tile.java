import java.util.ArrayList;

public class Tile {
    public String _name;
    public int _id;
    public ArrayList<Integer> _TLadj;
    public ArrayList<Integer> _TMadj;
    public ArrayList<Integer> _TRadj;
    public ArrayList<Integer> _MLadj;
    public ArrayList<Integer> _MRadj;
    public ArrayList<Integer> _BLadj;
    public ArrayList<Integer> _BMadj;
    public ArrayList<Integer> _BRadj;

    public Tile() {
        _name = null;
        _id = -1;
        init();
    }

    public Tile(String name, int id) {
        _name = name;
        _id = id;
        init();
    }

    private void init() {
        _TLadj = new ArrayList<>();
        _TMadj = new ArrayList<>();
        _TRadj = new ArrayList<>();
        _MLadj = new ArrayList<>();
        _MRadj = new ArrayList<>();
        _BLadj = new ArrayList<>();
        _BMadj = new ArrayList<>();
        _BRadj = new ArrayList<>(); 
    }

    public void addAll(Tile tile) {
        _TLadj.add(tile._id);
        _TMadj.add(tile._id);
        _TRadj.add(tile._id);
        _MLadj.add(tile._id);
        _MRadj.add(tile._id);
        _BLadj.add(tile._id);
        _BMadj.add(tile._id);
        _BRadj.add(tile._id); 
    }
}
