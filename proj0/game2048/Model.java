package game2048;

import java.util.Formatter;
import java.util.Observable;


/** The state of a game of 2048.
 *  @author Wan Ying Xuan
 */
public class Model extends Observable {
    /** Current contents of the board. */
    private Board board;
    /** Current score. */
    private int score;
    /** Maximum score so far.  Updated when game ends. */
    private int maxScore;
    /** True iff game is ended. */
    private boolean gameOver;

    /* Coordinate System: column C, row R of the board (where row 0,
     * column 0 is the lower-left corner of the board) will correspond
     * to board.tile(c, r).  Be careful! It works like (x, y) coordinates.
     */

    /** Largest piece value. */
    public static final int MAX_PIECE = 2048;

    /** A new 2048 game on a board of size SIZE with no pieces
     *  and score 0. */
    public Model(int size) {
        board = new Board(size);
        score = maxScore = 0;
        gameOver = false;
    }

    /** A new 2048 game where RAWVALUES contain the values of the tiles
     * (0 if null). VALUES is indexed by (row, col) with (0, 0) corresponding
     * to the bottom-left corner. Used for testing purposes. */
    public Model(int[][] rawValues, int score, int maxScore, boolean gameOver) {
        int size = rawValues.length;
        board = new Board(rawValues, score);
        this.score = score;
        this.maxScore = maxScore;
        this.gameOver = gameOver;
    }

    /** Return the current Tile at (COL, ROW), where 0 <= ROW < size(),
     *  0 <= COL < size(). Returns null if there is no tile there.
     *  Used for testing. Should be deprecated and removed.
     *  */
    public Tile tile(int col, int row) {
        return board.tile(col, row);
    }

    /** Return the number of squares on one side of the board.
     *  Used for testing. Should be deprecated and removed. */
    public int size() {
        return board.size();
    }

    /** Return true iff the game is over (there are no moves, or
     *  there is a tile with value 2048 on the board). */
    public boolean gameOver() {
        checkGameOver();
        if (gameOver) {
            maxScore = Math.max(score, maxScore);
        }
        return gameOver;
    }

    /** Return the current score. */
    public int score() {
        return score;
    }

    /** Return the current maximum game score (updated at end of game). */
    public int maxScore() {
        return maxScore;
    }

    /** Clear the board to empty and reset the score. */
    public void clear() {
        score = 0;
        gameOver = false;
        board.clear();
        setChanged();
    }

    /** Add TILE to the board. There must be no Tile currently at the
     *  same position. */
    public void addTile(Tile tile) {
        board.addTile(tile);
        checkGameOver();
        setChanged();
    }

    /** Tilt the board toward SIDE. Return true iff this changes the board.
     *
     * 1. If two Tile objects are adjacent in the direction of motion and have
     *    the same value, they are merged into one Tile of twice the original
     *    value and that new value is added to the score instance variable
     * 2. A tile that is the result of a merge will not merge again on that
     *    tilt. So each move, every tile will only ever be part of at most one
     *    merge (perhaps zero).
     * 3. When three adjacent tiles in the direction of motion have the same
     *    value, then the leading two tiles in the direction of motion merge,
     *    and the trailing tile does not.
     * */
    public boolean tilt(Side side) {
        boolean changed;
        changed = false;

        // TODO: Modify this.board (and perhaps this.score) to account
        // for the tilt to the Side SIDE. If the board changed, set the
        // changed local variable to true.

        // set viewing perspective to the correct one
        board.setViewingPerspective(side);

        for (int i = 0; i < board.size(); i++) {
            // only check column if its not empty
            if (!checkColumnEmpty(i) && checkEachColumn(i)) {
                changed = true;
            }
        }

        // reset to north after each click
        board.setViewingPerspective(Side.NORTH);

        checkGameOver();
        if (changed) {
            setChanged();
        }
        return changed;
    }

    private boolean checkEachColumn(int currentCol) {
        boolean changed = false;
        // merge exists boolean
        boolean mergeExistsForColumn = checkMergeExists(currentCol);

        // move all tile to most upwards possible (no merge yet)
        if (moveTileUpwards(currentCol)){
            changed = true;
        }

        if (mergeExistsForColumn) {
            System.out.printf("Tile value for column %s match\n\n", currentCol);
        } else {
            System.out.printf("Tile value for column %s does not match\n\n", currentCol);
        }

        return changed;
    }

