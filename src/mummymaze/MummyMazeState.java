package mummymaze;

import agent.Action;
import agent.State;
import person.Hero;
import person.WhiteMummy;

import java.util.ArrayList;
import java.util.Arrays;

public class MummyMazeState extends State implements Cloneable {

    private  char[][] matrix;
    private Hero hero;
    private WhiteMummy whiteMummy;
    private int lineDoor;
    private int columnDoor;

    public MummyMazeState(char[][] matrix) {
        this.matrix = new char[matrix.length][matrix.length];

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                this.matrix[i][j] = matrix[i][j];
                if (this.matrix[i][j] == 'S') {
                    lineDoor = i;
                    columnDoor = j;
                }
                if (this.matrix[i][j] == 'H') {
                    hero = new Hero(i, j);
                }
                if (this.matrix[i][j] == 'M') {
                    whiteMummy = new WhiteMummy(i, j);
                }
            }
        }
    }

    @Override
    public void executeAction(Action action) {
        action.execute(this);
        firePuzzleChanged(null);
    }

    public boolean canMoveUp() {
        System.out.println(transformMatrixToString(matrix));
        return hero.canMoveUp(matrix);
        }

    public boolean canMoveRight() {
        return hero.canMoveRight(matrix);
    }

    public boolean canMoveDown() {
        return hero.canMoveDown(matrix);
     }

    public boolean canMoveLeft() {
        return hero.canMoveLeft(matrix);
    }

    /*
     * In the next four methods we don't verify if the actions are valid.
     * This is done in method executeActions in class EightPuzzleProblem.
     * Doing the verification in these methods would imply that a clone of the
     * state was created whether the operation could be executed or not.
     */
    public void moveUp() {
        hero.moveUp(matrix);
        //TODO METER UM DELAY DE ALGUM MODO
        if (getLineHero() == getLineDoor() && getColumnHero() == getColumnDoor()) {
            matrix[getLineHero()][getColumnHero()] = 'S';
        }

        System.out.println(transformMatrixToString(matrix));
    }

    public void moveRight() {
        hero.moveRight(matrix);
        System.out.println(transformMatrixToString(matrix));
    }

    public void moveDown() {
        hero.moveDown(matrix);
        System.out.println(transformMatrixToString(matrix));
    }

    public void moveLeft() {
        hero.moveLeft(matrix);
        System.out.println(transformMatrixToString(matrix));
    }

    public void dontMove() {
        matrix[hero.getLine()][hero.getColumn()] = 'H';
        System.out.println(transformMatrixToString(matrix));
    }

    /*public double computeTilesOutOfPlace(MummyMazeState finalState) {
        double h = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                // Blank is ignored so that the heuristic is admissible
                if (this.matrix[i][j] != 0 && this.matrix[i][j] != finalState.matrix[i][j]) {
                    h++;
                }
            }
        }
        return h;
    }

    public double computeTileDistances(MummyMazeState finalState) {
        double h = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                if (this.matrix[i][j] != 0) { // Blank is ignored so that the heuristic is admissible
                    h += Math.abs(i - linesfinalMatrix[this.matrix[i][j]])
                            + Math.abs(j - colsfinalMatrix[this.matrix[i][j]]);
                }
            }
        }
        return h;
    }*/

    public int getTileValue(int line, int column) {
        if (!isValidPosition(line, column)) {
            throw new IndexOutOfBoundsException("Invalid position!");
        }
        return matrix[line][column];
    }

    public boolean isValidPosition(int line, int column) {
        return line >= 0 && line < matrix.length && column >= 0 && column < matrix[0].length;
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof MummyMazeState)) {
            return false;
        }

        MummyMazeState o = (MummyMazeState) other;
        if (matrix.length != o.matrix.length) {
            return false;
        }

        return Arrays.deepEquals(matrix, o.matrix);
    }

    @Override
    public int hashCode() {
        return 97 * 7 + Arrays.deepHashCode(this.matrix);
    }

    @Override
    public String toString() {
        StringBuilder buffer = new StringBuilder();
        for (int i = 0; i < matrix.length; i++) {
            buffer.append('\n');
            for (int j = 0; j < matrix.length; j++) {
                buffer.append(matrix[i][j]);
                buffer.append(' ');
            }
        }
        return buffer.toString();
    }

    @Override
    public MummyMazeState clone() {
        return new MummyMazeState(matrix);
    }

    //Listeners
    private transient ArrayList<MummyMazeListener> listeners = new ArrayList<MummyMazeListener>(3);

    public synchronized void removeListener(MummyMazeListener l) {
        if (listeners != null && listeners.contains(l)) {
            listeners.remove(l);
        }
    }

    public synchronized void addListener(MummyMazeListener l) {
        if (!listeners.contains(l)) {
            listeners.add(l);
        }
    }

    public void firePuzzleChanged(MummyMazeEvent pe) {
        for (MummyMazeListener listener : listeners) {
            listener.puzzleChanged(null);
        }
    }

    public int getLineHero() {
        return hero.getLine();
    }

    public int getColumnHero() {
        return hero.getColumn();
    }

    public int getLineDoor() {
        return lineDoor;
    }

    public int getColumnDoor() {
        return columnDoor;
    }

    public char[][] getMatrix() {
        return matrix;
    }

    //Usar para DEBUG
    public String transformMatrixToString(char[][] matrix) {
        String s = "";
        for (int k = 0; k < matrix.length; k++) {
            s += String.valueOf(matrix[k]) + "\n";
        }


        return s;
    }
}
