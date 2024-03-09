public class Tester2 {
    public static void main(String[] args) {
        Plane2 plane = new Plane2(2, 2);

        System.out.println(plane.toString());

        Tile2 tile0 = new Tile2(2000);
        tile0._isHorizontal = true;
        plane.set(1, 0, tile0);
        System.out.println(plane.toString());

        Tile2 tile1 = new Tile2(80085);
        tile1._isVertical = true;
        //plane.set(1, 1, tile1);
        //System.out.println(plane.toString());

        //plane.setAll(tile1);
        //System.out.println(plane.toString());


        Plane2 other = new Plane2(2, 2);

        //other.set(0, 1, tile0);
        other.set(1, 1, tile1);

        System.out.println(other.toString());

        System.out.println(plane.equals(other));
        System.out.println(plane.isomorphic(other));
    }
}
