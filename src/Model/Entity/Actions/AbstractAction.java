package Model.Entity.Actions;

import Model.Entity.Player;
import Model.Entity.StaticEntity;

public abstract class AbstractAction implements Action{

    protected boolean done = false;
    protected boolean redone = false;
    protected Player p;
    protected StaticEntity entity;

    public void setEntity(StaticEntity e)
    {
        entity = e;
    }
    
    protected AbstractAction(){
    	p = Player.getInstance();
    }

    @Override
    public boolean isDone() {
        return done;
    }

    @Override
    public boolean canBeRedone() {
        return redone;
    }

    @Override
    public void start() {
        if(isStartable())
        {
            if(!isDone() || canBeRedone())
            {
                actions();
                done = true;
            }
        }
    }

    @Override
    public boolean isStartable() {
        return true;
    }
}
