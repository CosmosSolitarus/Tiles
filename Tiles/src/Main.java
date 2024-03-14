public class Main {
    public static void main(String[] args) {
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

            t0._isBackslash = false;
            t0._isForwardSlash = false;
            t0._isHorizontal = false;
            t0._isVertical = false;

            t1._isBackslash = true;
            t1._isForwardSlash = false;
            t1._isHorizontal = false;
            t1._isVertical = false;

            t2._isBackslash = false;
            t2._isForwardSlash = true;
            t2._isHorizontal = false;
            t2._isVertical = false;

            t3._isBackslash = false;
            t3._isForwardSlash = false;
            t3._isHorizontal = true;
            t3._isVertical = false;

            t4._isBackslash = false;
            t4._isForwardSlash = false;
            t4._isHorizontal = false;
            t4._isVertical = true;

            t5._isBackslash = true;
            t5._isForwardSlash = true;
            t5._isHorizontal = false;
            t5._isVertical = false;

            t6._isBackslash = true;
            t6._isForwardSlash = false;
            t6._isHorizontal = true;
            t6._isVertical = false;

            t7._isBackslash = true;
            t7._isForwardSlash = false;
            t7._isHorizontal = false;
            t7._isVertical = true;

            t8._isBackslash = false;
            t8._isForwardSlash = true;
            t8._isHorizontal = true;
            t8._isVertical = false;

            t9._isBackslash = false;
            t9._isForwardSlash = true;
            t9._isHorizontal = false;
            t9._isVertical = true;

            t10._isBackslash = false;
            t10._isForwardSlash = false;
            t10._isHorizontal = true;
            t10._isVertical = true;

            t11._isBackslash = true;
            t11._isForwardSlash = true;
            t11._isHorizontal = true;
            t11._isVertical = false;

            t12._isBackslash = true;
            t12._isForwardSlash = true;
            t12._isHorizontal = false;
            t12._isVertical = true;

            t13._isBackslash = true;
            t13._isForwardSlash = false;
            t13._isHorizontal = true;
            t13._isVertical = true;

            t14._isBackslash = false;
            t14._isForwardSlash = true;
            t14._isHorizontal = true;
            t14._isVertical = true;

            t15._isBackslash = true;
            t15._isForwardSlash = true;
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

        System.out.println("Number of Solutions: " + pt._solutions.size());
        System.out.println(pt.printSolutions());
    }
}
