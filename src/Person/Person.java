package person;

public abstract class Person {
    protected int lineHero;
    protected int columnHero;

    public Person(int lineHero, int columnHero) {
        this.lineHero = lineHero;
        this.columnHero = columnHero;
    }

    public int getLineHero() {
        return lineHero;
    }

    public void setLineHero(int lineHero) {
        this.lineHero = lineHero;
    }

    public int getColumnHero() {
        return columnHero;
    }

    public void setColumnHero(int columnHero) {
        this.columnHero = columnHero;
    }
}
