package Controleur;

import Model.Model;
import Vue.Vue;

public class Controleur {

	private Model model;
	private Vue vue;
	
	/**
	 * Constructeur du Controleur permettant la gestion des evennements dans le programme.
	 * @param m Model du design pattern MVC
	 * @param v Vue du design pattern MVC
	 */
	public Controleur(Model m, Vue v)
	{
		model = m;
		vue = v;
	}
	
}
