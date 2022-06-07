package objectsInGame;

public class Door extends Object {
    private boolean isOpen;

    public Door(int line, int column) {
        super(line, column);
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean isOpen) {
        this.isOpen = isOpen;
    }

    public void openHorizontal(char[][] matrix) {
            isOpen = true;
            matrix[getLine()][getColumn()] = '_';
    }

    public void openVertical(char[][] matrix) {
        isOpen = true;
        matrix[getLine()][getColumn()] = ')';
    }

    public void closeVertical(char[][] matrix) {
            //isOpen = false;
            matrix[getLine()][getColumn()] = '"';
    }

    public void closeHorizontal(char[][] matrix) {
        isOpen = false;
        matrix[getLine()][getColumn()] = '=';
    }

    /*public void set(char[][] matrix) {
        if (isOpen) {
            close(matrix);
        } else {
            open(matrix);
        }
    }*/
}
