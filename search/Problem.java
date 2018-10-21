package search;

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

	/**
	 * returns all possible actions from the current state
	 * @return a list of all possible actions from the current state
	 */
	public List<Action> getActions();
	
	/**
	 * the method return the state resulting from taking action <code>a</code> in the current state
	 * @param a is the action taken
	 * @return the state resulting from taking action a in the current state
	 */
	public State execute(Action a);
	
	public Problem(State start, State stop){
		initialState = start;
		finalState = stop;
	}
	
	public String toString() {
		return initialState + "\n" + " V \n\n" + finalState;	
	}
	
	public int bfs(){
		
			List<State> visited = new ArrayList<>();
			List<State> file  =  new ArrayList<>();
			State currentState = this.initialState;
			
			int cpt=0;
			
			visite.add(initialState);

			while(currentState!=finalState)
				
				List<Action> actions = initialState.getActions();

				for(Action a : actions){
					
					State s = execute(a);
					if(!visited.contains(s)){
						file.add(s);
					}
				}
				
				while(!file.isEmpty()){
					currentState = file.get(0);
					actions = currentState.getActions();

				}

					
					

				}


			}

                return 0;
    }
	
	
	public int dfs(){
		// to be completed
        return 0;
    }
	
	public int aStar(Function<State,Double> g, Function<State,Double> h ){
		// to be completed
        return 0;
    }

}
