package mummymaze;

import agent.Action;
import agent.State;
import person.Hero;
import person.RedMummy;
import person.Scorpion;
import person.WhiteMummy;

import java.util.ArrayList;
import java.util.Arrays;

public class MummyMazeState extends State implements Cloneable {

    private char[][] matrix;
    private Hero hero;
    private WhiteMummy whiteMummy;
    private RedMummy redMummy;
    private Scorpion scorpion;
    private int lineDoor;
    private int columnDoor;
    private int lineTrap;
    private int columnTrap;
    private boolean isHeroAlive = true;


    public MummyMazeState(char[][] matrix) {
        this.matrix = new char[matrix.length][matrix.length];

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                this.matrix[i][j] = matrix[i][j];
                if (this.matrix[i][j] == 'S') {
                    lineDoor = i;
                    columnDoor = j;
                }
                if (this.matrix[i][j] == 'A') {
                    lineTrap=i;
                    columnTrap=j;
                    System.out.println("sheeesh");
                }
                if (this.matrix[i][j] == 'H') {
                    this.hero = new Hero(i, j);
                }
                if (this.matrix[i][j] == 'M') {
                    this.whiteMummy = new WhiteMummy(i, j);
                }
                if (this.matrix[i][j] == 'V') {
                    this.redMummy = new RedMummy(i, j);
                }
                if (this.matrix[i][j] == 'E') {
                    this.scorpion = new Scorpion(i, j);
                }

            }
        }
    }

    public int getLineTrap() {
        return lineTrap;
    }

    public int getColumnTrap() {
        return columnTrap;
    }

    @Override
    public void executeAction(Action action) {
        action.execute(this);
        firePuzzleChanged(null);
    }

    public boolean canMoveUp() {
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
        moveEnemies();

        verifyHeroAlive();
        verifyWinOfHero();
    }

    public void moveRight() {
        hero.moveRight(matrix);
        moveEnemies();

        verifyHeroAlive();
        verifyWinOfHero();
    }

    public void moveDown() {
        hero.moveDown(matrix);
        moveEnemies();

        verifyHeroAlive();
        verifyWinOfHero();
    }

    public void moveLeft() {
        hero.moveLeft(matrix);
        moveEnemies();

        verifyHeroAlive();
        verifyWinOfHero();
    }

    public void dontMove() {
        matrix[hero.getLine()][hero.getColumn()] = 'H';
        moveEnemies();
        verifyHeroAlive();
    }


    public void moveEnemies() {
        if (whiteMummy != null) {
            whiteMummy.move(matrix, getLineHero(), getColumnHero(), isHeroAlive,getLineTrap(),getColumnTrap());
        }
        if (redMummy != null) {
            redMummy.move(matrix, getLineHero(), getColumnHero(), isHeroAlive,getLineTrap(),getColumnTrap());
        }
        if (scorpion != null) {
            scorpion.move(matrix, getLineHero(), getColumnHero(), isHeroAlive,getLineTrap(),getColumnTrap());
        }
    }

    public void verifyHeroAlive() {
        if (matrix[getLineHero()][getColumnHero()] == 'M' || matrix[getLineHero()][getColumnHero()] == 'V' || matrix[getLineHero()][getColumnHero()] == 'E') {
            isHeroAlive = false;
        }
    }

    public void verifyWinOfHero() {
        if (getLineHero() == getLineDoor() && getColumnHero() == getColumnDoor()) {
            matrix[getLineHero()][getColumnHero()] = 'S';
        }
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

    public boolean isHeroAlive() {
        return isHeroAlive;
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
