package mummymaze;

import agent.Action;
import agent.State;
import objectsInGame.Key;
import objectsInGame.Stair;
import objectsInGame.Trap;
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
    private Stair stair;
    private int trapLine;
    private int trapColumn;
    private Key key;
    private int lineFence;//é a porta mas como já existe um lineDoor e columnDoor dei este nome. ainda pode mudar!!
    private int columnFence;
    private boolean isHeroAlive = true;


    public MummyMazeState(char[][] matrix) {
        this.matrix = new char[matrix.length][matrix.length];

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                this.matrix[i][j] = matrix[i][j];
                if (this.matrix[i][j] == 'S') {
                    this.stair = new Stair(i, j);
                }
                if (this.matrix[i][j] == 'A') {
                    trapLine = i;
                    trapColumn = j;
                }
                if (this.matrix[i][j] == 'C'){
                    this.key = new Key(i, j);
                }
                if (this.matrix[i][j] == '=') {//com as coodernadas guardadas falta verificar se a chave ainda existe na matriz depois de nao existir é so fazer a porta(fence) abrir,aka desaparecer
                    lineFence=i;
                    columnFence=j;
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
        if (getLineHero() == getLineStair() && getColumnHero() == getColumnStair()) {
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

    public int getLineStair() {
        return stair.getLine();
    }

    public int getColumnStair() {
        return stair.getColumn();
    }

    public int getLineTrap() {
        return trapLine;
    }

    public int getColumnTrap() {
        return trapColumn;
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
