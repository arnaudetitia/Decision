package Adresse;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.peer.KeyboardFocusManagerPeer;

import Utils.MiniJeu;

public class Passage extends MiniJeu {
	
	double pos = 0.025;
	double y1 = 0.5;
	double y2 = 0.5;
	double y3 = 0.5;
	double delta = 0.01;
	boolean win  = true; 

	public Passage() {
		super("Adresse","Le passage","Faites passer le carré rouge dun bout à lautre sans toucher les parrois");
		addKeyListener(new PassageListener());
		startGame();
	}

	@Override
	public void paint(Graphics g) {
		int w = getSize().width;
		int h = getSize().height;
		g.setColor(Color.BLUE);
		g.fillRect((int)(pos*w),(int)(0.475*h) , (int)(0.05*w), (int)(0.05*h));
		g.setColor(Color.RED);
		
		// gap = 0.30
		g.fillRect((int)(0.15*w),0 , (int)(0.1*w), (int)((y1-0.15)*h));
		g.fillRect((int)(0.15*w),(int)((y1+0.15)*h) , (int)(0.1*w), (int)((1.0-(y1-0.15))*h));
		// gap = 0.25
		g.fillRect((int)(0.45*w),0 , (int)(0.1*w), (int)((y2-0.125)*h));
		g.fillRect((int)(0.45*w),(int)((y2+0.125)*h) , (int)(0.1*w), (int)((1.0-(y2-0.125))*h));
		// gap = 0.20
		g.fillRect((int)(0.75*w),0 , (int)(0.1*w), (int)((y3-0.1)*h));
		g.fillRect((int)(0.75*w),(int)((y3+0.1)*h) , (int)(0.1*w), (int)((1.0-(y3-0.1))*h));
	}
	
	private void startGame(){
		new Thread(new Runnable() {

			
			@Override
			public void run() {
				try{
					while(win){
						y1 += delta;
						y2 += delta;
						y3 += delta;
						if ((y1 >= 0.9) || (y1 <= 0.1)){
							delta = -delta;
						}
						repaint();
						Thread.sleep(25);
					}
				}
				catch(InterruptedException e){
					
				}
			}
		}).start();
	}
	
	
	
	private class PassageListener extends KeyAdapter{
		
		Runnable moving = new Runnable(){
			@Override
			public void run() {
				try{
					for (int i = 0;i<30;i++){
						if (win) pos += 0.01;
						repaint();
						if (pos >= 0.10 && pos <= 0.25 ){
							if ((y1 <= 0.375) || (y1 >= 0.675)){
								win = false;
								System.out.println("Perdu 1");
							}
						}
						if (pos >= 0.40 && pos <= 0.55 ){
							if ((y2 <= 0.4) || (y2 >= 0.6)){
								System.out.println("Perdu 2");
								win = false;
							}
						}
						if (pos >= 0.70 && pos <= 0.85 ){
							if ((y3 <= 0.425) || (y3 >= 0.575)){
								win = false;
							}
						}
						Thread.sleep(25);
					}
				}
				catch(InterruptedException e){
					System.out.println(e);
				}
			}
		};
		
		
		
		@Override
		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_SPACE){
				Thread t = new Thread(moving);
				t.start();
			}
			if (e.getKeyCode() == KeyEvent.VK_SHIFT){
				y3 += delta;
				if ((y1 >= 0.9) || (y1 <= 0.1)){
					delta = -delta;
				}
				repaint();
				System.out.println(y3);
			}
		}
	}

}
