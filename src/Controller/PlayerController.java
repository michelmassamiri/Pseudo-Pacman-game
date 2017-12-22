package Controller;

import Model.Entity.Player;
import javafx.scene.input.KeyCode;

public class PlayerController {
	
	/*
	 * Player controller calls a method in the Model to tell it to change its state appropriately
	 */
	public void moveChange(KeyCode key) {
			Player.getInstance().move(Player.getInstance().getDirection(key));
	}
	
}