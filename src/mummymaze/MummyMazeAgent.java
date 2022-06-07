package mummymaze;

import agent.Agent;
import objectsInGame.Door;
import objectsInGame.Key;
import objectsInGame.Trap;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

public class MummyMazeAgent extends Agent<MummyMazeState>{
    
    protected MummyMazeState initialEnvironment;
    protected LinkedList<Trap> traps;
    protected Key key;
    protected LinkedList<Door> doors;
    
    public MummyMazeAgent(MummyMazeState environemt) {
        super(environemt);
        initialEnvironment = (MummyMazeState) environemt.clone();
        heuristics.add(new HeuristicTileDistance());
        heuristics.add(new HeuristicTilesOutOfPlace());
        heuristic = heuristics.get(0);
    }
            
    public MummyMazeState resetEnvironment(){
        environment = (MummyMazeState) initialEnvironment.clone();
        return environment;
    }
                 
    public MummyMazeState readInitialStateFromFile(File file) throws IOException {
        java.util.Scanner scanner = new java.util.Scanner(file);

        char[][] matrix = new char[13][13];
        traps = new LinkedList<>();
        doors = new LinkedList<>();
        
        for (int i = 0; i < 13; i++) {
            String line = scanner.nextLine();
            matrix[i] = line.toCharArray();

            for (int j = 0; j < 13; j++) {
                if (matrix[i][j] == 'C') {
                    this.key = new Key(i, j);
                }
                if (matrix[i][j] == '=' || matrix[i][j] == '”') {
                    doors.add(new Door(i, j));
                    //door.getsetOpen(false);
                } else if (matrix[i][j] == '_' || matrix[i][j] == ')') {
                    doors.add(new Door(i, j));
                    //door.setOpen(true);
                }
            }
        }
        //Neste inicial vamos enviar as mumias e o seguinte!
        initialEnvironment = new MummyMazeState(matrix, key, doors, traps);
        resetEnvironment();
        return environment;
    }
}
