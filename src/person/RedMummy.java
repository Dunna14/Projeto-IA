package person;

import objectsInGame.Trap;

import java.util.LinkedList;

public class RedMummy extends Mummy {

    public RedMummy(int line, int column) {
        super(line, column);
    }

    public boolean move(char[][] matrix, int lineHero, int columnHero, boolean isHeroAlive, LinkedList<Trap> traps) {

        for (int i = 0; i < 2; i++) {
            if (!isHeroAlive) {
                return isHeroAlive;
            }
            if (getLine() > lineHero && canMoveUp(matrix)) {

                moveUp(matrix, traps);

            } else if (getLine() < lineHero && canMoveDown(matrix)) {

                moveDown(matrix, traps);
            } else if (getColumn() > columnHero && canMoveLeft(matrix)) {

                moveLeft(matrix, traps);

                //Verificar se a mumia está a esquerda do heroi
            } else if (getColumn() < columnHero && canMoveRight(matrix)) {

                moveRight(matrix, traps);

            }
            matrix[getLine()][getColumn()] = 'V';

        }

        if (matrix[lineHero][columnHero] == 'V') {
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

    public void hierarchy(char[][] matrix, LinkedList<RedMummy> redMummies, LinkedList<Scorpion> scorpions) {
        int lineRedMummy;
        int columnRedMummy;
        for (int i = 0; i < redMummies.size(); i++) {
            lineRedMummy = redMummies.get(i).getLine();
            columnRedMummy = redMummies.get(i).getColumn();

            for (int j = 0; j < redMummies.size(); j++) {
                if (i != j) {
                    if (redMummies.get(j).getLine() == lineRedMummy && redMummies.get(j).getColumn() == columnRedMummy) {
                        redMummies.remove(i);

                    }
                }
            }

            for (int j = 0; j < scorpions.size(); j++) {
                if (matrix[scorpions.get(j).getLine()][scorpions.get(j).getColumn()] == matrix[lineRedMummy][columnRedMummy]) {
                    scorpions.remove(j);
                }
            }
        }
    }

}
