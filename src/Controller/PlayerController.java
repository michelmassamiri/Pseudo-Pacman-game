package Controller;

import Model.Entity.Player;
import javafx.scene.input.KeyCode;

public class PlayerController {

	
	public void moveChange(KeyCode keycode) {
		// TODO Auto-generated method stub
		Player.getInstance().move(Player.getInstance().getDirection(keycode));
		
	}
}
