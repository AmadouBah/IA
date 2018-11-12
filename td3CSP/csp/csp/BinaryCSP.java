package csp;

import java.util.ArrayList;
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
	
	/**
	 * this method tells if all variables of the list are assigned or not
	 * @param variables a List
	 * @return true or false
	 */
	public boolean areAssigned(List<Variable<D>> variables) {
		for(Variable v : variables ) {
				if(!v.isAssigned())
					return false;
			}
			return true;
	}
	
	/**
	 * this method checks if the variable value satisfies the constraints of the variable
	 * @param var a Variable
	 * @param val a Value
	 * @return true or false
	 */
	public boolean check(Variable<D> var, D val) {
		List<Variable<D>> relations = new ArrayList<>();
		
		for(BinaryConstraint<D>  bc : constraints) {
			if(bc.concerns(var)) {
				relations.add(bc.depends(var));
			}
		}
		
		for (Variable<D> v : relations) {
			if(!v.satisfies(val))
				return false;
		}
		return true;
	}
	
	/**
	 * this method is the first heuristic.
	 * It search in the list which variable has the smallest domain(values)
	 * @param list the search list
	 * @return  the smallest Variable 
	 */
	public Variable<D> h1(List<Variable<D>> list){
		int min=1000000;
		Variable<D> varmin = null;
		int taille;
		
		for(Variable<D> var : list) {
			taille = var.getPossibleValues().size();
			 if(taille < min) {
				 min=taille;
				 varmin=var;
			 }
		}
		return varmin;
	}
	
	/**
	 * 
	 * @param list
	 * @return
	 */
	public Variable<D> h2(List<Variable<D>> list){
		int max=-1;
		int cpt;
		Variable<D>vres =null;
		
		for (Variable<D>  var : list) {
			cpt=0;
			for(BinaryConstraint<D> varc : constraints) {
				if(varc.concerns(var) && !varc.depends(var).isAssigned())
					cpt++;
			}
			if(cpt>max) {
				max=cpt;
				vres=var;
			}
		}
		return vres;
	}
	
	
	public List<Variable<D>> getVoisins(Variable<D> var){
		List<Variable<D>> voisins = new ArrayList<>();
		
		for(BinaryConstraint<D> bc : constraints) {
			if(bc.concerns(var))
				voisins.add(bc.depends(var));
		}
		return voisins;
	}
	
	/** this method choose the value of the variable which is the less restrictive 
	 * In others words, the least present value in the neighboring variables..
	 * @param var, the variable to analize
	 * @return  the less restrictive value 
	 */
	public D h3(Variable<D> var) {
		D valres =null;
		int cpt;
		int min = 100000;
		List<Variable<D>> voisins = this.getVoisins(var);
		
		for(D value : var.getPossibleValues()) {
			cpt=0;
			for(Variable<D> v: voisins) {
				if(v.getPossibleValues().contains(value))
					cpt++;
			}
			if(cpt<min) {
				min =cpt;
				valres = value;
			}
		}
		return valres;
	}
	/**
	 * bactrack algorhitm version 1 (sans heuristics..)
	 * @return true or false
	 */
	public boolean forwardCheck() {
		
		List<Variable<D>> listvar =vars;
		Variable<D> current = listvar.remove(0);
		
		for(D val : current.getPossibleValues()) {
			current.setValue(val);
			if(this.check(current, val)) {
				if(forwardCheck())
					return true;
			}
		}
		return false;
					
			
	}

	/*public boolean ac3() {
		
	}

	public boolean revise(BinaryConstraint<D> c) {
    }

	public boolean forwardCheckAC3() {
    }*/
		
	
}
				
			
		
		
		
		
		
    

		
	
	


