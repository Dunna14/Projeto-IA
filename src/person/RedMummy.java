package person;

public class RedMummy extends Mummy {

    public RedMummy(int line, int column) {super(line, column);}

    public void move(char[][] matrix, int lineHero, int columnHero, boolean isHeroAlive,int lineTrap, int columnTrap) {

        for (int i = 0; i < 2; i++) {
            if (!isHeroAlive) {
                return;
            } else {
                //Verificar se a mumia já está na mesma linha do heroi
                if (getLine() == lineHero) {
                    //Verificar se a mumia esta direita do heroi
                    if (getColumn() > columnHero) {
                        if (canMoveLeft(matrix)) {
                            moveLeft(matrix,lineTrap, columnTrap);
                        }
                        //Caso contrario, a mumia está à esquerda do heroi
                    } else {
                        if (canMoveRight(matrix)) {
                            moveRight(matrix,lineTrap, columnTrap);
                        }
                    }
                    //Caso contrario, verificar se a mumia está a baixo do heroi
                } else if (getLine() > lineHero) {
                    if (canMoveUp(matrix)) {
                        moveUp(matrix,lineTrap, columnTrap);
                        //Caso não possa ir para cima devido a uma parede
                    } else {
                        //Verificar se a mumia está à direita do heroi
                        if (getColumn() > columnHero) {
                            if (canMoveLeft(matrix)) {
                                moveLeft(matrix,lineTrap, columnTrap);
                            }
                            //Caso contrario, a mumia está à esquerda do heroi
                        } else {
                            if (canMoveRight(matrix)) {
                                moveRight(matrix,lineTrap, columnTrap);
                            }
                        }
                    }
                    //Verificar se a mumia está a cima do heroi
                } else {
                    if (canMoveDown(matrix)) {
                        moveDown(matrix,lineTrap, columnTrap);
                        //Caso não possa ir para a baixo devido a uma parede
                    } else {
                        //Verificar se a mumia está à direita do heroi
                        if (getColumn() > columnHero) {
                            if (canMoveLeft(matrix)) {
                                moveLeft(matrix,lineTrap, columnTrap);
                            }
                            //Caso contrario, a mumia está à esquerda do heroi
                        } else {
                            if (canMoveRight(matrix)) {
                                moveRight(matrix,lineTrap, columnTrap);
                            }
                        }
                    }
                }
            }
            if (matrix[lineHero][columnHero] == 'V') {
                isHeroAlive=false;
            }
        }
    }
    @Override
    public void moveUp(char[][] matrix,int lineTrap, int columnTrap) {
        matrix[getLine()][getColumn()] = '.';
        setLine(getLine() - 2);
        matrix[getLine()][getColumn()] = 'V';
    }

    @Override
    public void moveDown(char[][] matrix,int lineTrap, int columnTrap) {
        matrix[getLine()][getColumn()] = '.';
        setLine(getLine() + 2);
        matrix[getLine()][getColumn()] = 'V';
    }

    @Override
    public void moveLeft(char[][] matrix,int lineTrap, int columnTrap) {
        matrix[getLine()][getColumn()] = '.';
        setColumn(getColumn() - 2);
        matrix[getLine()][getColumn()] = 'V';
    }

    @Override
    public void moveRight(char[][] matrix,int lineTrap, int columnTrap) {
        matrix[getLine()][getColumn()] = '.';
        setColumn(getColumn() + 2);
        matrix[getLine()][getColumn()] = 'V';
    }

}
