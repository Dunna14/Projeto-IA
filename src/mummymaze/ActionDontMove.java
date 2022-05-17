package mummymaze;

import agent.Action;

public class ActionDontMove extends Action<MummyMazeState>{

    public ActionDontMove(){
        super(1);
    }

    @Override
    public void execute(MummyMazeState state){
        state.dontMove();
        state.setAction(this);
    }

    @Override
    public boolean isValid(MummyMazeState state){
        return true;
    }
}
