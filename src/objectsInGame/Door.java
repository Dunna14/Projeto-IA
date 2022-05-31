package objectsInGame;

public class Door extends Object {
    private boolean isOpen;

    public Door(int line, int column) {
        super(line, column);
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void open(char[][] matrix) {
        if (!isOpen) {
            isOpen = true;
            matrix[getLine()][getColumn()] = '_';
        }
    }

    public void close(char[][] matrix) {
        if (isOpen) {
            isOpen = false;
            matrix[getLine()][getColumn()] = '=';
        }
    }
}
