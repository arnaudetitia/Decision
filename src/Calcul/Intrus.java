package Calcul;

import java.awt.Button;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;
import java.util.Vector;

import Utils.MiniJeu;
import Utils.Operation;
import Utils.OperationButton;

public class Intrus extends MiniJeu {
	
	int inPlay;
	int soloresult ;

	public Intrus(String s) {
		super("Calcul","L'intrus","Parmis ces 9 opérations quelle est la seule à ne pas avoir son double");
		setLayout(new GridLayout(3,3));
		Font font = new Font(Font.DIALOG,Font.PLAIN,40);
		
		String[] result = s.split("#");
		Vector<Operation> operations = new Vector<>();
		for (int i = 0;i< 4;i++){
			operations.add(new Operation(Integer.parseInt(result[i])));
			operations.add(new Operation(Integer.parseInt(result[i])));
		}
		soloresult= Integer.parseInt(result[4]);
		operations.add(new Operation(soloresult));
		inPlay = Integer.parseInt(result[5]);
		
		int [] order = new int[9];
		for (int i = 0;i<9;i++){
			order[i] = i;
		}
		
		int nbChanges = 10 + (int)(Math.random()*20);
		for (int i = 0; i<nbChanges;i++){
			int a = (int)(Math.random()*9);
			int b = (int)(Math.random()*9);
			
			int x = order[a];
			order[a] = order[b];
			order[b] = x;
		}
		
		for (int i = 0; i<9;i++){
			OperationButton b = new OperationButton(operations.get(order[i]));
			b.setFont(font);
			b.addActionListener(new OperationButtonListener(b.getOperation()));
			if (inPlay == 0) b.setEnabled(false);
			add(b);
		}
	}
	
	public void paint(Graphics g){
		
	}
	
	private class OperationButtonListener implements ActionListener{
		
		Operation operation;
		
		public OperationButtonListener(Operation o) {
			operation = o;
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			System.out.println(soloresult == operation.getResult() ? "Gagné" : "Perdu");
			//System.out.println(operation.getResult());
		}
	}
}
