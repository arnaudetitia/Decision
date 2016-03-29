package Memoire;

import java.awt.Button;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.Vector;

import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;

import Utils.MiniJeu;
import Utils.SequenceButton;

public class Sequence extends MiniJeu {
	
	Vector<SequenceButton> ordre = new Vector<>();
	Vector<SequenceButton> ordreToClick = new Vector<>();
	int av;

	public Sequence() {
		super("Mémore", "Sequence", "Cliquez le boutons dans l'ordre inverse ou ils s'éteignent");
		setLayout(new GridLayout(2, 4));
		for (int i = 0;i<8;i++){
			SequenceButton sb = new SequenceButton();
			add(sb);
			sb.addActionListener(new SequenceListener(sb));
			ordre.add(sb);
		}
		for (int i=0;i<30;i++){
			int a = (int)(Math.random() * ordre.size());
			int b = (int)(Math.random() * ordre.size());
			Collections.swap(ordre, a, b);
		}
		for (int i = 0;i<8;i++){
			ordreToClick.add(ordre.get(ordre.size()-1-i));
		}
		av = 0;
		startGame();
	}

	@Override
	public void paint(Graphics g) {
	}
	
	private void startGame() {
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				try 
				{
					for (SequenceButton sb : ordre){
						Thread.sleep(1000);
						sb.change();
					}
					
				} 
				catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}).start();
	}
	
	private class SequenceListener implements  ActionListener {
		
		SequenceButton button;
		
		public SequenceListener(SequenceButton sb){
			button = sb;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if (button.equals(ordreToClick.get(av))){
				button.change();
				av++;
			}
			else {
				System.out.println("Perdu");
			}
		}
	};

}
