package Adresse;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.LayoutManager;
import java.awt.Panel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class CliquePoursuite extends Panel{
	
	Frame fenetre;
	float squareX;
	float squareY;
	int cpt = 0;

	public CliquePoursuite(Frame fen) {
		fenetre = fen;
		squareX = (float)(Math.random()*90);
		squareY = (float)(Math.random()*90);
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				float clickX= e.getX();
				float clickY= e.getY();
				float w = fenetre.getWidth();
				float h = fenetre.getHeight();
				float tauX = 100*clickX/w;
				float tauY = 100*clickY/h;
				float marge = fenetre.getWidth()/10;
				if (tauX-squareX >= 0
					&& tauX-squareX <= marge
					&& tauY-squareY >= 0
					&& tauY-squareY <= marge){
					squareX = (float)(Math.random()*90);
					squareY = (float)(Math.random()*90);
					repaint();
				}
			}
		});
	}
	
	public void paint(Graphics g){
		int w = fenetre.getWidth();
		int h = fenetre.getHeight();
		
		g.setColor(Color.RED);
		g.fillRect((int)(w*squareX/100),(int)(h*squareY/100) ,w/10, w/10);
	}

	public CliquePoursuite(LayoutManager arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

}
