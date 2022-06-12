package mummymaze;

import agent.Heuristic;

public class HeuristicEnemyDistance extends Heuristic<MummyMazeProblem, MummyMazeState> {

    @Override
    public double compute(MummyMazeState state) {
        return state.computeEnemyDistance(problem.isGoal(state));
    }
    
    @Override
    public String toString(){
        return "Enemy Distance";
    }    
}