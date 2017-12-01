package Model.Entity.Actions;

import Model.Entity.BadGuy;
import Model.Entity.Player;

public class BadGuyAction extends AbstractAction{

    private Player p;
    private BadGuy b;
    private boolean lost;

    public BadGuyAction(Player player, BadGuy badGuy)
    {
        p = player;
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
