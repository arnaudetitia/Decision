package Utils;

import java.awt.Button;
import java.awt.HeadlessException;

public class OperationButton extends Button {
	
	Operation operation;

	public OperationButton(Operation o) throws HeadlessException {
		operation = o;
		setLabel(o.toString());
	}

	public Operation getOperation() {
		return operation;
	}

}
