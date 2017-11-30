package Model.Entity.Actions;

public abstract class AbstractAction implements Action{

    private boolean done = false;
    private boolean redone = false;


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
            }
        }
    }
    
    @Override
    public boolean isStartable() {
        return true;
    }
}
