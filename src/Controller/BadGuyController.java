package Controller;

import Model.Entity.BadGuy;

public class BadGuyController {
	private BadGuy[] BadGuys;
	private BadGuy bad1, bad2;
	private boolean gameOver;
	
	public BadGuyController() {
		BadGuys = new  BadGuy[2];
		bad1 = new BadGuy (1,2);
		bad2 = new BadGuy(3,6);
		BadGuys[0] = bad1;
		BadGuys[1] = bad2;
		
        
}
	public BadGuy[] getBadGuys(){
		return BadGuys;
	}
	
	
	/*
	 * Controller demands Model to pay attention if they have crashed the player
	 */
	public boolean getGameStatus(){
		for (int j=0; j<BadGuys.length;j++) {
            if (BadGuys[j].getAction().isStartable()) {
                gameOver = true;
              }
		}
		
		return gameOver;
	}
	
	/*
	 * Controller sends commands to mode in order to make Bad Guys move by themselves
	 */
	
	public  void move() {
		for (int i=0; i<BadGuys.length;i++) {
			BadGuys[i].Manhatan(GameController.getInstance().getModel().getLabyrinth());
			
		}
	}
	
	

}
