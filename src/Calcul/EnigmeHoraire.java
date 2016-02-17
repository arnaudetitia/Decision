package Calcul;

import java.awt.Button;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.TextEvent;
import java.awt.event.TextListener;

public class EnigmeHoraire extends Panel {
	
	int heureDepart;
	int decal;
	boolean avant;

	public EnigmeHoraire() {
		setLayout(new GridLayout(1,2));
		String s;
		decal = 1 + (int)(Math.random() * 200);
		avant = ((int)(Math.random() * 2 ) % 2 == 1);
		s = decal + " minutes";
		s += avant ? " avant " : " après ";
		heureDepart = (int)(Math.random() * 1440);
		s += heureDepart/60 + "h" +( (heureDepart%60 < 10) ? "0" : "") + heureDepart%60  ;
		Label enigme = new Label(s);
		enigme.setFont(new Font(Font.DIALOG,Font.PLAIN,20));
		add(enigme);
	}
	
	public int getReponse(){
		int rep =  avant ? heureDepart - decal : heureDepart + decal;
		if (rep < 0) 
			return rep + 1440;
		if (rep > 1439)
			return rep - 1440;
		else 
			return rep;
	}
}
