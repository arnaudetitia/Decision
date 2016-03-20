package Adresse;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import Utils.MiniJeu;

public class Chemin extends MiniJeu {
	
	Frame fenetre;
	int posX;
	int posY;
	float radius;
	boolean moveDot = false;
	int [][] laby;

	public Chemin(Frame f) {
		super("Adresse","Le Chemin","Déplacez le point bleu sans toucher les parrois");
		fenetre = f;
		posX=10;
		posY=10;
		radius = 3;
		addMouseListener(cheminMouseListener);
		addMouseMotionListener((MouseMotionListener) cheminMouseListener);
		setSize(getMaximumSize());
		laby = new int[][]{{0,4,4,4,6},
						   {9,4,10,4,7},
						   {8,4,4,4,6},
						   {9,4,10,4,7},
						   {8,4,4,4,2}};
	}

	@Override
	public void paint(Graphics g) {
		int w = getSize().width;
		int h = getSize().height;
		int mailleX = w/5;
		int mailleY = h/5;
		for (int i = 0;i<5;i++){
			for (int j=0;j<5;j++){
				drawCase(g, laby[j][i],i*mailleX, j*mailleY, mailleX+1, mailleY+1);
			}
		}
		g.setColor(Color.BLUE);
		g.fillOval(posX*w/100, posY*h/100,(int)(radius*w/100),(int)(radius*w/100));
		
	}
	
	private void drawCase(Graphics g,int type,int x,int y, int w,int h){
		switch(type){
		case 0:
			g.fillRect(x, y, w, h/4);
			g.fillRect(x, y+(int)(0.75*h), w,h/4);
			g.fillRect(x, y, w/4, h);
			break;
		case 1:
			g.fillRect(x, y, w/4, h);
			g.fillRect(x+(int)(0.75*w), y, w/4,h);
			g.fillRect(x, y, w, h/4);
			break;
		case 2:
			g.fillRect(x, y, w, h/4);
			g.fillRect(x, y+(int)(0.75*h), w,h/4);
			g.fillRect(x+(int)(0.75*w), y, w/4,h);
			break;
		case 3:
			g.fillRect(x, y, w/4, h);
			g.fillRect(x+(int)(0.75*w), y, w/4,h);
			g.fillRect(x, y+(int)(0.75*h), w,h/4);
			break;
		case 4:
			g.fillRect(x, y, w, h/4);
			g.fillRect(x, y+(int)(0.75*h), w,h/4);
			break;
		case 5 : 
			g.fillRect(x, y, w/4, h);
			g.fillRect(x+(int)(0.75*w), y, w/4,h);
			break;
		case 6:
			g.fillRect(x, y, w, h/4);
			g.fillRect(x+(int)(0.75*w), y, w/4,h);
			g.fillRect(x, y+(int)(0.75*h), w/4, h/4);
			break;
		case 7 :
			g.fillRect(x, y+(int)(0.75*h), w,h/4);
			g.fillRect(x+(int)(0.75*w), y, w/4,h);
			g.fillRect(x, y, w/4, h/4);
			break;
		case 8:
			g.fillRect(x, y, w/4, h);
			g.fillRect(x, y+(int)(0.75*h), w,h/4);
			g.fillRect(x+(int)(0.75*w), y, w/4, h/4);
			break;
		case 9:
			g.fillRect(x, y, w, h/4);
			g.fillRect(x, y, w/4, h);
			g.fillRect(x+(int)(0.75*w), y+(int)(0.75*h), w/4, h/4);
			break;
		case 10 :
			g.fillRect(x, y, w, (int)(0.35*h));
			g.fillRect(x, y+(int)(0.65*h), w, (int)(0.35*h));
			break;
		}
	}
	
	MouseListener cheminMouseListener = new MouseAdapter() {
		@Override
		public void mousePressed(MouseEvent e) {
			int w = getSize().width;
			int h = getSize().height;
			int clickX = 100*e.getX()/w;
			int clickY = 100*e.getY()/h;
			Point click = new Point(clickX,clickY);
			Point center = new Point(posX,posY);
			if (click.distance(center) <= radius){
				System.out.println("<<OK>>");
				moveDot = true;
				posX = clickX-(int)radius/2;
				posY = clickY-(int)radius/2;
				repaint();
			}
		}
		
		@Override
		public void mouseDragged(MouseEvent e) {
			int w = getSize().width;
			int h = getSize().height;
			int clickX = 100*e.getX()/w;
			int clickY = 100*e.getY()/h;
			posX = clickX-(int)radius/2;
			posY = clickY-(int)radius/2;
			repaint();
			checkContact(clickX,clickY,posX/20,posY/20);
		}

		private void checkContact(int clickX,int clickY,int i, int j) {
			int w = getSize().width;
			int h = getSize().height;
			int mailleX = w/5;
			int mailleY = h/5;
			int x = i*mailleX;
			int y = j*mailleY;
			int type = laby[j][i];
			switch(type){
			case 0:
				if((Math.abs((clickY%20)-10) > 5-radius/2) || (((clickX%20) < 5+radius/2) && clickX != 20)  ){
					System.out.println("Perdu 0");
				}
				break;
			case 1:
				break;
			case 2:
				break;
			case 3:
				break;
			case 4:
				if(Math.abs((clickY%20)-10) > 5-radius/2){
					System.out.println("Perdu 4");
				}
				break;
			case 5:
				if(Math.abs((clickX%20)-10) > 5-radius/2){
					System.out.println("Perdu 5");
				}
				break;
			case 6:
				break;
			case 7:
				break;
			case 8:
				break;
			case 9:
				break;
			case 10:
				if(Math.abs((float)(clickY%20)-10 ) > 3.5-radius/2){
					System.out.println("Perdu 10");
				}
				break;
			}
			
		}
	};
}
