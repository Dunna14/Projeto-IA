package person;

import objectsInGame.Trap;

import java.util.LinkedList;

public abstract class Enemy extends Person {

    public Enemy(int lineHero, int columnHero) {
        super(lineHero, columnHero);
    }

    public abstract void moveUp(char[][] matrix, LinkedList<Trap> traps);

    public abstract void moveDown(char[][] matrix, LinkedList<Trap> traps);

    public abstract void moveLeft(char[][] matrix, LinkedList<Trap> traps);

    public abstract void moveRight(char[][] matrix, LinkedList<Trap> traps);

    @Override
    public boolean canMoveUp(char[][] matrix) {
        return (getLine() > 1 && matrix[getLine() - 1][getColumn()] != '-');
    }

    @Override
    public boolean canMoveDown(char[][] matrix) {
        return (getLine() < 11 && matrix[getLine() + 1][getColumn()] != '-');
    }

    @Override
    public boolean canMoveLeft(char[][] matrix) {
        return (getColumn() > 1 && matrix[getLine()][getColumn() - 1] != '|');
    }

    @Override
    public boolean canMoveRight(char[][] matrix) {
        return (getColumn() < 11 && matrix[getLine()][getColumn() + 1] != '|');
    }
}
