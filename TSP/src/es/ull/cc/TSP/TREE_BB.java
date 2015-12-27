package es.ull.cc.TSP;

import java.util.ArrayList;
import java.util.Random;

public class TREE_BB {
	
	static final double INF = Double.MAX_VALUE;
	
	private double limitTour;
	private NODE root;
	private double finalCost;
	private ArrayList<Integer> finalTour;
	private TSP_INSTANCE graph;
	
	
	public TREE_BB(ArrayList<Integer> tour, double bestTour, TSP_INSTANCE graph){
		
		this.graph = graph;
		
		Random rnd = new Random();
		int start = (int)(rnd.nextDouble() * graph.getN() + 0);
		root = new NODE(start,  graph.getN(),0.0);
		
		limitTour = bestTour;
		
		finalCost = bestTour;
		
		finalTour = new ArrayList<Integer> ();
		finalTour = tour;
		
		newBranch(root);
		
	}
	
	public void newBranch(NODE r){
		
		if(!r.isComplete()){
			
			//Creamos los nuevos hijos en el grafo
			
			for(int i=0; i<graph.getN(); i++){
				
				if(!r.isVisited(i)){
					
					NODE ch = new NODE(i, graph.getN(), r.getCost() + graph.getMatrixItem(r.getVertex(), i));
					
					r.setChildren(ch);
				}
			}
			
			//Marcamos los nodos del padre visitados en el hijo
			
			for(int i=0; i<graph.getN(); i++){
				
				if(r.isVisited(i)){
				
					for(int j=0; j<r.getNChildren(); j++){
						
						r.getChildren(j).setVisited(i);
					}
				}
			}
			
			//Añadimos el nuevo valor paso del tour
			
			ArrayList<Integer> prevTour = r.getTour();
			
			for(int i=0; i<prevTour.size(); i++){
				
				for(int j= 0; j < r.getNChildren(); j ++){
					
					r.getChildren(j).newVertexToTour(prevTour.get(i));
				}
			}
			
			//Cuando ya hemos finalizado la rama
			
			if(r.getTourSize()==(graph.getN()-2)){
				
				r.newVertexToTour(r.getVertex());
				
				if(r.getChildren(0).getCost()<=limitTour){
					
					r.newVertexToTour(r.getChildren(0).getVertex());
					
					double fC = r.getChildren(0).getCost()+graph.getMatrixItem(r.getTour().get(r.getTour().size()-1), r.getTour().get(0));
					
					if(finalCost>fC){
						
						finalCost = fC;
						
						finalTour = r.getTour();
					}
				}
			}
			
			//Creamos una nueva rama
			
			for(int i=0; i<r.getNChildren(); i++){
				
				r.getChildren(i).newVertexToTour(r.getVertex());
				
				if(!r.isVisited(i)){
					
					//Comprobamos que el valor posible es menor al limite obtenido
				
					double valuePosible = valuePosibleTour(r.getChildren(i)) + r.getCost();
				
					if((valuePosible<limitTour)){
						
						newBranch(r.getChildren(i));
						
					}
				}
			}			
		}
	}
	
	public double valuePosibleTour(NODE nodeActual){
		
		double min;
		
		double minAdd = 0.0;
		
		int finalTour = -1;
		
		for(int i=0; i<graph.getN(); i++){
			
			min = INF;
			
			if(!nodeActual.isVisited(i)){

				for(int j = 0; j<graph.getN(); j++){
					
					if((!nodeActual.isVisited(j) && (i!=j))){
						
						finalTour = i;
					
						if(min > graph.getMatrixItem(j, i)){
							
							min = graph.getMatrixItem(j, i);
						}
					}
				}
			}
			
			if(min!=INF){
				
				minAdd += min;
			}
		}
		
		minAdd += graph.getMatrixItem(nodeActual.getTour().get(0), finalTour);
		
		return minAdd;
	}
	
	public ArrayList<Integer> getFinalTour(){
		
		return finalTour;
	}
}
