package csp;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class BinaryCSP<D> {

	List<Variable<D>> vars;
	List<BinaryConstraint<D>> constraints;

	public BinaryCSP() {
		vars = new LinkedList<Variable<D>>();
		constraints = new LinkedList<BinaryConstraint<D>>();
	}

	public boolean ac3() {
		
	}

	public boolean revise(BinaryConstraint<D> c) {
    }

	public boolean forwardCheckAC3() {
    }

	
	public boolean forwardCheck() {
		Stack<Variable<D>> backup = new Stack<>();
		List<D> possibles;
		
		while(!this.areAssigned(vars)) {
			Variable<D> current = vars.get(0);
			possibles = current.getPossibleValues();
			
			if(possibles.isEmpty()) {
				
			}
			boolean test =false;
			
			for(D val : possibles) {
				for(Variable<D> var : vars) {
					if(var.satisfies(val)) 
						test = true;
				}
				if(test) {
					for(Variable<D> v : vars) {
						v.removePossibleValue(val);
					}
					current.setValue(val);
					break;
				}
			}
			current.toString();
			backup.push(current);
			vars.remove(current);
			
		}
				
						
					
				
	
	}
			
			public boolean areAssigned(List<Variable<D>> variables) {
				for(Variable v : variables ) {
						if(!v.isAssigned())
							return false;
					}
					return true;
			}
				
			
		
		
		
		
		
    

		

}
