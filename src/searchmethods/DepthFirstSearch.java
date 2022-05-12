package searchmethods;

import agent.Action;
import agent.Problem;
import agent.Solution;
import agent.State;
import java.util.List;
import utils.NodeLinkedList;

public class DepthFirstSearch extends GraphSearch<NodeLinkedList> {

    public DepthFirstSearch() {
        frontier = new NodeLinkedList();
    }

    @Override
    protected Solution graphSearch(Problem problem) {
        frontier.clear();
        frontier.add(new Node(problem.getInitialState()));

        while (!frontier.isEmpty() && !stopped) {
            Node n = frontier.poll();
            State state = n.getState();
            if (problem.isGoal(state)) {
                return new Solution(problem, n);
            }
            List<Action> actions = problem.getActions(state);
            for(Action action : actions){
                State successor = problem.getSuccessor(state, action);
                addSuccessorToFrontier(successor, n);
            }
            computeStatistics(actions.size());
        }
        return null;
    }

    @Override
    public void addSuccessorToFrontier(State successor, Node parent) {
        if (!frontier.containsState(successor)) {
            //parent is always != null
            if (!parent.isCycle(successor)) {
                frontier.addFirst(new Node(successor, parent));
            }
        }
    }

    @Override
    public String toString() {
        return "Depth first search";
    }
}
