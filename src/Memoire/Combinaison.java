package Memoire;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import Utils.MiniJeu;

public class Combinaison extends MiniJeu {

	Frame fenetre;
	int av;
	
	private static final Color[] colorTab =
			new Color[]{Color.RED,Color.BLUE,Color.GREEN,Color.YELLOW,Color.ORANGE,new Color(255,0,255)};	
	
	private static int[] order = {0,1,2,3,4,5};
	private static int[] orderToClick = {0,1,2,3,4,5};
	public Combinaison(Frame fen) {
		super("Memoire", "Combinaison", "Clique sur les cases dans l'ordre");
		fenetre = fen;
		av = 0;
		for (int i = 0;i<=30;i++ ){
			int a = (int)(Math.random()*6);
			int b = (int)(Math.random()*6);
			int c = (int)(Math.random()*6);
			int d = (int)(Math.random()*6);
			int x = order[a];
			order[a] = order[b];
			order[b] = x;
			int y = orderToClick[c];
			orderToClick[c] = orderToClick[d];
			orderToClick[d] = y;
		}
		
		addMouseListener(new MyMousListener());
	}

	@Override
	public void paint(Graphics g) {
		float debX = (float) (fenetre.getWidth() * 0.2);
		float debY = (float) (fenetre.getHeight() * 0.1);
		float debX2 = (float) (fenetre.getWidth() * 0.05);
		float debY2 = (float) (fenetre.getHeight() * 0.6);
		float mailleX = (float) (fenetre.getWidth() * 0.2);
		float mailleY = (float) (fenetre.getHeight()* 0.2);
		float ajout = (float) (fenetre.getWidth()* 0.05);
		for (int i=0;i<2;i++){
			for (int j=0;j<3;j++){
				g.drawRect((int)(debX+j*mailleX), (int)(debY+i*mailleY), (int)mailleX,(int) mailleY);
				g.setColor(colorTab[order[i*3+j]]);
				g.fillRect((int)(debX+j*mailleX)+1, (int)(debY+i*mailleY)+1, (int)mailleX-1,(int) mailleY-1);
				g.setColor(Color.BLACK);
			}
		}
		
		for (int i=av;i<6;i++){
			g.drawRect((int)(debX2+i*(mailleX/2+ajout)), (int)(debY2), (int)mailleX/2,(int) mailleY/2);
			g.setColor(colorTab[orderToClick[i]]);
			g.fillRect((int)(debX2+i*(mailleX/2+ajout))+1, (int)(debY2)+1, (int)mailleX/2-1,(int) mailleY/2-1);
			g.setColor(Color.BLACK);
		}
	}
	
	private class MyMousListener extends MouseAdapter{
		
		@Override
		public void mouseClicked(MouseEvent e) {
			float tauX = (float)((float)e.getX()/(float)fenetre.getWidth());
			float tauY = (float)((float)e.getY()/(float)fenetre.getHeight());
			int c = (int)((tauX-0.2)/0.2);
			int l = (int)((tauY-0.1)/0.2);
			if (c <3 && l <2){
				if (colorTab[orderToClick[av]].equals(colorTab[order[l*3+c]])){
					av++;
					repaint();
				}
				else {
					av = 0;
					repaint();
				}
			}
		}
	}

}
