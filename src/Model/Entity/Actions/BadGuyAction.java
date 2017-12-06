package Model.Entity.Actions;

import Model.Entity.BadGuy;

public class BadGuyAction extends AbstractAction{

    private BadGuy b;
    private boolean lost;

    public BadGuyAction(BadGuy badGuy)
    {
        super();
        lost = false;
        redone = true;
        b = badGuy;
    }

    @Override
    public void actions() {
        lost = true;
    }

    @Override
    public boolean isStartable() {
        return b.getPos().equals(p.getPos());
    }

}
