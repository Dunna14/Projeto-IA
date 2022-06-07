package person;

import objectsInGame.Trap;

import java.util.LinkedList;

public class Scorpion extends Enemy {

    public Scorpion(int line, int column) {
        super(line, column);
    }
    //private boolean isInTrap =false;

    public void move(char[][] matrix, int lineHero, int columnHero, boolean isHeroAlive, LinkedList<Trap> traps) {
        //isInTrap =false;
        if (!isHeroAlive) {
            return;
        } else {
            //System.out.println("Armadilha: "+lineTrap+" "+columnTrap);
            //Verificar se a mumia já está na mesma coluna do heroi
            if (getColumn() == columnHero) {
                //Verificar se a mumia está a baixo do heroi
                if (getLine() > lineHero) {
                    if (canMoveUp(matrix)) {
                        moveUp(matrix, traps);
                    }
                    //Caso contrario, a mumia está a cima do heroi
                } else {
                    if (canMoveDown(matrix)) {
                        moveDown(matrix, traps);
                    }
                }
                //Caso contrario, verificar se a mumia está a direita do heroi
            } else if (getColumn() > columnHero) {
                if (canMoveLeft(matrix)) {
                    moveLeft(matrix, traps);
                    //Caso não possa ir para a esquerda devido a uma parede
                } else {
                    //Verificar se a mumia está a baixo do heroi
                    if (getLine() > lineHero) {
                        if (canMoveUp(matrix)) {
                            moveUp(matrix, traps);
                        }
                        //Caso contrario, a mumia está a cima do heroi
                    } else {
                        if (canMoveDown(matrix)) {
                            moveDown(matrix, traps);
                        }
                    }
                }
                //Verificar se a mumia está a esquerda do heroi
            } else {
                if (canMoveRight(matrix)) {
                    moveRight(matrix, traps);
                    //Caso não possa ir para a direita devido a uma parede
                } else {
                    //Verificar se a mumia está a baixo do heroi
                    if (getLine() > lineHero) {
                        if (canMoveUp(matrix)) {
                            moveUp(matrix, traps);
                        }
                        //Caso contrario, a mumia está a cima do heroi
                    } else {
                        if (canMoveDown(matrix)) {
                            moveDown(matrix, traps);
                        }
                    }
                }
            }
        }
        if (matrix[lineHero][columnHero] == 'E') {
            isHeroAlive = false;
        }

    }

    @Override
    public void moveUp(char[][] matrix, LinkedList<Trap> traps) {
        for (Trap trap : traps) {
            if (getLine() == trap.getLine() && getColumn() == trap.getColumn()) {
                matrix[getLine()][getColumn()] = 'A';
            } else {
                matrix[getLine()][getColumn()] = '.';
            }
        }

        setLine(getLine() - 2);
        matrix[getLine()][getColumn()] = 'E';
    }

    @Override
    public void moveDown(char[][] matrix, LinkedList<Trap> traps) {
        for (Trap trap : traps) {
            if (getLine() == trap.getLine() && getColumn() == trap.getColumn()) {
                matrix[getLine()][getColumn()] = 'A';
            } else {
                matrix[getLine()][getColumn()] = '.';
            }
        }

        setLine(getLine() + 2);
        matrix[getLine()][getColumn()] = 'E';
    }

    @Override
    public void moveLeft(char[][] matrix, LinkedList<Trap> traps) {
        for (Trap trap : traps) {
            if (getLine() == trap.getLine() && getColumn() == trap.getColumn()) {
                matrix[getLine()][getColumn()] = 'A';
            } else {
                matrix[getLine()][getColumn()] = '.';
            }
        }

        setColumn(getColumn() - 2);
        matrix[getLine()][getColumn()] = 'E';
    }

    @Override
    public void moveRight(char[][] matrix, LinkedList<Trap> traps) {
        for (Trap trap : traps) {
            if (getLine() == trap.getLine() && getColumn() == trap.getColumn()) {
                matrix[getLine()][getColumn()] = 'A';
            } else {
                matrix[getLine()][getColumn()] = '.';
            }
        }

        setColumn(getColumn() + 2);
        matrix[getLine()][getColumn()] = 'E';
    }

}
