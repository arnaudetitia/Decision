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
	int maille;
	int state; 
	Vector<Point> cases;
	Point removed;

	public Fugitif(Frame fen) {
		super("Mémoire","Le Fugitif", "Quelle case n'est plus rouge ?");
		fenetre = fen;
		maille = fenetre.getWidth()/10;
		cases = new Vector<>();
		generateCases();
		state = 0;
		addMouseListener(new FugitifAdapter());
	}

	public void paint(Graphics g){
		g.setColor(Color.BLACK);
		for (int i = 0;i < 11;i++){
			g.drawLine(i*maille, 0,i*maille , fenetre.getHeight());
			g.drawLine(0, i*maille,fenetre.getWidth() , i*maille);
		}
		switch(state){
			case 1: case 3 :
				g.setColor(Color.RED);
				for (Point c : cases){
					g.fillRect((int)c.getX()*maille+1, (int)c.getY()*maille+1, maille-1, maille-1);
				}
				break;
			case 2:
				g.setColor(Color.BLUE);
				for (int i = 0;i < 11;i++){
					for (int j = 0;j < 11;j++){
						g.fillRect(i*maille+1, j*maille+1, maille-1, maille-1);
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
				Point response = new Point(e.getX()/maille,e.getY()/maille);
				System.out.println(response.equals(removed) ? "Gagné" : "Perdu");
			}
		}
	}

}
