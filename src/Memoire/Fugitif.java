package Memoire;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

import Utils.MiniJeu;

public class Fugitif extends MiniJeu {
	
	Frame fenetre;
	int mailleX;
	int mailleY;
	int state; 
	Vector<Point> cases;
	Point removed;

	public Fugitif(Frame fen) {
		super("Mémoire","Le Fugitif", "Quelle case n'est plus rouge ?");
		fenetre = fen;
		mailleX = 8*fenetre.getWidth()/100;
		mailleY = 8*fenetre.getHeight()/100;
		cases = new Vector<>();
		generateCases();
		state = 0;
		addMouseListener(new FugitifAdapter());
	}

	public void paint(Graphics g){
		mailleX = 8*fenetre.getWidth()/100;
		mailleY = 8*fenetre.getHeight()/100;
		int w = fenetre.getWidth();
		int h = fenetre.getHeight();
		int deltaX = 10*w/100; 
		int deltaY = 10*h/100; 
		g.setColor(Color.BLACK);
		for (int i = 0;i < 11;i++){
			g.drawLine(deltaX + i*mailleX, deltaY,deltaX +i*mailleX , fenetre.getHeight()-deltaY);
			g.drawLine(deltaX, deltaY+i*mailleY,fenetre.getWidth()-deltaX ,deltaY+i*mailleY);
		}
		switch(state){
			case 1: case 3 :
				g.setColor(Color.RED);
				for (Point c : cases){
					g.fillRect(deltaX+(int)c.getX()*mailleX+1, deltaY+(int)c.getY()*mailleY+1, mailleX-1, mailleY-1);
				}
				break;
			case 2:
				g.setColor(Color.BLUE);
				for (int i = 0;i < 10;i++){
					for (int j = 0;j < 10;j++){
						g.fillRect(deltaX+i*mailleX+1, deltaY+j*mailleY+1, mailleX-1, mailleY-1);
					}
				}
				break;
			default: break;
		}
	}
	
	private void generateCases() {
		int a;
		int b;
		Point c;
		for (int i =0;i < 25 ; i++){
			do{
				a = (int)(Math.random()*10);
				b = (int)(Math.random()*10);
				c = new Point(a,b);
			}while(cases.contains(c));
			cases.add(c);
		}
	}	
	
	
	private class FugitifAdapter extends MouseAdapter{
		
		@Override
		public void mousePressed(MouseEvent e) {
			state++;
			if (state==1){
				new Thread(new Runnable(){
					@Override
					public void run() {
						repaint();
						try {
							Thread.sleep(10000);
							state++;
							repaint();
							Thread.sleep(5000);
							removed = cases.remove((int)(Math.random()*cases.size()));
							state++;
							repaint();
						} catch (InterruptedException e) {
							System.out.println(e);
						}
					}
				}).start();
			}
			if (state >= 3){
				int w = fenetre.getWidth();
				int h = fenetre.getHeight();
				int deltaX = 10*w/100; 
				int deltaY = 10*h/100;
				Point response = new Point((e.getX()-deltaX)/mailleX,(e.getY()-deltaY)/mailleY);
				System.out.println(response.equals(removed) ? "Gagné" : "Perdu");
			}
		}
	}

}
