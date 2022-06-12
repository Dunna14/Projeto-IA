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
    
    public MummyMazeAgent(MummyMazeState environemt) {
        super(environemt);
        initialEnvironment = (MummyMazeState) environemt.clone();

        heuristics.add(new HeuristicStairDistance());
        heuristics.add(new HeuristicEnemyDistance());
        heuristics.add(new HeuristicEnemyLocked());
        heuristics.add(new HeuristicEnemyAndStairDistance());
        heuristic = heuristics.get(0);
    }
            
    public MummyMazeState resetEnvironment(){
        environment = (MummyMazeState) initialEnvironment.clone();
        return environment;
    }
                 
    public MummyMazeState readInitialStateFromFile(File file) throws IOException {
        java.util.Scanner scanner = new java.util.Scanner(file);

        char[][] matrix = new char[13][13];
        MummyMazeState.traps = new LinkedList<>();
        MummyMazeState.doors = new LinkedList<>();
        
        for (int i = 0; i < 13; i++) {
            String line = scanner.nextLine();
            matrix[i] = line.toCharArray();

            for (int j = 0; j < 13; j++) {
                if (matrix[i][j] == 'C') {
                    MummyMazeState.key = new Key(i, j);
                }
                if (matrix[i][j] == '=' || matrix[i][j] == '”' || matrix[i][j] == '_' || matrix[i][j] == ')') {
                    MummyMazeState.doors.add(new Door(i, j));
                }
            }
        }
        //Neste inicial vamos enviar as mumias e o seguinte!
        initialEnvironment = new MummyMazeState(matrix);
        resetEnvironment();
        return environment;
    }
}
