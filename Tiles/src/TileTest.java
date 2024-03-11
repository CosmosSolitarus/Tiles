import static org.junit.Assert.*;

import org.junit.Test;

public class TileTest {

    @Test
    public void connectsTest() {
        Tile t0 = new Tile(0);
        Tile t1 = new Tile(1);

        assertTrue(t0.connectsB(t1));
        assertTrue(t0.connectsF(t1));
        assertTrue(t0.connectsH(t1));
        assertTrue(t0.connectsV(t1));

        t0._isBackSlash = true;
        t0._isFrontSlash = true;
        t0._isHorizontal = true;
        t0._isVertical = true;

        assertFalse(t0.connectsB(t1));
        assertFalse(t0.connectsF(t1));
        assertFalse(t0.connectsH(t1));
        assertFalse(t0.connectsV(t1));

        t1._isBackSlash = true;
        t1._isFrontSlash = true;
        t1._isHorizontal = true;
        t1._isVertical = true;

        assertTrue(t0.connectsB(t1));
        assertTrue(t0.connectsF(t1));
        assertTrue(t0.connectsH(t1));
        assertTrue(t0.connectsV(t1));
    }

    @Test
    public void equalsTest() {
        Tile t0 = new Tile(0);
        Tile t1 = new Tile(1);

        assertTrue(t0.equals(t1));

        t0._isBackSlash = true;
        assertFalse(t0.equals(t1));

        t0._isFrontSlash = true;
        assertFalse(t0.equals(t1));

        t0._isHorizontal = true;
        assertFalse(t0.equals(t1));
        
        t0._isVertical = true;
        assertFalse(t0.equals(t1));

        t1._isBackSlash = true;
        t1._isFrontSlash = true;
        t1._isHorizontal = true;
        t1._isVertical = true;

        assertTrue(t0.equals(t1));

        Tile t2 = new Tile(t1);

        assertTrue(t1.equals(t2));
    }

    @Test
    public void rotateTest() {
        Tile t0 = new Tile(0);
        Tile t1 = new Tile(1);

        t0._isBackSlash = true;
        t0._isHorizontal = true;

        t0.rotate();

        t1._isFrontSlash = true;
        t1._isVertical = true;

        assertTrue(t0.equals(t1));

        
    }

    @Test
    public void mirrorTest() {
        Tile t0 = new Tile(0);
        Tile t1 = new Tile(1);
    }
}
