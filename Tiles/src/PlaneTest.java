import static org.junit.Assert.*;

import org.junit.Test;

public class PlaneTest {    
    @Test
    public void getTest() {
        Plane p0 = new Plane(4, 1);

        Tile t0 = new Tile(0);
        t0._isForwardSlash = true;

        p0.set(2, 0, t0);

        assertTrue(p0.get(2, 0).equals(t0));

        assertThrows(IndexOutOfBoundsException.class, () -> {
            p0.get(4, 0);
        });

        assertThrows(IndexOutOfBoundsException.class, () -> {
            p0.get(0, 1);
        });

        assertThrows(IndexOutOfBoundsException.class, () -> {
            p0.get(-1, 0);
        });

        assertThrows(IndexOutOfBoundsException.class, () -> {
            p0.get(0, -1);
        });
    }
    
    @Test
    public void setTest() {
        Plane p0 = new Plane(2, 3);

        Tile t0 = new Tile(0);
        t0._isForwardSlash = true;

        assertTrue((new Tile()).equals(p0.set(1, 0, t0)));

        Tile t1 = new Tile(1);
        t1._isVertical = true;

        assertTrue(t0.equals(p0.set(1, 0, t0)));

        assertThrows(IndexOutOfBoundsException.class, () -> {
            p0.set(2, 0, t0);
        });

        assertThrows(IndexOutOfBoundsException.class, () -> {
            p0.set(0, 3, t0);
        });

        assertThrows(IndexOutOfBoundsException.class, () -> {
            p0.set(-1, 0, t0);
        });

        assertThrows(IndexOutOfBoundsException.class, () -> {
            p0.set(0, -1, t0);
        });
    }

    @Test
    public void setAllTest() {
        Plane p0 = new Plane(12, 19);

        Tile t0 = new Tile(0);
        t0._isHorizontal = true;

        p0.setAll(t0);

        for (int x = 0; x < 12; x++) {
            for (int y = 0; y < 19; y++) {
                assertTrue(p0.get(x, y).equals(t0));
            }
        }
    }

    @Test
    public void planarSetTest() {
        Plane p0 = new Plane(5, 5);
        Plane p1 = new Plane(p0);

        // Top left planar set test
        Tile t0 = new Tile(0);
        t0._isForwardSlash = true;

        p0.planarSet(1, 1, t0);

        p1.set(1, 1, t0);
        p1.set(4, 1, t0);
        p1.set(1, 4, t0);
        p1.set(4, 4, t0);

        assertTrue(p0.equals(p1));

        // Top middle planar set test
        Tile t1 = new Tile(1);
        t1._isBackslash = true;

        p0.planarSet(2, 1, t1);

        p1.set(2, 1, t1);
        p1.set(2, 4, t1);

        assertTrue(p0.equals(p1));

        // Top right planar set test
        Tile t2 = new Tile(2);
        t2._isHorizontal = true;

        p0.planarSet(3, 1, t2);

        p1.set(3, 1, t2);
        p1.set(0, 1, t2);
        p1.set(3, 4, t2);
        p1.set(0, 4, t2);

        assertTrue(p0.equals(p1));

        // Left middle planar set test
        Tile t3 = new Tile(3);
        t3._isVertical = true;

        p0.planarSet(1, 2, t3);

        p1.set(1, 2, t3);
        p1.set(4, 2, t3);

        assertTrue(p0.equals(p1));

        // Middle planar set test
        Tile t4 = new Tile(4);
        t4._isBackslash = true;
        t4._isForwardSlash = true;

        p0.planarSet(2, 2, t4);

        p1.set(2, 2, t4);
        
        assertTrue(p0.equals(p1));

        // Right middle planar set test
        Tile t5 = new Tile(5);
        t5._isBackslash = true;
        t5._isHorizontal = true;

        p0.planarSet(3, 2, t5);

        p1.set(3, 2, t5);
        p1.set(0, 2, t5);

        assertTrue(p0.equals(p1));

        // Bottom left planar set test
        Tile t6 = new Tile(5);
        t6._isBackslash = true;
        t6._isVertical = true;

        p0.planarSet(1, 3, t6);

        p1.set(1, 3, t6);
        p1.set(4, 3, t6);
        p1.set(1, 0, t6);
        p1.set(4, 0, t6);

        assertTrue(p0.equals(p1));

        // Bottom middle planar set test
        Tile t7 = new Tile(5);
        t7._isForwardSlash = true;
        t7._isHorizontal = true;

        p0.planarSet(2, 3, t7);

        p1.set(2, 3, t7);
        p1.set(2, 0, t7);

        assertTrue(p0.equals(p1));

        // Bottom right planar set test
        Tile t8 = new Tile(5);
        t8._isForwardSlash = true;
        t8._isVertical = true;

        p0.planarSet(3, 3, t8);

        p1.set(3, 3, t8);
        p1.set(0, 3, t8);
        p1.set(3, 0, t8);
        p1.set(0, 0, t8);

        assertTrue(p0.equals(p1));

        // planar bounds check test
        assertThrows(IndexOutOfBoundsException.class, () -> {
            p0.planarSet(0, 1, t8);
        });

        assertThrows(IndexOutOfBoundsException.class, () -> {
            p0.planarSet(1, 0, t8);
        });

        assertThrows(IndexOutOfBoundsException.class, () -> {
            p0.planarSet(0, 4, t8);
        });

        assertThrows(IndexOutOfBoundsException.class, () -> {
            p0.planarSet(4, 0, t8);
        });
    }

