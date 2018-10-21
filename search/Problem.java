package search;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
import java.util.function.Function;


public class Problem<State extends Searchable<State,Action>,Action> {
	
	State initialState;
	State finalState;
	
	
	public boolean goal_test(State s){
		return finalState.equals(s);
	}
	
	public Problem(State start, State stop){
		initialState = start;
		finalState = stop;
	}
	
	public String toString() {
		return initialState + "\n" + " V \n\n" + finalState;	
	}
	
	public int bfs(){
		State current = this.initialState;
		List<State> dejavu = new ArrayList<State>();
		List<State> avisiter = new ArrayList<State>();
		List<Action> actions = new ArrayList<>();
		avisiter.add(current);
		int cpt=0;
		System.out.println(current);
		System.out.println("debut de bfs!");

		while(!goal_test(current)) {
			
			actions = current.getActions();
			State fils;
			for(Action a: actions) {
				fils = current.execute(a);
				if(!dejavu.contains(fils)&& !avisiter.contains(fils)){
					avisiter.add(fils);
				}
			}
			dejavu.add(current);
			avisiter.remove(current);
			current = avisiter.get(0);
			cpt++;
		}
		System.out.println(current);

		System.out.println("nb noeuds visites:"+cpt );
		return cpt;
	}
	
	
	public int dfs(){
		State current = this.initialState;
		List<State> dejavu = new ArrayList<State>();  //liste des etats deja visités
		Stack<State> avisiter = new Stack<State>();   //liste des etats a visiter
		List<Action>actions  = new ArrayList<>();
		avisiter.push(current);
		
		int cpt=0;
		System.out.println(current);

		System.out.println("debut de dfs !");
		while(!goal_test(current)) {
			
			actions = current.getActions();
			State fils;
			
			for(Action a: actions) {
				fils = current.execute(a);
				if(!dejavu.contains(fils)){
					avisiter.push(fils);
				}
			}
			
			dejavu.add(current);
			avisiter.remove(current);
			current = avisiter.peek();
			cpt++;
		}
		System.out.println(current);

		System.out.println("nb noeuds visites : "+cpt);

		return cpt;
    }
	
	public int getIndiceMin(List<Integer>list) {
		int min =10000000;
		int indiceMin = 0;
		
		for(Integer val: list) {
			if(val<min) {
				min=val;
				indiceMin = list.indexOf(val);
			}
		}
		return  indiceMin;
	}
	
	
	public int aStar(Function<State,Double> g, Function<State,Double> h ){
		
		State current = initialState;
		List<State> avisiter = new ArrayList<>();
		List<State> dejavu = new ArrayList<>();
		List<Integer>couts = new ArrayList<>();
		List<Action> actions = new ArrayList<>();
		int cpt=0;
		int f;
		int indicemin;
		while(!goal_test(current)) {
			
			actions = current.getActions();
			for(Action a : actions) {
				State fils = current.execute(a);
				
				if(!dejavu.contains(fils)) {
					fils.setValueG(fils.getValueG()+fils.getPredecessor().getValueG());
					f=fils.getHeuristic()+fils.getValueG();
					avisiter.add(fils);
					couts.add(f);
				}
			}
			
			indicemin=this.getIndiceMin(couts);
			dejavu.add(current);
			avisiter.remove(current);
			current = avisiter.get(indicemin);
			
			cpt++;
		}
		
		
		return cpt;
    }

}
