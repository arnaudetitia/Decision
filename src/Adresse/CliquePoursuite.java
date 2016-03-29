package Adresse;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.LayoutManager;
import java.awt.Panel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JProgressBar;

import Utils.MiniJeu;

public class CliquePoursuite extends MiniJeu{
	
	Frame fenetre;
	float squareX;
	float squareY;
	int cpt = 0;

	public CliquePoursuite() {
		super("Adresse","Clique Poursuite", "Cliquer sur 10 cibles en 20 secondes");
		squareX = (float)(Math.random()*90);
		squareY = (float)(Math.random()*90);
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				float clickX= e.getX();
				float clickY= e.getY();
				float w = getSize().width;
				float h = getSize().height;
				float tauX = 100*clickX/w;
				float tauY = 100*clickY/h;
				float marge = 10;
				if (tauX-squareX >= 0
					&& tauX-squareX <= marge
					&& tauY-squareY >= 0
					&& tauY-squareY <= marge){
					System.out.println("<<OK>>" + cpt++);
					squareX = (float)(Math.random()*90);
					squareY = (float)(Math.random()*90);
					repaint();
				}
			}
		});
	}
	
	public void paint(Graphics g){
		float w = getSize().width;
		float h = getSize().height;
		
		g.setColor(Color.RED);
		g.fillRect((int)(w*squareX/100),(int)(h*squareY/100) ,(int)(w/10), (int)(w/10));
	}

}
