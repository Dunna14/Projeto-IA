package person;

public abstract class Person {
    protected int line;
    protected int column;
    protected boolean canMove;


    public Person(int line, int column) {
        this.line = line;
        this.column = column;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line = line;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public abstract boolean canMoveUp(char[][] matrix);

    public abstract boolean canMoveDown(char[][] matrix);

    public abstract boolean canMoveLeft(char[][] matrix);

    public abstract boolean canMoveRight(char[][] matrix);

}
