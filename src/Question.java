import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.TextArea;
import java.util.StringTokenizer;

import javax.swing.JTextPane;

import Utils.MiniJeu;

public class Question extends MiniJeu {

	public Question(String r) {
		super("Culture générale",null, r);
		setLayout(new GridBagLayout());
		Label l = new Label("Bonjour");
		Label l2 = new Label("Au revoire");
		Font f = new Font(Font.DIALOG,Font.PLAIN,30);
		l.setFont(f);
		l2.setFont(f);
		GridBagConstraints c = new GridBagConstraints();
		
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 0.5;
		c.weighty = 0.25;
		
		c.gridx=0;
		c.gridy=2;
		Button b1 = new Button("Réponse A");
		add(b1,c);
		
		c.gridx=1;
		c.gridy=2;
		Button b2 = new Button("Réponse B");
		//b2.setSize(w/2, h/4);
		add(b2,c);
		
		c.gridx=0;
		c.gridy=3;
		Button b3 = new Button("Réponse C");
		//b3.setSize(w/2, h/4);
		add(b3,c);
		
		c.gridx=1;
		c.gridy=3;
		Button b4 = new Button("Réponse D");
		//b4.setSize(w/2, h/4);
		add(b4,c);
		
		c.gridx=0;
		c.gridy=0;
		c.gridwidth = 2;
		c.weightx = 1.0;
		add(l,c);
		
		c.gridx=0;
		c.gridy=1;
		add(l2,c);
		
	}

	@Override
	public void paint(Graphics g) {
		g.drawString("La question", 0, 0);
	}

}
