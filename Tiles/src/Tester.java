public class Tester {
    public static void main(String[] args) {
        PlaneTiler PT = new PlaneTiler(3, 3);
        
        Tile tile0 = new Tile("A", 0);
        Tile tile1 = new Tile("B", 1);
        Tile tile2 = new Tile("C", 2);
        Tile tile3 = new Tile("D", 3);
        Tile tile4 = new Tile("E", 4);
        Tile tile5 = new Tile("F", 5);
        Tile tile6 = new Tile("G", 6);
        Tile tile7 = new Tile("H", 7);
        Tile tile8 = new Tile("I", 8);

        tile0.addAll(tile1);
        tile0.addAll(tile2);
        tile0.addAll(tile3);
        tile0.addAll(tile4);
        tile0.addAll(tile5);
        tile0.addAll(tile6);
        tile0.addAll(tile7);
        tile0.addAll(tile8);

        tile1.addAll(tile0);
        tile1.addAll(tile2);
        tile1.addAll(tile3);
        tile1.addAll(tile4);
        tile1.addAll(tile5);
        tile1.addAll(tile6);
        tile1.addAll(tile7);
        tile1.addAll(tile8);

        tile2.addAll(tile3);
        tile2.addAll(tile4);
        tile2.addAll(tile5);
        tile2.addAll(tile6);
        tile2.addAll(tile7);
        tile2.addAll(tile8);

        tile3.addAll(tile4);
        tile3.addAll(tile5);
        tile3.addAll(tile6);
        tile3.addAll(tile7);
        tile3.addAll(tile8);

        tile4.addAll(tile5);
        tile4.addAll(tile6);
        tile4.addAll(tile7);
        tile4.addAll(tile8);

        tile5.addAll(tile6);
        tile5.addAll(tile7);
        tile5.addAll(tile8);

        tile6.addAll(tile7);
        tile6.addAll(tile8);

        tile7.addAll(tile8);

        PT.add(tile0);
        PT.add(tile1);
        PT.add(tile2);
        PT.add(tile3);
        PT.add(tile4);
        PT.add(tile5);
        PT.add(tile6);
        PT.add(tile7);
        PT.add(tile8);

        System.out.println(PT.tiler());
        //PT.tiler();
    }
}
