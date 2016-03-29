package Memoire;

import java.awt.Button;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Polygon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;
import java.util.Collections;
import java.util.Vector;

import javax.swing.plaf.basic.BasicOptionPaneUI.ButtonActionListener;

import Utils.MiniJeu;

public class Memory extends MiniJeu{
	
	Vector<Integer> ordre = new Vector<Integer>();
	Vector<Button> buttons = new Vector<Button>();
	Integer forme1;
	Integer forme2;
	Integer button1;
	Integer button2;
	Integer pairesTrouves;
	
	
	public Memory() {
		super("Mémoire", "Le Memory", "Formez les 8 paires en 30 secondes");
		setLayout(new GridLayout(4,4));
		for(int i=0;i<16;i++){
			Button b = new Button("Button" + i);
			add(b);
			buttons.add(b);
			b.addActionListener(new MemoryButtonLisener(b,i));
		}
		for(int i=0;i<8;i++){
			ordre.add(i);
			ordre.add(i);
		}
		for(int i=0;i<30;i++){
			int a = (int)(Math.random()*ordre.size()-1);
			int b = (int)(Math.random()*ordre.size()-1);
			Collections.swap(ordre, a, b);
		}
		pairesTrouves = 0;
	}

	@Override
	public void paint(Graphics g) {
		for(int i=0;i<16;i++){
			drawForme(g, i%4, i/4, ordre.get(i));
		}
	}
	
	private void drawForme(Graphics g,int i,int j,int type){
		int mailleX = getSize().width/4;
		int mailleY = getSize().height/4;
		switch(type){
		case 0:
			g.setColor(Color.RED);
			g.fillOval(i*mailleX+5, j*mailleY+5,mailleX-10 , mailleY-10);
			break;
		case 1:
			g.setColor(Color.BLUE);
			g.fillRect(i*mailleX+5, j*mailleY+5,mailleX-10 , mailleY-10);
			break;
		case 2:
			g.setColor(Color.GREEN);
			Polygon triangle = new Polygon();
			triangle.addPoint(i*mailleX+5, (j+1)*mailleY-5);
			triangle.addPoint((i+1)*mailleX-5, (j+1)*mailleY-5);
			triangle.addPoint((int)((i+0.5)*mailleX), j*mailleY+5);
			g.fillPolygon(triangle);
			break;
		case 3 :
			g.setColor(Color.YELLOW);
			Polygon losange = new Polygon();
			losange.addPoint((int)((i+0.5)*mailleX), j*mailleY+5);
			losange.addPoint(i*mailleX+5, (int)((j+0.5)*mailleY));
			losange.addPoint((int)((i+0.5)*mailleX), (j+1)*mailleY-5);
			losange.addPoint((i+1)*mailleX-5, (int)((j+0.5)*mailleY));
			g.fillPolygon(losange);
			break;
		case 4:
			g.setColor(Color.PINK);
			g.fillRect((int)((i+0.4)*mailleX), j*mailleY+5 , (int)(0.2*mailleX),mailleY-5);
			g.fillRect(i*mailleX+5, (int)((j+0.4)*mailleY) , mailleX-5,(int)((0.2)*mailleY));
			break;
		case 5:
			g.setColor(Color.ORANGE);
			Polygon triangleBas = new Polygon();
			triangleBas.addPoint(i*mailleX+5, j*mailleY+5);
			triangleBas.addPoint((i+1)*mailleX-5, j*mailleY+5);
			triangleBas.addPoint((int)((i+0.5)*mailleX), (j+1)*mailleY-5);
			g.fillPolygon(triangleBas);
			break;
		case 6:
			g.setColor(Color.DARK_GRAY);
			Polygon octo = new Polygon();
			octo.addPoint((int)((i+0.33)*mailleX), j*mailleY+5 );
			octo.addPoint((int)((i+0.66)*mailleX), j*mailleY+5 );
			octo.addPoint((i+1)*mailleX-5, (int)((j+0.33)*mailleY));
			octo.addPoint((i+1)*mailleX-5, (int)((j+0.66)*mailleY));
			octo.addPoint((int)((i+0.66)*mailleX), (j+1)*mailleY-5 );
			octo.addPoint((int)((i+0.33)*mailleX), (j+1)*mailleY-5 );
			octo.addPoint(i*mailleX+5, (int)((j+0.66)*mailleY));
			octo.addPoint(i*mailleX+5, (int)((j+0.33)*mailleY));
			g.fillPolygon(octo);
			break;
		default:
			g.setColor(new Color(165,42,42));
			g.fillArc(i*mailleX+5, j*mailleY+5, mailleY-5, mailleX-5, 0, 180);
		}
	}
	
	private class MemoryButtonLisener implements ActionListener{

		Button button;
		int indexButton;
		
		public MemoryButtonLisener(Button b,int i) {
			button = b;
			indexButton = i;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			button.setVisible(false);
			if ((button1 != null) && (button2 != null)){
				if (forme1 != forme2){
					buttons.get(button1).setVisible(true);
					buttons.get(button2).setVisible(true);
				}
				button1 = null;
				button2 = null;
			}
			if (button1==null){
				button1 = indexButton;
				return;
			}
			if (button2==null){
				button2 = indexButton;
				forme1 = ordre.get(button1);
				forme2 = ordre.get(button2);
				if (forme1 == forme2){
					pairesTrouves++;
				}
				if (pairesTrouves == 8){
					System.out.println("Gagné");
				}
			}
		}
	}

}
