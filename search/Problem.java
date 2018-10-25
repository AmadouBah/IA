package search;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
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
	
	/**
	 * Parcours en largeur
	 * @return le nombre de noeuds visités
	 */
	public int bfs(){
		State current = this.initialState;
		List<State> dejavu = new ArrayList<State>();
		List<State> avisiter = new ArrayList<State>();
		List<Action> actions = new ArrayList<>();
		avisiter.add(current);
		int cpt=0;
		
		System.out.println("debut de bfs!");
		System.out.println(current);

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
		
		System.out.println("fin de bfs!");
		System.out.println(current);
		System.out.println("nb noeuds visites:"+cpt );
		return cpt;
	}
	
	/**
	 * Parcours en profondeur
	 * @return le nombre de noeuds visités
	 */
	public int dfs(){
		State current = this.initialState;
		List<State> dejavu = new ArrayList<State>();  //liste des etats deja visités
		Stack<State> avisiter = new Stack<State>();   //liste des etats a visiter
		List<Action>actions  = new ArrayList<>();
		avisiter.push(current);
		
		int cpt=0;

		System.out.println("debut de dfs !");
		System.out.println(current);

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
		
		System.out.println("Fin de dfs !");
		System.out.println(current);
		System.out.println("nb noeuds visites : "+cpt);

		return cpt;
    }
	
	
	
	/**
	 * méthode retournant le State qui a le cout f minimum
	 * @param avisiter liste contenant les states a comparer
	 * @return le state de cout f minimum
	 */
	public State getStateMin(List<State>avisiter) {
		State next=avisiter.get(0);
		int min=next.getHeuristic()+next.getValueG();
		int f;
		for(State s: avisiter) {
			f=s.getHeuristic()+s.getValueG();
			if(f<min) {
				min = f;
				next = s;
			}
		}
		return next;
	}
	
	
	public int aStar(Function<State,Double> g, Function<State,Double> h ){
		
		State current = initialState;
		List<State> avisiter = new ArrayList<>();
		List<State> dejavu = new ArrayList<>();
		List<Action> actions = new ArrayList<>();
		int cpt=0;
		
		System.out.println("Debut de a*!");
		System.out.println(current);
		
		while(!goal_test(current)) {
			actions = current.getActions();
			
			for(Action a : actions) {
				State fils = current.execute(a);
				
				if(!dejavu.contains(fils) && !avisiter.contains(fils)) {
					fils.setValueG(g.apply(fils));
					fils.setValueH(h.apply(fils));
					avisiter.add(fils);
				}
			}
			dejavu.add(current);
			avisiter.remove(current);
			current = getStateMin(avisiter);
			cpt++;
		}
		System.out.println("Fin de a*!");
		System.out.println(current);
		System.out.println("nb noeuds visites : "+cpt);
		return cpt;
    }

}
