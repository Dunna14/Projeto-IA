package mummymaze;

import agent.Heuristic;

public class HeuristicStairDistance extends Heuristic<MummyMazeProblem, MummyMazeState>{

    @Override
    public double compute(MummyMazeState state){
        return state.computeStairDistance();
    }

    @Override
    public String toString(){
        return "Stair Distance";
    }
}