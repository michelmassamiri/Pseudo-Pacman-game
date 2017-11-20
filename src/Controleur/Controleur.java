package Controleur;

import Model.Model;
import Vue.Vue;

public class Controleur {

	private Model model;
	private Vue vue;
	
	public Controleur(Model m, Vue v)
	{
		model = m;
		vue = v;
	}
	
}