    /**
     * Try to move all tile to the upper one
     * - if the upper column is empty then move the available tile up to that emtpy column
     * - else move it to the next available tile
     * @param currentCol
     */
    private boolean moveTileUpwards(int currentCol){
        boolean changed = false;

        for (int j = 3; j > 0; j--) {
            Tile currentTile = tile(currentCol, j);

            if (currentTile == null) {
                System.out.println("Current Tile is empty");

                int nextTileRowValue = j-1;
                // check the remaining tile to see if there is any others that can move upwards
                while (nextTileRowValue >= 0) {
                    Tile nextTile = tile(currentCol, nextTileRowValue);

                    if (nextTile != null) {
                        System.out.printf("Move tile from row %s to %s\n", nextTileRowValue, j);
                        board.move(currentCol, j, nextTile);
                        changed = true;
                        break;
                    } else {
                        nextTileRowValue -= 1;
                    }
                }
            }
        }
        System.out.println();
        return changed;
    }

    private boolean checkMergeExists(int currentCol) {
        // start from most upper tile
        int j = 3;

        // check the row 3 times (for all 4 tiles to check)
        while (j > 0) {
            Tile currentTile = tile(currentCol, j);
            Tile nextTile = tile(currentCol, j-1);
            System.out.printf("Current Tile: %s\n", currentTile);
            System.out.printf("Next Tile: %s\n", nextTile);

            // if they match and not null, means merge exists
            if (nextTile != null && currentTile != null && currentTile.value() == nextTile.value()) {
                mergeRow(currentCol, j, j-1);
                return true;
            }

            j -= 1;
        }

        return false;
    }

    /**
     * Merge second tile to first tile
     * - when merge need to add to score
     *
     * @param currentCol current column
     * @param firstTileRowValue first tile row value
     * @param secondTileRowValue second tile row value
     */
    private void mergeRow(int currentCol, int firstTileRowValue, int secondTileRowValue) {
        board.move(currentCol, firstTileRowValue, tile(currentCol, secondTileRowValue));
        this.score += tile(currentCol, firstTileRowValue).value();
    }

    /**
     * Check if the column is empty if yes means no action needed can just end early
     *
     * @param currentCol
     * @return true / false
     */
    private boolean checkColumnEmpty(int currentCol) {
        for (int j = 0; j < board.size(); j++) {
            if (tile(currentCol, j) != null) {
                return false;
            }
        }

        return true;
    }

//    private boolean checkEachColumn(int currentCol) {
//        boolean changed = false;
//
//        for (int j = 3; j > -1; j --) {
//            // current Tile that can move to
//            Tile currentTile = board.tile(currentCol, j);
//
//            // if currentTile is empty
//            // find the tile to move to it
//            if (currentTile == null) {
//                System.out.printf("Current row %s column %s is empty", currentCol, j);
//                System.out.println();
//
//                int nextTileRow = j-1;
//                while (nextTileRow >= 0) {
//                    if (board.tile(currentCol, nextTileRow) != null) {
//                        System.out.printf("Move %s from %s", j, nextTileRow);
//                        System.out.println();
//                        board.move(currentCol, j, board.tile(currentCol, nextTileRow));
//                        changed = true;
//                        break;
//                    } else {
//                        nextTileRow -= 1;
//                    }
//                }
//            } else {
//                int nextTileRow = j-1;
//                while (nextTileRow >= 0) {
//                    System.out.printf("Current row %s column %s is not empty", currentCol, j);
//                    System.out.println();
//
//                    // find the next available tile
//                    if (board.tile(currentCol, nextTileRow) != null) {
//                        // check with it can merge with the currentTile (uppestTile)
//                        if (board.tile(currentCol, nextTileRow).value() == board.tile(currentCol, j).value()) {
//                            board.move(currentCol, j, board.tile(currentCol, nextTileRow));
//                            System.out.println("Able to merge");
//                            score += board.tile(currentCol, j).value();
//                            changed = true;
//                            break;
//                        } else {
//                            System.out.println("Unable to merge");
//                            System.out.printf("Move to %s from %s", j-1, nextTileRow);
//                            System.out.println();
//                            board.move(currentCol, j-1, board.tile(currentCol, nextTileRow));
//                            changed = true;
//                            break;
//                        }
//
//                    } else {
//                        nextTileRow -= 1;
//                    }
//                }
//            }
//
//        }
//        System.out.println();
//        return changed;
//    }

