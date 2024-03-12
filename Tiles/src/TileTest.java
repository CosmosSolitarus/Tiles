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

        t0._isBackslash = true;
        t0._isForwardSlash = true;
        t0._isHorizontal = true;
        t0._isVertical = true;

        assertFalse(t0.connectsB(t1));
        assertFalse(t0.connectsF(t1));
        assertFalse(t0.connectsH(t1));
        assertFalse(t0.connectsV(t1));

        t1._isBackslash = true;
        t1._isForwardSlash = true;
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

        t0._isBackslash = true;
        assertFalse(t0.equals(t1));

        t0._isForwardSlash = true;
        assertFalse(t0.equals(t1));

        t0._isHorizontal = true;
        assertFalse(t0.equals(t1));
        
        t0._isVertical = true;
        assertFalse(t0.equals(t1));

        t1._isBackslash = true;
        t1._isForwardSlash = true;
        t1._isHorizontal = true;
        t1._isVertical = true;

        assertTrue(t0.equals(t1));

        Tile t2 = new Tile(t1);

        assertTrue(t1.equals(t2));
    }

    @Test
    public void rotateTest() {
        // Naming Scheme:
        // b - Backslash
        // f - Forward Slash
        // v - Vertical
        // h - Horizontal
        // # - degrees rotated clockwise

        // Backslash rotation test
        Tile b0 = new Tile(0);
        Tile b180 = new Tile(1);
        Tile b360;

        b0._isBackslash = true;
        b180._isForwardSlash = true;
        b360 = new Tile(b0);

        b0.rotate();

        assertTrue(b0.equals(b180));

        b0.rotate();

        assertTrue(b0.equals(b360));

        // Forward Slash rotation test
        Tile f0 = new Tile(0);
        Tile f180 = new Tile(1);
        Tile f360;

        f0._isForwardSlash = true;
        f180._isBackslash = true;
        f360 = new Tile(f0);

        f0.rotate();

        assertTrue(f0.equals(f180));

        f0.rotate();

        assertTrue(f0.equals(f360));

        // horizontal rotation test
        Tile h0 = new Tile(0);
        Tile h180 = new Tile(1);
        Tile h360;

        h0._isHorizontal = true;
        h180._isVertical = true;
        h360 = new Tile(h0);

        h0.rotate();

        assertTrue(h0.equals(h180));

        h0.rotate();

        assertTrue(h0.equals(h360));

        // vertical rotation test
        Tile v0 = new Tile(0);
        Tile v180 = new Tile(1);
        Tile v360;

        v0._isVertical = true;
        v180._isHorizontal = true;
        v360 = new Tile(v0);

        v0.rotate();

        assertTrue(v0.equals(v180));

        v0.rotate();

        assertTrue(v0.equals(v360));
    }

    @Test
    public void mirrorTest() {
                // Naming Scheme:
        // b - Backslash
        // f - Forwardslash
        // v - vertical
        // h - horizontal
        // # - times mirrored over y-axis

        // Backslash mirror test
        Tile b0 = new Tile(0);
        Tile b1 = new Tile(1);
        Tile b2;

        b0._isBackslash = true;
        b1._isForwardSlash = true;
        b2 = new Tile(b0);

        b0.mirror();

        assertTrue(b0.equals(b1));

        b0.mirror();

        assertTrue(b0.equals(b2));

        // Forwardslash mirror test
        Tile f0 = new Tile(0);
        Tile f1 = new Tile(1);
        Tile f2;

        f0._isForwardSlash = true;
        f1._isBackslash = true;
        f2 = new Tile(f0);

        f0.mirror();

        assertTrue(f0.equals(f1));

        f0.mirror();

        assertTrue(f0.equals(f2));

        // horizontal mirror test
        Tile h0 = new Tile(0);
        Tile h1 = new Tile(1);
        Tile h2;

        h0._isHorizontal = true;
        h1._isHorizontal = true;
        h2 = new Tile(h0);

        h0.mirror();

        assertTrue(h0.equals(h1));

        h0.mirror();

        assertTrue(h0.equals(h2));

        // vertical mirror test
        Tile v0 = new Tile(0);
        Tile v1 = new Tile(1);
        Tile v2;

        v0._isVertical = true;
        v1._isVertical = true;
        v2 = new Tile(v0);

        v0.mirror();

        assertTrue(v0.equals(v1));

        v0.mirror();

        assertTrue(v0.equals(v2));
    }
}
