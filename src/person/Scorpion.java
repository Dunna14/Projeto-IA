package person;

public class Scorpion extends Enemy{

    public Scorpion(int line, int column) {
        super(line, column);
    }
    private boolean isInTrap =false;

    public void move(char[][] matrix, int lineHero, int columnHero, boolean isHeroAlive,int lineTrap, int columnTrap) {
        isInTrap =false;
            if (!isHeroAlive) {
                return;
            } else {
                System.out.println("Armadilha: "+lineTrap+" "+lineTrap);
                //Verificar se a mumia já está na mesma coluna do heroi
                if (getColumn() == columnHero) {
                    //Verificar se a mumia está a baixo do heroi
                    if (getLine() > lineHero) {
                        if (canMoveUp(matrix)) {
                            moveUp(matrix,lineTrap, columnTrap);
                        }
                        //Caso contrario, a mumia está a cima do heroi
                    } else {
                        if (canMoveDown(matrix)) {
                            moveDown(matrix,lineTrap, columnTrap);
                        }
                    }
                    //Caso contrario, verificar se a mumia está a direita do heroi
                } else if (getColumn() > columnHero) {
                    if (canMoveLeft(matrix)) {
                        moveLeft(matrix,lineTrap, columnTrap);
                        //Caso não possa ir para a esquerda devido a uma parede
                    } else {
                        //Verificar se a mumia está a baixo do heroi
                        if (getLine() > lineHero) {
                            if (canMoveUp(matrix)) {
                                moveUp(matrix,lineTrap, columnTrap);
                            }
                            //Caso contrario, a mumia está a cima do heroi
                        } else {
                            if (canMoveDown(matrix)) {
                                moveDown(matrix,lineTrap, columnTrap);
                            }
                        }
                    }
                    //Verificar se a mumia está a esquerda do heroi
                } else {
                    if (canMoveRight(matrix)) {
                        moveRight(matrix,lineTrap, columnTrap);
                        //Caso não possa ir para a direita devido a uma parede
                    } else {
                        //Verificar se a mumia está a baixo do heroi
                        if (getLine() > lineHero) {
                            if (canMoveUp(matrix)) {
                                moveUp(matrix,lineTrap, columnTrap);
                            }
                            //Caso contrario, a mumia está a cima do heroi
                        } else {
                            if (canMoveDown(matrix)) {
                                moveDown(matrix,lineTrap, columnTrap);
                            }
                        }
                    }
                }
            }
        if (matrix[lineHero][columnHero] == 'E') {
            isHeroAlive=false;
        }

    }
    @Override
    public void moveUp(char[][] matrix,int lineTrap,int columntrap) {
        if(getLine()==lineTrap && getColumn() == columntrap){
            matrix[getLine()][getColumn()] = 'A';
        }else {
            matrix[getLine()][getColumn()] = '.';
        }
        setLine(getLine() - 2);
        matrix[getLine()][getColumn()] = 'E';
    }

    @Override
    public void moveDown(char[][] matrix,int lineTrap,int columntrap) {
        if(getLine()==lineTrap && getColumn() == columntrap){
            matrix[getLine()][getColumn()] = 'A';
        }else {
            matrix[getLine()][getColumn()] = '.';
        }
        setLine(getLine() + 2);
        matrix[getLine()][getColumn()] = 'E';
    }

    @Override
    public void moveLeft(char[][] matrix,int lineTrap,int columntrap) {
        if(getLine()==lineTrap && getColumn() == columntrap){
            matrix[getLine()][getColumn()] = 'A';
        }else {
            matrix[getLine()][getColumn()] = '.';
        }
        setColumn(getColumn() - 2);
        matrix[getLine()][getColumn()] = 'E';
    }

    @Override
    public void moveRight(char[][] matrix,int lineTrap,int columntrap) {
        if(getLine()==lineTrap && getColumn() == columntrap){
            matrix[getLine()][getColumn()] = 'A';
        }else {
            matrix[getLine()][getColumn()] = '.';
        }
        setColumn(getColumn() + 2);
        matrix[getLine()][getColumn()] = 'E';
    }


}
