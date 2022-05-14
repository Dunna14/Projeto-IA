package person;

public class Mummy extends Enemy {

    public Mummy(int lineHero, int columnHero) {
        super(lineHero, columnHero);
    }

    @Override
    public void moveUp(char[][] matrix) {
        matrix[getLine()][getColumn()] = '.';
        setLine(getLine() - 2);
        matrix[getLine()][getColumn()] = 'H';
    }

    @Override
    public void moveDown(char[][] matrix) {

    }

    @Override
    public void moveLeft(char[][] matrix) {

    }

    @Override
    public void moveRight(char[][] matrix) {

    }
}
