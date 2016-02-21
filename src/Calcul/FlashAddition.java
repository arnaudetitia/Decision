package Calcul;

import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.lang.reflect.Field;

import Utils.MiniJeu;

public class FlashAddition extends MiniJeu {
	
	Frame fenetre;
	Label nombre;
	TextField field;
	int sum;
	
	public FlashAddition(Frame fen){
		super("Calcul","Flash Addition","Donner la somme des chiffres qui vont apparaitre");
		fenetre = fen;
		fen.addKeyListener(new KeyAdapter(){
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_SPACE)
					startGame();
			}
		});
		nombre = new Label();
		Font font = new Font(Font.DIALOG,Font.PLAIN,300);
		nombre.setFont(font);
		nombre.setText(">>");
		add(nombre);
		field = new TextField();
		Font font2 = new Font(Font.DIALOG,Font.PLAIN,100);
		field.setFont(font2);
		field.setColumns(3);
		field.setFocusable(false);
		field.addActionListener(new ResultFieldListener());
		add(field);
	}

	public void startGame() {
		try{
			int x;
			for (int i = 0;i<5;i++){
				x = 1 + (int)(Math.random()*20);
				sum += x;
				nombre.setText(x + "");
				Thread.sleep(1500);
			}
			nombre.setText("??");
			field.setFocusable(true);
			Thread.sleep(1500);
		}
		catch(InterruptedException e){
			e.printStackTrace();
		}
	}	
	
	public void paint(Graphics g){
		
	}
	
	private class ResultFieldListener implements ActionListener{
		
		TextField resultField;
		
		public ResultFieldListener() {
			resultField = field;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println(Integer.parseInt(resultField.getText()) == sum ? "Gagné" : "Perdu");
		}
		
	}
}
