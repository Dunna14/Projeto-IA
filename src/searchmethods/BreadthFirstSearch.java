package searchmethods;

import agent.Action;
import agent.Problem;
import agent.Solution;
import agent.State;
import utils.NodeLinkedList;

import java.util.List;

public class BreadthFirstSearch extends GraphSearch<NodeLinkedList> {

    public BreadthFirstSearch() {
        frontier = new NodeLinkedList();
    }

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

    @Override
    public void addSuccessorToFrontier(State successor, Node parent) {
        if (!(frontier.containsState(successor) || explored.contains(successor))) {
            frontier.addLast(new Node(successor, parent));
        }
    }

    @Override
    public String toString() {
        return "Breadth first search";
    }
}