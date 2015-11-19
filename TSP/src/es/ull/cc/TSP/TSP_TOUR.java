package es.ull.cc.TSP;

import java.util.Random;
import java.util.ArrayList;

public class TSP_TOUR {
	
	static final double INF = 1e100;
	
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
						
						destinity = i;
					}
				}
			}
			
			start = destinity;
			
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

	private ArrayList<Integer> twoOPT(ArrayList<Integer> bT){
		
		Double newTourValue = INF;
		Double bestDistance = calculateTourValue(tour);
		
		ArrayList<Integer> newTour = new ArrayList<Integer> ();
		ArrayList<Integer> bestTour = bT;
		
		while(true){
			
			boolean found = false;

			for (int i=0; i<tsp.getN()-1; i++){
				
				if(found == true){
					
					break;
				}
				
				for (int k=i+1; k<tsp.getN(); k++){
					
					newTour = twoOptSwap(bestTour, i, k);
					newTourValue = calculateTourValue(newTour);
					
					if (newTourValue < bestDistance) {
		            	
		            	bestDistance = newTourValue;
		                bestTour = newTour;
		                
		                found = true;
		                
		                break;
		            }
		        }
		    }
			
			if (newTourValue >= bestDistance){
            	
            	break;
            }
		}
		
		return bestTour;
	}
	
	private ArrayList<Integer> twoOptSwap(ArrayList<Integer> bestTour, int i, int k) {
		
		ArrayList<Integer> newRoute = new ArrayList<Integer> ();
			
		for(int j=0; j<=i-1; j++){
			
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
		
		for(int i=1; i<aux.size(); i++){
			
			auxTourValue += tsp.getMatrixItem(aux.get(i-1), aux.get(i));
		}
		
		return auxTourValue;
	}

	private void update(ArrayList<Integer> aux){
			
			tourValue = calculateTourValue(aux);
			
			tour = aux;
	}
}
