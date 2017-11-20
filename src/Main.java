import Controleur.Controleur;
import Model.Model;
import Vue.Vue;

public class Main {
	
	public static void main(String[] args) {	
		Model m = new Model();
		Vue v = new Vue(m);
		Controleur c = new Controleur(m, v);
	}

}
