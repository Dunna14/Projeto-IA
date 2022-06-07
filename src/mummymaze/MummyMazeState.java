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
    private LinkedList<WhiteMummy> whiteMummys;
    private LinkedList<RedMummy> redMummys;
    private LinkedList<Scorpion> scorpions;
    private Stair stair;
    private LinkedList<Trap> traps;
    private Key key;
    private LinkedList<Door> doors;
    private boolean isHeroAlive = true;


    public MummyMazeState(char[][] matrix, Key key, LinkedList<Door> doors, LinkedList<Trap> traps) {
        this.matrix = new char[matrix.length][matrix.length];
        this.traps = traps;
        this.doors = doors;
        this.key = key;
        whiteMummys = new LinkedList<>();
        redMummys = new LinkedList<>();
        scorpions = new LinkedList<>();

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                this.matrix[i][j] = matrix[i][j];
                if (this.matrix[i][j] == 'S') {
                    this.stair = new Stair(i, j);
                }
                if (this.matrix[i][j] == 'A') {
                    traps.add(new Trap(i, j));
                }
                if (this.matrix[i][j] == 'H') {
                    this.hero = new Hero(i, j);
                }
                if (this.matrix[i][j] == 'M') {
                    whiteMummys.add(new WhiteMummy(i, j));
                }
                if (this.matrix[i][j] == 'V') {
                    redMummys.add(new RedMummy(i, j));
                }
                if (this.matrix[i][j] == 'E') {
                    scorpions.add(new Scorpion(i, j));
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
        moveEnemies();
        armadilha();
        setDoor();

        verifyHeroAlive();
        verifyWinOfHero();
        System.out.println(transformMatrixToString(matrix));
    }

    public void moveRight() {
        hero.moveRight(matrix);
        moveEnemies();
        armadilha();
        setDoor();

        verifyHeroAlive();
        verifyWinOfHero();
        System.out.println(transformMatrixToString(matrix));
    }

    public void moveDown() {
        hero.moveDown(matrix);
        moveEnemies();
        armadilha();
        setDoor();

        verifyHeroAlive();
        verifyWinOfHero();
        System.out.println(transformMatrixToString(matrix));
    }

    public void moveLeft() {
        hero.moveLeft(matrix);
        moveEnemies();
        armadilha();
        setDoor();

        verifyHeroAlive();
        verifyWinOfHero();
        System.out.println(transformMatrixToString(matrix));
    }

    public void dontMove() {
        matrix[hero.getLine()][hero.getColumn()] = 'H';
        moveEnemies();
        armadilha();
        setDoor();

        verifyHeroAlive();
        System.out.println(transformMatrixToString(matrix));
    }


    public void moveEnemies() {
        if (!scorpions.isEmpty()) {
            for (Scorpion scorpion : scorpions) {
                isHeroAlive = scorpion.move(matrix, getLineHero(), getColumnHero(), isHeroAlive, traps);
                scorpion.hierarchy(matrix, scorpions);
            }
        }
        if (!redMummys.isEmpty()) {
            for (RedMummy redMummy : redMummys) {
                isHeroAlive = redMummy.move(matrix, getLineHero(), getColumnHero(), isHeroAlive, traps);
                redMummy.hierarchy(matrix, redMummys, scorpions);
            }
        }
        if (!whiteMummys.isEmpty()) {
            for (WhiteMummy whiteMummy : whiteMummys) {
                isHeroAlive = whiteMummy.move(matrix, getLineHero(), getColumnHero(), isHeroAlive, traps);
                whiteMummy.hierarchy(matrix, whiteMummys, redMummys, scorpions);
            }

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
                for (Door door : doors) {
                    if (matrix[door.getLine()][door.getColumn()] == ')') {
                        door.closeVertical(matrix);
                    } else if (matrix[door.getLine()][door.getColumn()] == '_') {
                        door.closeHorizontal(matrix);
                    } else if (matrix[door.getLine()][door.getColumn()] == '=') {
                        door.openHorizontal(matrix);
                    } else if (matrix[door.getLine()][door.getColumn()] == '"') {
                        door.openVertical(matrix);
                    }
                }
            }


            if (!whiteMummys.isEmpty()) {
                for (WhiteMummy whiteMummy : whiteMummys) {
                    if (whiteMummy.getLine() == getLineKey() && whiteMummy.getColumn() == getColumnKey()) {
                        for (Door door : doors) {
                            if (matrix[door.getLine()][door.getColumn()] == ')') {
                                door.closeVertical(matrix);
                            } else if (matrix[door.getLine()][door.getColumn()] == '_') {
                                door.closeHorizontal(matrix);
                            } else if (matrix[door.getLine()][door.getColumn()] == '=') {
                                door.openHorizontal(matrix);
                            } else if (matrix[door.getLine()][door.getColumn()] == '"') {
                                door.openVertical(matrix);
                            }
                        }
                    }
                }
            }
            if (!redMummys.isEmpty()) {
                for (RedMummy redMummy : redMummys) {
                    if (redMummy.getLine() == getLineKey() && redMummy.getColumn() == getColumnKey()) {
                        for (Door door : doors) {
                            if (matrix[door.getLine()][door.getColumn()] == ')') {
                                door.closeVertical(matrix);
                            } else if (matrix[door.getLine()][door.getColumn()] == '_') {
                                door.closeHorizontal(matrix);
                            } else if (matrix[door.getLine()][door.getColumn()] == '=') {
                                door.openHorizontal(matrix);
                            } else if (matrix[door.getLine()][door.getColumn()] == '"') {
                                door.openVertical(matrix);
                            }
                        }
                    }
                }
            }
            if (!scorpions.isEmpty()) {
                for (Scorpion scorpion : scorpions) {
                    if (scorpion.getLine() == getLineKey() && scorpion.getLine() == getColumnKey()) {
                        for (Door door : doors) {
                            if (matrix[door.getLine()][door.getColumn()] == ')') {
                                door.closeVertical(matrix);
                            } else if (matrix[door.getLine()][door.getColumn()] == '_') {
                                door.closeHorizontal(matrix);
                            } else if (matrix[door.getLine()][door.getColumn()] == '=') {
                                door.openHorizontal(matrix);
                            } else if (matrix[door.getLine()][door.getColumn()] == '"') {
                                door.openVertical(matrix);
                            }
                        }
                    }
                }
            }
        }
        if (matrix[getLineKey()][getColumnKey()] == '.') {
            matrix[getLineKey()][getColumnKey()] = 'C';
        }
    }

    public void armadilha() {
        int trapLine;
        int trapColumn;
        for (int i = 0; i < traps.size(); i++) {
            trapLine = traps.get(i).getLine();
            trapColumn = traps.get(i).getColumn();

            if (trapLine == getLineHero() && trapColumn == getColumnHero()) {
                isHeroAlive = false;
            }
            if (matrix[trapLine][trapColumn] == '.') {
                matrix[trapLine][trapColumn] = 'A';
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
        return new MummyMazeState(matrix, key, doors, traps);
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
