public class Tester2 {
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

        PlaneTiler2 pt = new PlaneTiler2(4, 4);

        if(true) {
            Tile2 t0 = new Tile2(0);
            Tile2 t1 = new Tile2(1);
            Tile2 t2 = new Tile2(2);
            Tile2 t3 = new Tile2(3);
            Tile2 t4 = new Tile2(4);
            Tile2 t5 = new Tile2(5);
            Tile2 t6 = new Tile2(6);
            Tile2 t7 = new Tile2(7);
            Tile2 t8 = new Tile2(8);
            Tile2 t9 = new Tile2(9);
            Tile2 t10 = new Tile2(10);
            Tile2 t11 = new Tile2(11);
            Tile2 t12 = new Tile2(12);
            Tile2 t13 = new Tile2(13);
            Tile2 t14 = new Tile2(14);
            Tile2 t15 = new Tile2(15);

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
