package Calcul;

import java.util.Vector;

public class Operation {
	
	enum Type{NUM,ADD,SUB,MUL,DIV};
	
	int type;
	int result;
	int terme1;
	int terme2;

	public Operation() {
		type = (int)(Math.random()*Type.values().length);
		result =  1+(int)(Math.random()*50);
		generateOperation();
	}
	
	public Operation(int res){
		type = (int)(Math.random()*Type.values().length);
		result = res;
		generateOperation();
	}
	
	private void generateOperation(){
		switch(type){
		case 1 :
			terme1 = (int)(Math.random()*result);
			terme2 = result - terme1;
			break;
		case 2 :
			terme2 = (int)(Math.random()*30);
			terme1 = result + terme2;
			break;
		case 3 : 
			while (divisors().isEmpty()){
				result =  (int)(Math.random()*51);
			}
			int ind =  (int)(Math.random()*divisors().size());
			terme2 = divisors().get(ind);
			terme1 = result / terme2;
			break;
		case 4 : 
			terme2 = 1+(int)(Math.random()*15);
			terme1 = result * terme2;
			break;
	}
	}
	
	private Vector<Integer> divisors(){
		Vector<Integer> div = new Vector<>();
		for (Integer d = 2; d<=Math.sqrt(result);d++){
			if (result%d == 0){
				div.add(d);
			}
		}
		return div;
	}
	
	@Override
	public String toString() {
		String op = "";
		switch(type){
			case 0:
				return String.valueOf(result); 
			case 1 :
				op = "+"; 
				break;
			case 2 :
				op = "-"; 
				break;
			case 3 : 
				op = "*";
				break;
			case 4 : 
				op = "/";
				break;
		}
		return terme1 + op + terme2;
	}
		
	public int getResult() {
		return result;
	}

}
