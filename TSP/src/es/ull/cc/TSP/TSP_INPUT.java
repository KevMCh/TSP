package es.ull.cc.TSP;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class TSP_INPUT extends TSP_INSTANCE{

	String name;
	String source;
	String description;
	double doublePrecision;
	int ignoredDigits;
	
/*****************************************************************************************************************************************************************/

	public TSP_INPUT(){}
	
	public TSP_INPUT(String problem){
		
		read(problem);
	}
	
	public void printAll(){
		
		System.out.println("\nDetalles del Problema\n");
		
		System.out.println("Nombre del Problema: " + name);
		System.out.println("Source: " + source);
		System.out.println("Descripción: " + description);
		System.out.println("Precisión doble: " + doublePrecision);
		System.out.println("Digitos ignorados: " + ignoredDigits);
		printG();
	}
	
/*****************************************************************************************************************************************************************/

	//Lectura del fichero de entrada en XML
	private void read(String nameProblem){
		
		Document dom;
		DocumentBuilderFactory dbf;
		DocumentBuilder db;
		
		dbf = DocumentBuilderFactory.newInstance();
		
		try
		{
		  db = dbf.newDocumentBuilder();
		  
		  //Guardamos la estructura XML
		  
		  String curDir = System.getProperty("user.dir");
		  String ruta = curDir + "/files/"+ nameProblem + ".xml";
		  
		  dom = db.parse(ruta);
		  
		  name = dom.getElementsByTagName("name").item(0).getTextContent();
		  
		  source = dom.getElementsByTagName("source").item(0).getTextContent();
		  
		  description = dom.getElementsByTagName("description").item(0).getTextContent();
		  
		  doublePrecision = Double.valueOf(dom.getElementsByTagName("doublePrecision").item(0).getTextContent()).doubleValue();
		  
		  ignoredDigits = Integer.parseInt(dom.getElementsByTagName("ignoredDigits").item(0).getTextContent());
		
		  // Sacamos el número de nodos del grafo del problema
		  
		  Element rootElement = dom.getDocumentElement();
		  
		  NodeList vertexList = rootElement.getElementsByTagName("vertex");
		  
		  n = vertexList.getLength();
		  
		  buildMatrix(n);
		  
		  // Bucle para leer los costes
		  
		  if(vertexList != null && vertexList.getLength()>0){
			  
			for(int i=0;i<vertexList.getLength();i++){

				Element elem1 = (Element) vertexList.item(i);
				
				NodeList edgeList = elem1.getElementsByTagName("edge");				
				
				if(edgeList != null && edgeList.getLength()>0){
				
					for(int j=0;j<edgeList.getLength();j++){
						
						Element elem2 = (Element) edgeList.item(j);
						
						double aux = Double.valueOf(elem2.getAttribute("cost")).doubleValue();
							
						setMatrixItem(i, Integer.parseInt(elem2.getFirstChild().getNodeValue()), aux);
					}
				}
			}
		  }
		}
		catch(Exception ex) {
			
			System.out.println("Error en la carga del fichero. Error: " + ex);
			
		}
	}
}
