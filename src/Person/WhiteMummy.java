package person;

public class WhiteMummy extends Mummy {
    private Hero heroi;

    public WhiteMummy(int lineHero, int columnHero) {
        super(lineHero, columnHero);
    }

    public void move(char[][] matrix) {
        //For para andar 2x
        for (int i = 0; i < 2; i++) {
            //Verificar se a mumia já está na mesma coluna do heroi
            if (getColumn() == heroi.getColumn()) {
                //Verificar se a mumia está a baixo do heroi
                if (getLine() > heroi.getLine()) {
                    if (canMoveDown(matrix)) {
                        moveDown(matrix);
                    }
                    //Caso contrario, a mumia está a cima do heroi
                } else {
                    if (canMoveUp(matrix)) {
                        moveUp(matrix);
                    }
                }
                //Caso contrario, verificar se a mumia está a direita do heroi
            } else if (getColumn() > heroi.getColumn()) {
                if (canMoveLeft(matrix)) {
                    moveLeft(matrix);
                    //Caso não possa ir para a esquerda devido a uma parede
                } else {
                    //Verificar se a mumia está a baixo do heroi
                    if (getLine() > heroi.getLine()) {
                        if (canMoveDown(matrix)) {
                            moveDown(matrix);
                        }
                        //Caso contrario, a mumia está a cima do heroi
                    } else {
                        if (canMoveUp(matrix)) {
                            moveUp(matrix);
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
                    if (getLine() > heroi.getLine()) {
                        if (canMoveDown(matrix)) {
                            moveDown(matrix);
                        }
                        //Caso contrario, a mumia está a cima do heroi
                    } else {
                        if (canMoveUp(matrix)) {
                            moveUp(matrix);
                        }
                    }
                }
            }
        }
    }
}
