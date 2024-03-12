import static org.junit.Assert.*;

import org.junit.Test;

public class PlaneTest {
    @Test
    public void getTest() {

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

    }

    @Test
    public void equalsTest() {

    }

    @Test
    public void isomorphicTest() {

    }
}
