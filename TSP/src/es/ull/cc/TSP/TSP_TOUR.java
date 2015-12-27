package es.ull.cc.TSP;

import java.util.Random;
import java.util.ArrayList;

public class TSP_TOUR {
	
	static final double INF = Double.MAX_VALUE;
	
	private TSP_INSTANCE tsp;
	
	private ArrayList<Integer> tour;
	
	private double tourValue;
	
	public TSP_TOUR(){}
	
	public TSP_TOUR(TSP_INSTANCE tsp_i){
		
		load(tsp_i);
	}
	
	public void load(TSP_INSTANCE tsp_i){
		
		tsp = tsp_i;
		
		tour = new ArrayList<Integer> ();
		
		tourValue = INF;
	}
	
	public void print(){
		
		System.out.println("Valor de la cota superior :" + tourValue);
		
		System.out.println("Tour: ");
		
		for(int i=0; i<tour.size(); i++){
			
			System.out.print(" " + tour.get(i) + " ");
		}
		
		System.out.println();
	}
	
	public void newTour(){
		
		ArrayList<Integer> aux = NNAlgoritm();
		
		aux = twoOPT(aux);
		
		update(aux);
	}
	
	
	public double getTourValue(){
		
		return tourValue;
	}
	
	public ArrayList<Integer> getTour(){
		
		return tour;
	}
	
	public void searchTreeBB(){
		
		TREE_BB treeBB = new TREE_BB(tour, tourValue, tsp);
		
		update(treeBB.getFinalTour());
	}

/*****************************************************************************************************************************************************************/

	private ArrayList<Integer> NNAlgoritm(){
		
		ArrayList<Integer> aux = new ArrayList<Integer> ();
		
		Integer start;
		
		Boolean [] visited = new Boolean[tsp.getN()];
		
		for(int i=0; i<tsp.getN(); i++){
			
			visited[i]=false;
		}
		
		Random rnd = new Random();
		
		start = (int)(rnd.nextDouble() * tsp.getN() + 0);
		
		visited[start] = true;
		aux.add(start);

		while(!(isAllVisited(visited))){
			
			Double vertexValue = INF;
			Integer destinity = -1;
			
			for(int i=0; i<tsp.getN(); i++){
				
				if(start!=i && visited[i]!=true){
					
					if(tsp.getMatrixItem(start, i) < vertexValue){
						
						vertexValue = tsp.getMatrixItem(start, i);
						
						destinity = i;
					}
				}
			}
			
			start = destinity;
			vertexValue = INF;
			visited[start] = true;
			aux.add(start);
		}
		
		return aux;
	}
	
	private boolean isAllVisited(Boolean[] visited){
		
		for(int i=0; i<tsp.getN(); i++){
		
			if(visited[i] == false){
				
				return false;
			}
		}
		
		return true;
	}
	
	private ArrayList<Integer> twoOPT(ArrayList<Integer> bT) {
		
		boolean go_start = false;
		boolean improvement = true;
		
		double tempValue = calculateTourValue(bT);
		double bestValue = calculateTourValue(bT);
		
		ArrayList<Integer> tempTour = bT;
		ArrayList<Integer> bestTour = bT;

		while(improvement){
			
			improvement = false;
			
			for(int i=0; i < tsp.getN();i++){
				
				if(go_start){
					
					break;
				}
				
				for(int k= i+1; k < tsp.getN(); k++){
					
					tempTour = twoOptSwap(tempTour, i, k);
					tempValue = calculateTourValue(tempTour);
					
					if(tempValue < bestValue){
						
						bestValue = tempValue;
						bestTour = tempTour;
						improvement = true;
						go_start = true;
						
						break;
					
					}
				}
			}
			
		}
		
		return bestTour;
	}
	
	private ArrayList<Integer> twoOptSwap(ArrayList<Integer> bestTour, int i, int k) {
		
		ArrayList<Integer> newRoute = new ArrayList<Integer> ();
			
		for(int j=0; j<i; j++){
			
			newRoute.add(bestTour.get(j));
		}
		
		for(int j=k; j>=i; j--){
			
			newRoute.add(bestTour.get(j));
		}
		
		for(int j=k+1; j<bestTour.size(); j++){
			
			newRoute.add(bestTour.get(j));
		}
		
		return newRoute;
	}

	private Double calculateTourValue(ArrayList<Integer> aux) {
		
		Double auxTourValue = 0.0;
		
		for(int i=0; i<aux.size()-1; i++){
			
			auxTourValue += tsp.getMatrixItem(aux.get(i), aux.get(i+1));
		}
		
		if(aux.size()>0){
			
			auxTourValue += tsp.getMatrixItem((aux.get(aux.size()-1)), aux.get(0));
		}
		
		return auxTourValue;
	}

	private void update(ArrayList<Integer> aux){
			
			tourValue = calculateTourValue(aux);
			
			tour = aux;
	}
}
