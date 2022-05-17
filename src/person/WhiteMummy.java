package person;

public class WhiteMummy extends Mummy {
    //private boolean isAlive = false;
    //private Hero hero;

    public WhiteMummy(int line, int column) {
        super(line, column);
    }

    /*public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public boolean isAlive() {
        return isAlive;
    }*/

    public void move(char[][] matrix, int lineHero, int columnHero, boolean isHeroAlive) {


        for (int i = 0; i < 2; i++) {
            if (!isHeroAlive) {
                return;
            } else {
                //Verificar se a mumia já está na mesma coluna do heroi
                if (getColumn() == columnHero) {
                    //Verificar se a mumia está a baixo do heroi
                    if (getLine() > lineHero) {
                        if (canMoveUp(matrix)) {
                            moveUp(matrix);
                        }
                        //Caso contrario, a mumia está a cima do heroi
                    } else {
                        if (canMoveDown(matrix)) {
                            moveDown(matrix);
                        }
                    }
                    //Caso contrario, verificar se a mumia está a direita do heroi
                } else if (getColumn() > columnHero) {
                    if (canMoveLeft(matrix)) {
                        moveLeft(matrix);
                        //Caso não possa ir para a esquerda devido a uma parede
                    } else {
                        //Verificar se a mumia está a baixo do heroi
                        if (getLine() > lineHero) {
                            if (canMoveUp(matrix)) {
                                moveUp(matrix);
                            }
                            //Caso contrario, a mumia está a cima do heroi
                        } else {
                            if (canMoveDown(matrix)) {
                                moveDown(matrix);
                            }
                        }
                    }
                    //Verificar se a mumia está a esquerda do heroi
                } else {
                    if (canMoveRight(matrix)) {
                        moveRight(matrix);
                        //Caso não possa ir para a direita devido a uma parede
                    } else {
                        //Verificar se a mumia está a baixo do heroi
                        if (getLine() > lineHero) {
                            if (canMoveUp(matrix)) {
                                moveUp(matrix);
                            }
                            //Caso contrario, a mumia está a cima do heroi
                        } else {
                            if (canMoveDown(matrix)) {
                                moveDown(matrix);
                            }
                        }
                    }
                }
            }
            if (matrix[lineHero][columnHero] == 'M') {
                isHeroAlive=false;
            }
        }
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




}

