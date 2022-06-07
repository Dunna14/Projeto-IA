package person;

import objectsInGame.Trap;

import java.util.LinkedList;

public class WhiteMummy extends Mummy {

    public WhiteMummy(int line, int column) {
        super(line, column);
    }

    public boolean move(char[][] matrix, int lineHero, int columnHero, boolean isHeroAlive, LinkedList<Trap> traps) {

        for (int i = 0; i < 2; i++) {
            if (!isHeroAlive) {
                return isHeroAlive;
            }
            //Verificar se a mumia já está à direita do heroi
            if (getColumn() > columnHero && canMoveLeft(matrix)) {

                moveLeft(matrix, traps);


                //Verificar se a mumia está a esquerda do heroi
            } else if (getColumn() < columnHero && canMoveRight(matrix)) {

                moveRight(matrix, traps);

            } else if (getLine() > lineHero && canMoveUp(matrix)) {

                moveUp(matrix, traps);

                //Caso contrario, a mumia está a cima do heroi
            } else if (getLine() < lineHero && canMoveDown(matrix)) {

                moveDown(matrix, traps);
            }

            matrix[getLine()][getColumn()] = 'M';
        }


        if (matrix[lineHero][columnHero] == 'M') {
            isHeroAlive = false;
        }
        return isHeroAlive;
    }

    @Override
    public void moveUp(char[][] matrix, LinkedList<Trap> traps) {
        matrix[getLine()][getColumn()] = '.';
        setLine(getLine() - 2);
    }

    @Override
    public void moveDown(char[][] matrix, LinkedList<Trap> traps) {
        matrix[getLine()][getColumn()] = '.';
        setLine(getLine() + 2);
    }

    @Override
    public void moveLeft(char[][] matrix, LinkedList<Trap> traps) {
        matrix[getLine()][getColumn()] = '.';
        setColumn(getColumn() - 2);
    }

    @Override
    public void moveRight(char[][] matrix, LinkedList<Trap> traps) {
        matrix[getLine()][getColumn()] = '.';
        setColumn(getColumn() + 2);
    }


    public void hierarchy(char[][] matrix, LinkedList<WhiteMummy> whiteMummies, LinkedList<RedMummy> redMummies,
                          LinkedList<Scorpion> scorpions) {
        int lineMummy;
        int columnMummy;
        for (int i = 0; i < whiteMummies.size(); i++) {
            lineMummy = whiteMummies.get(i).getLine();
            columnMummy = whiteMummies.get(i).getColumn();

            for (int j = 0; j < whiteMummies.size(); j++) {
                if (i != j) {
                    //FUNCIONA
                    if (matrix[whiteMummies.get(j).getLine()][whiteMummies.get(j).getColumn()] == matrix[lineMummy][columnMummy]) {
                        whiteMummies.remove(j);
                    }
                }
            }

            for (int j = 0; j < redMummies.size(); j++) {
                if (matrix[redMummies.get(j).getLine()][redMummies.get(j).getColumn()] == matrix[lineMummy][columnMummy]) {
                    redMummies.remove(j);
                }
            }

            for (int j = 0; j < scorpions.size(); j++) {
                if (matrix[scorpions.get(j).getLine()][scorpions.get(j).getColumn()] == matrix[lineMummy][columnMummy]) {
                    scorpions.remove(j);
                }
            }
        }
    }

}

