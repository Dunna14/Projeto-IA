package person;

public class WhiteMummy extends Mummy {
    private Hero heroi;

    public WhiteMummy(int lineHero, int columnHero) {
        super(lineHero, columnHero);
    }

    public boolean canMoveUp(char[][] matrix) {
        return (getLine() > 1 && matrix[getLine() - 1][getColumn()] != '-');
    }

    public void move(char[][] matrix) {
        //For para andar 2x
        for (int i = 0; i < 2; i++) {
            if (getColumn() == heroi.getColumn()) {
                if (getLine() > heroi.getLine()) {
                    if (canMoveDown) {
                        moveDown(matrix);
                    }
                } else {
                    if (canMoveUp(matrix)) {
                        moveUp(matrix);
                    }
                }
            } else if (getColumn() > heroi.getColumn()) {
                if (canMoveLeft) {
                    moveLeft(matrix);
                } else {
                    if (getLine() > heroi.getLine()) {
                        if (canMoveDown) {
                            moveDown(matrix);
                        }
                    } else {
                        if (canMoveUp) {
                            moveUp(matrix);
                        }
                    }
                }
            } else {
                if (canMoveRight) {
                    moveRight(matrix);
                } else {
                    if (getLine() > heroi.getLine()) {
                        if (canMoveDown) {
                            moveDown(matrix);
                        }
                    } else {
                        if (canMoveUp) {
                            moveUp(matrix);
                        }
                    }
                }
            }
        }
    }
}
