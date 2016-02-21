package Calcul;

import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.LayoutManager;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Utils.MiniJeu;
import Utils.Operation;

public class Comparaison extends MiniJeu {
	
	Operation op1;
	Label op1Label;
	Operation op2;
	Label op2Label;
	Label compLabel;
	Frame fenetre;
	boolean croiss;
	Button trueButton;
	Button falseButton;
	boolean goodAnswer;

	public Comparaison() {
		super("Calcul","Les Comparaisons","Dire si ces comparaisons sont vraies ou fausses en 30 secondes.");
		setLayout(new GridLayout(2,3));
		Font font = new Font(Font.DIALOG,Font.PLAIN,50);
		op1Label = new Label();
		compLabel = new Label();
		op2Label = new Label();
		
	
		op1Label.setFont(font);
		op1Label.setAlignment(Label.CENTER);
		
		compLabel.setFont(font);
		compLabel.setAlignment(Label.CENTER);
		
		op2Label.setFont(font);
		op2Label.setAlignment(Label.CENTER);
		
		makeAComparaison();
		
		trueButton = new Button("Vrai");
		trueButton.setBackground(Color.GREEN);
		trueButton.addActionListener(new MyButtonListener(true));
		//trueButton.setSize(new Dimension(150, 150));
		
		falseButton = new Button("Faux");
		falseButton.setBackground(Color.RED);
		falseButton.addActionListener(new MyButtonListener(false));
		//falseButton.setSize(new Dimension(300, 300));
		
		add(op1Label);
		add(compLabel);
		add(op2Label);
		add(trueButton);
		add(new Label());
		add(falseButton);
	}
	
	private void makeAComparaison(){
		op1 = new Operation();
		op1Label.setText(op1.toString());
		
		croiss = ((int)(Math.random()*2) == 1);
		compLabel.setText(isCroiss());
		
		op2 = new Operation();
		op2Label.setText(op2.toString());
		
		goodAnswer = croiss ? op1.getResult() < op2.getResult() : 
							  op1.getResult() > op2.getResult();
	}

	private String isCroiss() {
		return croiss ? "<" : ">";
	}
	
	public void paint(Graphics g){
		
	}
	
	class MyButtonListener implements ActionListener{
		
		boolean reponse;
		
		public MyButtonListener(boolean r) {
			reponse = r;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println(goodAnswer == reponse ? "Gagné" : "Perdu");
			makeAComparaison();	
		}
		
	}

}