    @Test
    public void equalsTest() {
        Plane p0 = new Plane(3, 7);
        Plane p1 = new Plane(2, 6);

        assertFalse(p0.equals(p1));

        Plane p2 = new Plane(3, 7);

        assertTrue(p0.equals(p2));

        Tile t0 = new Tile(0);
        t0._isVertical = true;

        Tile t1 = new Tile(1);
        t1._isVertical = true;

        p0.set(1, 3, t0);

        assertFalse(p0.equals(p2));

        p2.set(1, 3, t1);

        assertTrue(p0.equals(p2));
    }

    @Test
    public void isomorphicTest() {
        Plane p0 = new Plane(5, 5);
        Plane p1 = new Plane(3, 4);

        // different size test
        assertFalse(p0.isomorphic(p1));

        // translation only test
        Plane p2 = new Plane(p0);

        assertTrue(p0.isomorphic(p2));

        Tile vert = new Tile(0);
        vert._isVertical = true;

        p0.set(1, 2, vert);
        p2.set(0, 2, vert);

        assertTrue(p0.isomorphic(p2));

        Tile hori = new Tile(1);
        hori._isHorizontal = true;

        p0.set(4, 1, hori);
        p2.set(3, 1, hori);

        assertTrue(p0.isomorphic(p2));

        // rotation only test
        Plane p3 = new Plane(5, 5);
        Plane p4 = new Plane(5, 5);

        p3.set(0, 0, vert);
        p3.set(0, 1, vert);
        p3.set(0, 2, vert);

        p4.set(0, 0, hori);
        p4.set(1, 0, hori);
        p4.set(2, 0, hori);

        assertFalse(p3.isomorphic(p2));
        assertTrue(p3.isomorphic(p4));

        // mirror only test
        Plane p5 = new Plane(5, 5);
        Plane p6 = new Plane(5, 5);

        Tile back = new Tile(2);
        back._isBackslash = true;

        Tile forw = new Tile(3);
        forw._isForwardSlash = true;

        p5.set(0, 0, back);
        p5.set(0, 1, vert);
        p5.set(0, 2, forw);
        p5.set(1, 1, hori);

        p6.set(4, 0, forw);
        p6.set(4, 1, vert);
        p6.set(4, 2, back);
        p6.set(3, 1, hori);

        assertTrue(p5.isomorphic(p6));

        // rotation, translation, and mirror test
        Plane p7 = new Plane(5, 5);
        Plane p8 = new Plane(5, 5);

        p7.set(0, 0, back);
        p7.set(1, 1, back);
        p7.set(2, 2, back);

        p7.set(3, 3, vert);
        p7.set(3, 4, vert);

        p7.set(1, 4, hori);
        p7.set(2, 4, hori);

        p7.set(4, 1, forw);
        p7.set(4, 2, forw);

        // mirror, rotate, left 2, down 1
        p8.set(2, 0, back);
        p8.set(1, 4, back);
        p8.set(0, 3, back);

        p8.set(3, 2, hori);
        p8.set(4, 2, hori);

        p8.set(3, 3, vert);
        p8.set(3, 4, vert);

        p8.set(0, 1, forw);
        p8.set(1, 1, forw);

        System.out.println(p7.toString());
        System.out.println(p8.toString());

        assertTrue(p7.isomorphic(p8));
    }
}
