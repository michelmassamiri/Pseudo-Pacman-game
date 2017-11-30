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

    /**
     * Method that set the actions to do.
     */
    void actions();

    /**
     * Method that tells if the action as met the conditions to be started
     * @return true if all conditions are met, false otherwise
     */
    boolean isStartable();
}