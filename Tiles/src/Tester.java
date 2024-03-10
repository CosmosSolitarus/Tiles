public class Tester {
    public static void main(String[] args) {
        /*
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
        */

        PlaneTiler pt = new PlaneTiler(4, 4);

        if(true) {
            Tile t0 = new Tile(0);
            Tile t1 = new Tile(1);
            Tile t2 = new Tile(2);
            Tile t3 = new Tile(3);
            Tile t4 = new Tile(4);
            Tile t5 = new Tile(5);
            Tile t6 = new Tile(6);
            Tile t7 = new Tile(7);
            Tile t8 = new Tile(8);
            Tile t9 = new Tile(9);
            Tile t10 = new Tile(10);
            Tile t11 = new Tile(11);
            Tile t12 = new Tile(12);
            Tile t13 = new Tile(13);
            Tile t14 = new Tile(14);
            Tile t15 = new Tile(15);

            t0._isBackSlash = false;
            t0._isFrontSlash = false;
            t0._isHorizontal = false;
            t0._isVertical = false;

            t1._isBackSlash = true;
            t1._isFrontSlash = false;
            t1._isHorizontal = false;
            t1._isVertical = false;

            t2._isBackSlash = false;
            t2._isFrontSlash = true;
            t2._isHorizontal = false;
            t2._isVertical = false;

            t3._isBackSlash = false;
            t3._isFrontSlash = false;
            t3._isHorizontal = true;
            t3._isVertical = false;

            t4._isBackSlash = false;
            t4._isFrontSlash = false;
            t4._isHorizontal = false;
            t4._isVertical = true;

            t5._isBackSlash = true;
            t5._isFrontSlash = true;
            t5._isHorizontal = false;
            t5._isVertical = false;

            t6._isBackSlash = true;
            t6._isFrontSlash = false;
            t6._isHorizontal = true;
            t6._isVertical = false;

            t7._isBackSlash = true;
            t7._isFrontSlash = false;
            t7._isHorizontal = false;
            t7._isVertical = true;

            t8._isBackSlash = false;
            t8._isFrontSlash = true;
            t8._isHorizontal = true;
            t8._isVertical = false;

            t9._isBackSlash = false;
            t9._isFrontSlash = true;
            t9._isHorizontal = false;
            t9._isVertical = true;

            t10._isBackSlash = false;
            t10._isFrontSlash = false;
            t10._isHorizontal = true;
            t10._isVertical = true;

            t11._isBackSlash = true;
            t11._isFrontSlash = true;
            t11._isHorizontal = true;
            t11._isVertical = false;

            t12._isBackSlash = true;
            t12._isFrontSlash = true;
            t12._isHorizontal = false;
            t12._isVertical = true;

            t13._isBackSlash = true;
            t13._isFrontSlash = false;
            t13._isHorizontal = true;
            t13._isVertical = true;

            t14._isBackSlash = false;
            t14._isFrontSlash = true;
            t14._isHorizontal = true;
            t14._isVertical = true;

            t15._isBackSlash = true;
            t15._isFrontSlash = true;
            t15._isHorizontal = true;
            t15._isVertical = true;

            pt.add(t0);
            pt.add(t1);
            pt.add(t2);
            pt.add(t3);
            pt.add(t4);
            pt.add(t5);
            pt.add(t6);
            pt.add(t7);
            pt.add(t8);
            pt.add(t9);
            pt.add(t10);
            pt.add(t11);
            pt.add(t12);
            pt.add(t13);
            pt.add(t14);
            pt.add(t15);
        }

        pt.tiler();

        System.out.println(pt.printSolutions());
    }
}
