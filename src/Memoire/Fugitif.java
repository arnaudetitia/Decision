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

	public Fugitif() {
		super("Mémoire","Le Fugitif", "Quelle case n'est plus rouge ?");
		mailleX = (int) (10*getSize().getWidth()/100);
		mailleY = (int) (10*getSize().getHeight()/100);
		cases = new Vector<>();
		generateCases();
		state = 0;
		addMouseListener(new FugitifAdapter());
	}

	public void paint(Graphics g){
		int w = getSize().width;
		int h = getSize().height; 
		mailleX = w/10;
		mailleY =h/10;
		g.setColor(Color.BLACK);
		for (int i = 0;i < 10;i++){
			for(int j=0;j<10;j++)
			g.drawRect(i*mailleX, j*mailleY, mailleX, mailleY);
		}
		switch(state){
			case 1: case 3 :
				g.setColor(Color.RED);
				for (Point c : cases){
					g.fillRect((int)(c.getX()*mailleX+1), (int)(c.getY()*mailleY+1), mailleX-1, mailleY-1);
				}
				break;
			case 2:
				g.setColor(Color.BLUE);
				for (int i = 0;i < 10;i++){
					for (int j = 0;j < 10;j++){
						g.fillRect(i*mailleX+1, j*mailleY+1, mailleX-1, mailleY-1);
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
				removeMouseListener(getMouseListeners()[0]);
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
							addMouseListener(new FugitifAdapter());
						} catch (InterruptedException e) {
							System.out.println(e);
						}
					}
				}).start();
			}
			if (state >= 3){
				int w = getSize().width;
				int h = getSize().height; 
				Point response = new Point(e.getX()/mailleX,e.getY()/mailleY);
				System.out.println(response.equals(removed) ? "Gagné" : "Perdu");
			}
		}
	}

}
