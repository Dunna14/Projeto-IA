package searchmethods;

import agent.Action;
import agent.Problem;
import agent.Solution;
import agent.State;

import java.util.List;

public class AStarSearch extends InformedSearch {

    //In this version we assume that the heuristic is consistent.

    @Override
    protected Solution graphSearch(Problem problem) {
        frontier.clear();
        explored.clear();
        frontier.add(new Node(problem.getInitialState()));

        while (!frontier.isEmpty() && !stopped) {
            Node n = frontier.poll();
            State state = n.getState();
            explored.add(state);
            List<Action> actions = problem.getActions(state);
            for(Action action : actions){
                State successor = problem.getSuccessor(state, action);
                if (problem.isGoal(successor)) {
                    Node successorNode = new Node(successor, n);
                    return new Solution(problem, successorNode);
                }
                addSuccessorToFrontier(successor, n);
            }
            computeStatistics(actions.size());
        }
        return null;
    }

    //f = g + h
    @Override
    public void addSuccessorToFrontier(State successor, Node parent) {
        double g = parent.getG() + successor.getAction().getCost();
        if (!(frontier.containsState(successor) || explored.contains(successor))) {
            frontier.add(new Node(successor, parent, g, g + heuristic.compute(successor)));
        }
    }

    @Override
    public String toString() {
        return "A* search";
    }
}