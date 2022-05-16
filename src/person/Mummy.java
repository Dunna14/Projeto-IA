package person;

public class Mummy extends Enemy {

    public Mummy(int lineHero, int columnHero) {
        super(lineHero, columnHero);
    }

    @Override
    public void moveUp(char[][] matrix) {
        matrix[getLine()][getColumn()] = '.';
        setLine(getLine() - 2);
        matrix[getLine()][getColumn()] = 'M';
    }

    @Override
    public void moveDown(char[][] matrix) {
        matrix[getLine()][getColumn()] = '.';
        setLine(getLine() + 2);
        matrix[getLine()][getColumn()] = 'M';
    }

    @Override
    public void moveLeft(char[][] matrix) {
        matrix[getLine()][getColumn()] = '.';
        setColumn(getColumn() - 2);
        matrix[getLine()][getColumn()] = 'M';
    }

    @Override
    public void moveRight(char[][] matrix) {
        matrix[getLine()][getColumn()] = '.';
        setColumn(getColumn() + 2);
        matrix[getLine()][getColumn()] = 'M';
    }

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
