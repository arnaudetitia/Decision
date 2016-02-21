package Utils;


import java.awt.Graphics;
import java.awt.Panel;

abstract public class MiniJeu extends Panel {
	String genre;
	String nom;
	String regles;
	
	public MiniJeu(String g,String n,String r) {
		genre = g;
		nom = n;
		regles = r;
	}
	
	abstract public void paint(Graphics g) ; 
		
}
