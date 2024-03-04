public class AdjacencyBuilder {
    public static final boolean[][] TilesLines = {
        // back, vert, horiz, forwd
        {false, false, false, false},   // A
        {false, false, false, true},    // B
        {false, false, true, false},    // C
        {false, false, true, true},     // D
        {false, true, false, false},    // E
        {false, true, false, true},     // F
        {false, true, true, false},     // G
        {false, true, true, true},      // H
        {true, false, false, false},    // I
        {true, false, false, true},     // J
        {true, false, true, false},     // K
        {true, false, true, true},      // L
        {true, true, false, false},     // M
        {true, true, false, true},      // N
        {true, true, true, false},      // O
        {true, true, true, true}        // P
    };

    public static void main(String[] args) {
        int n = TilesLines.length;

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                // back
                if (TilesLines[i][0] == TilesLines[j][0]) {
                    System.out.println("tile" + i + "._TLadj.add(" + j + ");");
                    System.out.println("tile" + i + "._BRadj.add(" + j + ");");
                    System.out.println("tile" + j + "._TLadj.add(" + i + ");");
                    System.out.println("tile" + j + "._BRadj.add(" + i + ");");
                }

                // vert
                if (TilesLines[i][1] == TilesLines[j][1]) {
                    System.out.println("tile" + i + "._TMadj.add(" + j + ");");
                    System.out.println("tile" + i + "._BMadj.add(" + j + ");");
                    System.out.println("tile" + j + "._TMadj.add(" + i + ");");
                    System.out.println("tile" + j + "._BMadj.add(" + i + ");");
                }

                // horiz
                if (TilesLines[i][2] == TilesLines[j][2]) {
                    System.out.println("tile" + i + "._MLadj.add(" + j + ");");
                    System.out.println("tile" + i + "._MRadj.add(" + j + ");");
                    System.out.println("tile" + j + "._MLadj.add(" + i + ");");
                    System.out.println("tile" + j + "._MRadj.add(" + i + ");");
                }

                // forwd
                if (TilesLines[i][3] == TilesLines[j][3]) {
                    System.out.println("tile" + i + "._TRadj.add(" + j + ");");
                    System.out.println("tile" + i + "._BLadj.add(" + j + ");");
                    System.out.println("tile" + j + "._TRadj.add(" + i + ");");
                    System.out.println("tile" + j + "._BLadj.add(" + i + ");");
                }
            }
        }
    }
}
