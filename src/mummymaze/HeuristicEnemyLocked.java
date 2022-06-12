package mummymaze;

import agent.Heuristic;

public class HeuristicEnemyLocked extends Heuristic<MummyMazeProblem, MummyMazeState> {

    @Override
    public double compute(MummyMazeState state) {
        return state.computeEnemyDistance(problem.isGoal(state));
    }
    
    @Override
    public String toString(){
        return "Enemy Locked";
    }    
}