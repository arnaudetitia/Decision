package Memoire;

import java.awt.Frame;
import java.awt.Graphics;

import Utils.MiniJeu;

public class Combinaison extends MiniJeu {

	Frame fenetre;
	
	public Combinaison(Frame fen) {
		super("Memoire", "Combinaison", "Clique sur les cases dans l'ordre");
		fenetre = fen;
	}

	@Override
	public void paint(Graphics g) {
		float debX = (float) (fenetre.getWidth() * 0.2);
		float debY = (float) (fenetre.getWidth() * 0.1);
		float mailleX = (float) (fenetre.getWidth() * 0.2);
		float mailleY = (float) (fenetre.getHeight()* 0.2);
		for (int i=0;i<2;i++){
			for (int j=0;j<3;j++){
				g.drawRect((int)(debX+j*mailleX), (int)(debY+i*mailleY), (int)mailleX,(int) mailleY);
			}
		}
		
	}

}
