package mummymaze;

import agent.Action;
import agent.State;
import objectsInGame.Door;
import objectsInGame.Key;
import objectsInGame.Stair;
import objectsInGame.Trap;
import person.Hero;
import person.RedMummy;
import person.Scorpion;
import person.WhiteMummy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class MummyMazeState extends State implements Cloneable {

    private char[][] matrix;
    private Hero hero;
    private WhiteMummy whiteMummy;
    private RedMummy redMummy;
    private Scorpion scorpion;
    private Stair stair;
    private LinkedList<Trap> traps;
    private Key key;
    private Door door;
    private boolean isHeroAlive = true;


    public MummyMazeState(char[][] matrix) {
        this.matrix = new char[matrix.length][matrix.length];
        traps = new LinkedList<>();

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                this.matrix[i][j] = matrix[i][j];
                if (this.matrix[i][j] == 'S') {
                    this.stair = new Stair(i, j);
                }
                if (this.matrix[i][j] == 'A') {
                    traps.add(new Trap(i, j));
                }
                if (this.matrix[i][j] == 'C') {
                    this.key = new Key(i, j);
                }
                if (this.matrix[i][j] == '=' || this.matrix[i][j] == '”') {
                    this.door = new Door(i, j);
                    door.setOpen(false);
                } else if (this.matrix[i][j] == '_' || this.matrix[i][j] == ')') {
                    this.door = new Door(i, j);
                    door.setOpen(true);
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
        setDoor();
        moveEnemies();

        verifyHeroAlive();
        verifyWinOfHero();
    }

    public void moveRight() {
        hero.moveRight(matrix);
        setDoor();
        moveEnemies();

        verifyHeroAlive();
        verifyWinOfHero();
    }

    public void moveDown() {
        hero.moveDown(matrix);
        setDoor();
        moveEnemies();

        verifyHeroAlive();
        verifyWinOfHero();
    }

    public void moveLeft() {
        hero.moveLeft(matrix);
        setDoor();
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
            whiteMummy.move(matrix, getLineHero(), getColumnHero(), isHeroAlive, traps);
        }
        if (redMummy != null) {
            redMummy.move(matrix, getLineHero(), getColumnHero(), isHeroAlive, traps);
        }
        if (scorpion != null) {
            scorpion.move(matrix, getLineHero(), getColumnHero(), isHeroAlive, traps);
        }
    }

    public void verifyHeroAlive() {
        if (matrix[getLineHero()][getColumnHero()] == 'M' || matrix[getLineHero()][getColumnHero()] == 'V' || matrix[getLineHero()][getColumnHero()] == 'E') {
            isHeroAlive = false;
        }
    }

    public void verifyWinOfHero() {
        if (getLineHero() == getLineStair() && getColumnHero() == getColumnStair()) {
            matrix[getLineHero()][getColumnHero()] = 'S';
        }
    }

    public void setDoor() {
        if (key != null) {
            if (getLineHero() == getLineKey() && getColumnHero() == getColumnKey()) {
                door.set(matrix);
            }
        }
        if (whiteMummy != null) {
            if (whiteMummy.getLine() == getLineKey() && whiteMummy.getColumn() == getColumnKey()) {
                door.set(matrix);
            }
        }
        if (redMummy != null) {
            if (redMummy.getLine() == getLineKey() && redMummy.getColumn() == getColumnKey()) {
                door.set(matrix);
            }
        }
        if (scorpion != null) {
            if (scorpion.getLine() == getLineKey() && scorpion.getLine() == getColumnKey()) {
                door.set(matrix);
            }
        }
    }

    public void armadilha() {
        for (Trap trap : traps) {
            if (trap.getLine() == getLineHero() && trap.getColumn() == getColumnHero()) {
                isHeroAlive = false;
            }
            if (matrix[trap.getLine()][trap.getColumn()] == '.') {
                matrix[trap.getLine()][trap.getColumn()] = 'A';
            }
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

    public int getLineStair() {
        return stair.getLine();
    }

    public int getColumnStair() {
        return stair.getColumn();
    }

    public int getLineKey() {
        if (key != null) {
            return key.getLine();
        }
        return 0;
    }

    public int getColumnKey() {
        if (key != null) {
            return key.getColumn();
        }
        return 0;
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
