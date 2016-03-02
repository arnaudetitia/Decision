
import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Panel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;

import Adresse.CliquePoursuite;
import Adresse.Jauge;
import Calcul.BonneHeure;
import Calcul.Comparaison;
import Calcul.FlashAddition;
import Calcul.Intrus;
import Memoire.Combinaison;
import Memoire.Fugitif;
import Utils.MiniJeu;

public class Main {
	
	static WindowAdapter adapter = new WindowAdapter() {
		public void windowClosing(WindowEvent e) {
			System.exit(0);
		};
	};
	
	
	public static void main(String[] args) {
		Frame fen = new Frame();
		fen.setSize(500,500);
		fen.addWindowListener(adapter);
		MiniJeu jeu = new Combinaison(fen); 
		fen.add(jeu);
		fen.setVisible(true);	
	}
}
