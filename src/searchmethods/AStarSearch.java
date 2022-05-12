package searchmethods;

import agent.State;

public class AStarSearch extends InformedSearch {

    //In this version we don't assume that the heuristic is consistent.

    //f = g + h
    @Override
    public void addSuccessorToFrontier(State successor, Node parent) {
        double g = parent.getG() + successor.getAction().getCost();
        if (!frontier.containsState(successor)) {
            if (!explored.contains(successor)) {
                frontier.add(new Node(successor, parent, g, g + heuristic.compute(successor)));
            }
        } else if (g < frontier.getNode(successor).getG()) {
            frontier.removeNode(successor);
            frontier.add(new Node(successor, parent, g, g + heuristic.compute(successor)));
        }
    }

    @Override
    public String toString() {
        return "A* search";
    }
}