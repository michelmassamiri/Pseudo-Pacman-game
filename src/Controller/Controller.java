package Controller;

import Model.Model;
import View.View;

public class Controller {

	private Model model;
	private View view;
	
	/**
	 * Constructeur du Controleur permettant la gestion des evennements dans le programme.
	 * @param m Model du design pattern MVC
	 * @param v Vue du design pattern MVC
	 */
	public Controller(Model m, View v)
	{
		model = m;
		view = v;
	}
	
}
