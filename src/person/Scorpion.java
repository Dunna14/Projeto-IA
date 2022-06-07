package person;

import objectsInGame.Trap;

import java.util.LinkedList;

public class Scorpion extends Enemy {

    public Scorpion(int line, int column) {
        super(line, column);
    }

    public boolean move(char[][] matrix, int lineHero, int columnHero, boolean isHeroAlive, LinkedList<Trap> traps) {

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

        matrix[getLine()][getColumn()] = 'E';

        if (matrix[lineHero][columnHero] == 'E') {
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

    public void hierarchy(char[][] matrix, LinkedList<Scorpion> scorpions) {
        int lineScorpion;
        int columnScorpion;
        for (int i = 0; i < scorpions.size(); i++) {
            lineScorpion = scorpions.get(i).getLine();
            columnScorpion = scorpions.get(i).getColumn();

            for (int j = 0; j < scorpions.size(); j++) {
                if (i != j) {
                    if (matrix[scorpions.get(j).getLine()][scorpions.get(j).getColumn()] == matrix[lineScorpion][columnScorpion]) {
                        scorpions.remove(j);
                    }
                }
            }

        }
    }

}
