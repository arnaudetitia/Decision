package Adresse;

import java.awt.Button;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.LayoutManager;
import java.awt.Panel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import Utils.MiniJeu;

public class Jauge extends MiniJeu{

	Frame fenetre;
	float hauteur;
	int state;
	
	public Jauge() {
		super("Adresse","La Jauge","Remplir La jauge pour qu'elle soit entre les deux traits rouges");
		hauteur = 0;
		state = 0;
		addKeyListener(new JaugeAdapter());
		repaint();
	}

	@Override
	public void paint(Graphics g) {
		int w = getSize().width;
		int h = getSize().height;
		g.drawLine(w/3, 0, w/3, h);
		g.drawLine(2*(w/3), 0, 2*(w/3), h);
		if (hauteur > 50 && state == 1){
			g.setColor(getBackground());
		}
		else{
			g.setColor(Color.BLUE);
		}
		g.fillRect(w/3+1, (int)(h-(h*hauteur/100)), w/3-1 , (int)(h*hauteur/100));
		g.setColor(Color.RED);
		g.drawLine(w/3,h*5/100 ,2*(w/3) ,h*5/100 );
		g.drawLine(w/3,h*15/100 ,2*(w/3) ,h*15/100 );
	}
	
	
	private class JaugeAdapter extends KeyAdapter{
		
		@Override
		public void keyPressed(KeyEvent e) {
			state++;
			Thread t = new Thread(new Runnable(){
				@Override
				public void run() {
					if (e.getKeyCode() != KeyEvent.VK_SPACE)
						return;
					while(hauteur<101 && state == 1 ){
						hauteur += 0.25;
						try {
							Thread.sleep(7);
							repaint();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			});
			t.start();
			if (state == 2){
				if (hauteur >= 85 && hauteur <= 95 ){
					System.out.println("Gagné");
				}
				else {
					System.out.println("Perdu");
				}
			}
			
		}
	}
}
