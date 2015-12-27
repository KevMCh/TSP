package es.ull.cc.TSP;

import java.util.ArrayList;

public class NODE {
	
	private int vertex;
	private boolean visited [];
	private double cost;
	private ArrayList<NODE> children;
	private ArrayList<Integer> tour;
	
	public NODE(int vertex, int nVertex, double cost){
		
		this.vertex = vertex;
		
		visited = new boolean[nVertex];
		visited[vertex] = true;
		
		this.children = new ArrayList<NODE> ();
		this.tour = new ArrayList<Integer> ();
		 
		this.cost = cost;

	}
	
	public int getVertex(){
		
		return vertex;
	}
	
	public void setVisited(int i) {
		
		visited[i] = true;		
	}
	
	public boolean isVisited(int i){
		
		return visited[i];
	}
	
	public boolean isComplete(){
		
		for(int i=0; i<visited.length; i++){
			
			if(!visited[i]){
				
				return false;
			}
		}
		
		return true;
	}
	
	public double getCost() {
		
		return cost;
	}
	
	public NODE getChildren(int i){
		
		return children.get(i);
	}

	public void setChildren(NODE children){
		
		this.children.add(children);
	}

	public int getNChildren() {
		
		return children.size();
	}
	
	public void printNode() {
		
		System.out.println("Nodo: " + vertex);
		
		System.out.println("Hijos: ");
		
		for(int i=0; i<children.size(); i++){
			
			System.out.print(children.get(i).getVertex() + " " + children.get(i).getCost() + "\n");
		}
		
		System.out.println("Visitados");
		
		for(int i=0; i<visited.length; i++){
			
			if(visited[i]){
				
				System.out.print("T");
				
			}else{
				
				System.out.print("F");
			}
		}
		
		System.out.println("\nTour" + tour);
		
		System.out.println("Coste: " + cost);
		
		System.out.println();
	}
	
	public void newVertexToTour(int i){
		
		tour.add(i);
	}

	public ArrayList<Integer> getTour() {
		
		return tour;
	}

	public int getTourSize() {
		
		return tour.size();
	}

}
