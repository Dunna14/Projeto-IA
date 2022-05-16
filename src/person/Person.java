package person;

public abstract class Person {
    protected int line;
    protected int column;
    protected boolean canMove;


    public Person(int lineHero, int columnHero) {
        this.line = lineHero;
        this.column = columnHero;
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

}
