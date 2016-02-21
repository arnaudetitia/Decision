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
	
	int soloresult ;

	public Intrus() {
		super("Calcul","L'intrus","Parmis ces 9 opérations quelle est la seule à ne pas avoir son double");
		setLayout(new GridLayout(3,3));
		Font font = new Font(Font.DIALOG,Font.PLAIN,40);
		
		Vector<Operation> operations = new Vector<>();
		for (int i = 0;i< 5;i++){
			operations.add(new Operation());
		}
		int solo = (int)(Math.random()*5);
		for (int i = 0;i<5;i++){
			int res = operations.get(i).getResult();
			if (i != solo){
				operations.add(new Operation(res));
			}
			else{
				soloresult = res;
			}
		}
		
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
		}
	}
}
