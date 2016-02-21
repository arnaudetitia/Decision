package Utils;

import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;
import java.awt.Label;
import java.awt.TextField;

import javax.swing.BoxLayout;

public class ReponseHeure extends Container {
	
	TextField hourLabel;
	TextField minuteLabel;

	public ReponseHeure() throws HeadlessException {
		setLayout(new FlowLayout());
		
		Font font = new Font(Font.DIALOG,Font.PLAIN,50);
		
		hourLabel = new TextField();
		hourLabel.setColumns(2);
		hourLabel.setFont(font);
		
		Label hLabel = new Label("H");
		hLabel.setFont(font);
		hLabel.setAlignment(FlowLayout.CENTER);
		
		
		minuteLabel = new TextField();
		minuteLabel.setColumns(2);
		minuteLabel.setFont(font);
		
		add(hourLabel);
		add(hLabel);
		add(minuteLabel);
	}
	
	public int getReponse(){
		int h = Integer.parseInt(hourLabel.getText());
		int m = Integer.parseInt(minuteLabel.getText());
		return h*60+m;
	}

}
