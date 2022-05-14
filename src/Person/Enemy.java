package person;

public abstract class Enemy extends Person {

    public Enemy(int lineHero, int columnHero) {
        super(lineHero, columnHero);
    }

    public abstract void moveUp(char[][] matrix);

    public abstract void moveDown(char[][] matrix);

    public abstract void moveLeft(char[][] matrix);

    public abstract void moveRight(char[][] matrix);
}
