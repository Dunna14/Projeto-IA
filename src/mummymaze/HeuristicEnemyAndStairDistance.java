package mummymaze;

import agent.Heuristic;

public class HeuristicEnemyAndStairDistance extends Heuristic<MummyMazeProblem, MummyMazeState> {

    @Override
    public double compute(MummyMazeState state) {
        return state.computeEnemyAndStairDistance(problem.isGoal(state));
    }

    @Override
    public String toString(){
        return "Enemy and Stair Distance";
    }
}
