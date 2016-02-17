package Calcul;

import java.awt.Button;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.LayoutManager;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class BonneHeure extends Panel {
	
	Vector<EnigmeHoraire> enigmes;
	Vector<ReponseHeure> reponses;
	
	Button validButton;

	public BonneHeure() {
		enigmes = new Vector<>();
		reponses = new Vector<>();
		setLayout(new GridLayout(4,1));
		for (int i = 0;i<3;i++){
			EnigmeHoraire e = new EnigmeHoraire();
			enigmes.add(e);
			add(e);
			ReponseHeure r = new ReponseHeure();
			reponses.add(r);
			add(r);
		}
		add(new Label());
		validButton= new Button("Valider");
		validButton.addActionListener(new HeureButtonListener());
		add(validButton);
	}
	
	public void verify(){
		for (int i =0;i<enigmes.size();i++){
			int sol = enigmes.elementAt(i).getReponse();
			int rep = reponses.elementAt(i).getReponse();
			if (sol != rep){
				System.out.println("Perdu");
				return;
			}
		}
		System.out.println("Gagné");
	}
	
	class HeureButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			verify();
		}
		
	}

}
