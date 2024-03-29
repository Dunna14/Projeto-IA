package person;

public class Hero extends Person {

    public Hero(int lineHero, int columnHero) {
        super(lineHero, columnHero);
    }

    public boolean canMoveUp(char[][] matrix) {

        return (getLine() == 1 && matrix[getLine() - 1][getColumn()] == 'S')
                || (getLine() > 1 && (matrix[getLine() - 2][getColumn()] == '.'
                || matrix[getLine() - 2][getColumn()] == 'C')
                && matrix[getLine() - 1][getColumn()] != '-'
                && matrix[getLine() - 1][getColumn()] != '=');

    }

    public boolean canMoveRight(char[][] matrix) {

        return (getColumn() == 11 && matrix[getLine()][getColumn() + 1] == 'S')
                || (getColumn() < 11 && (matrix[getLine()][getColumn() + 2] == '.'
                || matrix[getLine()][getColumn() + 2] == 'C')
                && matrix[getLine()][getColumn() + 1] != '|'
                && matrix[getLine()][getColumn() + 1] != '"');
    }

    public boolean canMoveDown(char[][] matrix) {

        return (getLine() == 11 && matrix[getLine() + 1][getColumn()] == 'S')
                || (getLine() < 11 && (matrix[getLine() + 2][getColumn()] == '.'
                || matrix[getLine() + 2][getColumn()] == 'C')
                && matrix[getLine() + 1][getColumn()] != '-'
                && matrix[getLine() + 1][getColumn()] != '=');
    }

    public boolean canMoveLeft(char[][] matrix) {

        return (getColumn() == 1 && matrix[getLine()][getColumn() - 1] == 'S')
                || (getColumn() > 1 && (matrix[getLine()][getColumn() - 2] == '.'
                || matrix[getLine()][getColumn() - 2] == 'C')
                && matrix[getLine()][getColumn() - 1] != '|'
                && matrix[getLine()][getColumn() - 1] != '"');
    }

    public void moveUp(char[][] matrix) {
        if (getLine() == 1) {
            matrix[getLine()][getColumn()] = '.';
            setLine(getLine() - 1);
            matrix[getLine()][getColumn()] = 'H';
        } else {
            matrix[getLine()][getColumn()] = '.';
            setLine(getLine() - 2);
            matrix[getLine()][getColumn()] = 'H';
        }
    }

    public void moveRight(char[][] matrix) {

        if (getColumn() == 11) {
            matrix[getLine()][getColumn()] = '.';
            setColumn(getColumn() + 1);
            matrix[getLine()][getColumn()] = 'H';
        } else {
            matrix[getLine()][getColumn()] = '.';
            setColumn(getColumn() + 2);
            matrix[getLine()][getColumn()] = 'H';
        }
    }

    public void moveDown(char[][] matrix) {

        if (getLine() == 11) {
            matrix[getLine()][getColumn()] = '.';
            setLine(getLine() + 1);
            matrix[getLine()][getColumn()] = 'H';
        } else {
            matrix[getLine()][getColumn()] = '.';
            setLine(getLine() + 2);
            matrix[getLine()][getColumn()] = 'H';
        }
    }

    public void moveLeft(char[][] matrix) {
        if (getColumn() == 1) {
            matrix[getLine()][getColumn()] = '.';
            setColumn(getColumn() - 1);
            matrix[getLine()][getColumn()] = 'H';
        } else {
            matrix[getLine()][getColumn()] = '.';
            setColumn(getColumn() - 2);
            matrix[getLine()][getColumn()] = 'H';
        }
    }
}
