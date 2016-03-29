package Utils;

import java.awt.Button;
import java.awt.Color;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SequenceButton extends Button {
	
	boolean on;

	public SequenceButton() throws HeadlessException {
		super();
		on = true;
		setColorButton();
	}
	
	public void change(){
		on = !on;
		setColorButton();
	}

	private void setColorButton() {
		setBackground(on ? Color.YELLOW : Color.BLACK);
	}

}
