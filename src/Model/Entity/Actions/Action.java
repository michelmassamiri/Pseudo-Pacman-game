package Model.Entity.Actions;

public interface Action {

    /**
     * Tells if the action has been done yet
     * @return true if the action has been done, false otherwise
     */
    boolean isDone();

    /**
     * Tells if the action can be done mutliple times
     * @return true if it can be done several times, false otherwise
     */
    boolean canBeRedone();

    /**
     * Launch the action when it's called.
     */
    void start();
}
