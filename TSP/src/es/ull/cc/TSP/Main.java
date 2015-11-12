package es.ull.cc.TSP;
/*****************************************************************************************************************************************************************
							"The Traveling Salesman Problem"
*****************************************************************************************************************************************************************/

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);

		TSP_INPUT problem = new TSP_INPUT();
		
		int op = 3;
		
		do{
			
			System.out.println("\nMenú:");
			System.out.println("\n\t1. Leer fichero.");
			System.out.println("\n\t2. Imprimir datos problema.");			
			System.out.println("\n\t3. Salir.\n");
			
			System.out.println("\nIntroduce opción:\n");
			
			op = sc.nextInt();
			
			switch(op){
				
				case 1:{
					
					System.out.println("Introduce nombre archivo");
					
					String file = sc.next();
					
					problem = new TSP_INPUT(file);
	
					break;
				}
				
				case 2:{

					problem.printAll();
					
					break;
	
				}
				
				case 3:{
					
					System.out.println ("Apagando..");
					
					break;
	
				}
				
                default:{
                	
                	System.out.println("Error, en la elección de la opción.");
                	
                	break;
                }
			}
			
		}while(op!=3);
	}
}