    /** Checks if the game is over and sets the gameOver variable
     *  appropriately.
     */
    private void checkGameOver() {
        gameOver = checkGameOver(board);
    }

    /** Determine whether game is over. */
    private static boolean checkGameOver(Board b) {
        return maxTileExists(b) || !atLeastOneMoveExists(b);
    }

    /** Returns true if at least one space on the Board is empty.
     *  Empty spaces are stored as null.
     * */
    public static boolean emptySpaceExists(Board b) {
        /*
         * i is column and j is row
         */
        for (int i = 0; i < b.size(); i++) {
            for (int j = 0; j < b.size(); j++) {
                if (b.tile(i, j) == null) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Returns true if any tile is equal to the maximum valid value.
     * Maximum valid value is given by MAX_PIECE. Note that
     * given a Tile object t, we get its value with t.value().
     */
    public static boolean maxTileExists(Board b) {
        /*
         * Check each tile until 2048 is found return True
         * Else if can't find it return false
         */
        for (int i = 0; i < b.size(); i++) {
            for (int j = 0; j < b.size(); j++) {
                if (b.tile(i, j) != null && b.tile(i, j).value() == MAX_PIECE) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Returns true if there are any valid moves on the board.
     * There are two ways that there can be valid moves:
     * 1. There is at least one empty space on the board.
     * 2. There are two adjacent tiles with the same value.
     */
    public static boolean atLeastOneMoveExists(Board b) {
        // way 1: at least one empty space means move exists
        if (emptySpaceExists(b)) {
            return true;
        } else {
            // way two check if adjacent tile have same value
            for (int i = 0; i < b.size(); i++) {
                for (int j = 0; j < b.size(); j++) {
                    // go through column from the end of left and end of right
                    // check by going all the way up
                    if (j < 3) {
                        // check from the bottom to upper (most left & most down)
                        Tile curColTile = b.tile(i, j);
                        int nextUpperTileIndex = j+1;
                        Tile curUpperNextTile = b.tile(i, nextUpperTileIndex);

                        // debug
                        // System.out.printf("CurrentTileValue: %s", curColTile);
                        // System.out.println(" ");
                        // System.out.printf("NextTileValue: %s", curUpperNextTile);
                        // System.out.println(" ");

                        if (curColTile.value() == curUpperNextTile.value()) {
                            return true;
                        }

                        // then check from left to the right (most left & most down)
                        Tile curRowTile = b.tile(j, i);
                        int nextLeftTileIndex = j + 1;
                        Tile curLeftNextTile = b.tile(nextLeftTileIndex, i);

                        // debug
                        // System.out.printf("CurrentTileValue: %s", curRowTile);
                        // System.out.println(" ");
                        // System.out.printf("NextTileValue: %s", curLeftNextTile);
                        // System.out.println(" ");

                        if (curRowTile.value() == curLeftNextTile.value()) {
                            return true;
                        }
                    }
                    }
            }
        }
        return false;
    }

    @Override
     /** Returns the model as a string, used for debugging. */
    public String toString() {
        Formatter out = new Formatter();
        out.format("%n[%n");
        for (int row = size() - 1; row >= 0; row -= 1) {
            for (int col = 0; col < size(); col += 1) {
                if (tile(col, row) == null) {
                    out.format("|    ");
                } else {
                    out.format("|%4d", tile(col, row).value());
                }
            }
            out.format("|%n");
        }
        String over = gameOver() ? "over" : "not over";
        out.format("] %d (max: %d) (game is %s) %n", score(), maxScore(), over);
        return out.toString();
    }

    @Override
    /** Returns whether two models are equal. */
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        } else if (getClass() != o.getClass()) {
            return false;
        } else {
            return toString().equals(o.toString());
        }
    }

    @Override
    /** Returns hash code of Model’s string. */
    public int hashCode() {
        return toString().hashCode();
    }
}
