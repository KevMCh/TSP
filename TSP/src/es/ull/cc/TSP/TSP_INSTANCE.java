package es.ull.cc.TSP;

public class TSP_INSTANCE {
	
	//Clase que contendrá los datos del Grafo
	
	protected int n;					// Número de vértices 
	protected double graph[];			// Matriz de costes del grafo. (Tam: nxn)
	
/*****************************************************************************************************************************************************************/

	public TSP_INSTANCE(){}
	
	public int getN(){
		
		return n;
	}
	
	public void  printG(){

		System.out.println("Matriz de costes: ");

		for(int i=0;i<getN();i++){

			for(int j=0;j<getN();j++){

				System.out.print(" " + getMatrixItem(i,j) + "\t");
			}
			
			System.out.println();
		}
	}
	
	public double getMatrixItem(int i,int j){

		if(i<0 || j<0){

			return 0;

		}else{

			return graph[position(i,j)];

		}
	}
	
	public void setMatrixItem(int i,int j, double it){
		
		graph[position(i,j)]=it;
	}
	
/*****************************************************************************************************************************************************************/

	private int position(int i,int j){

		if ((i<0)||(i>getN())||(j<0)||(j>getN())){

			System.out.println("Error accediendo a matriz");

			return 0;

		}else{

			return i*getN()+j;

		}
	}
	
/*****************************************************************************************************************************************************************/

	protected void buildMatrix(int n){
		
		this.n = n; 
		
		graph = new double[n*n];
	}
}
