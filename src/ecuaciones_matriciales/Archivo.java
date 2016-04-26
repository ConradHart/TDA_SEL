package ecuaciones_matriciales;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

public class Archivo {

	private static String Resource1 = "Resources//Entrada//";
	private static String Resource2 = "Resources//Entrada//";
	private static String Resource3 = "Resources//Entrada//";
	private static String Resource4 = "Resources//Entrada//04_caso2x2cCasiLDsimple.in";
	private static String Resource5 = "Resources//Entrada//05_caso3x3.in";

	public void leer() {
		
		File archivo = null;
		FileReader fr = null;
		BufferedReader br = null;
		

		try {

			archivo = new File(Resource5);
			fr = new FileReader(archivo);
			br = new BufferedReader(fr);
			String linea = "";
			Boolean PrimeraLinea = true;
			long l1 = -1;
			long l2 = 0;
			long l3 = 0;
			Long lNumeroLineas = new Long(l1);
			Long lNumeroLineaFinMatriz = new Long(l2);
			Long lNumeroLineaFinVector = new Long(l3);
			
			System.out.println("Leyendo matriz del archivo...");
			MatrizMath Matriz = null;
			VectorMath Vector = null;
			//VectorMath Vector = new VectorMath(3); //vector columna de términos independientes. 
			String iFila = "";
			String jColumna = "";
			String eElemento = "";
			
			while ((linea = br.readLine()) != null){ //Primera lectura obtiene la dimension de la matriz y vector			
				lNumeroLineas++; //Contador de Lineas la primera lectura
				if(PrimeraLinea == true){
						System.out.println("La dimension de la matriz cuadrada es " + linea + "x" + linea);
						System.out.println("La dimension del vector es " + linea);
						lNumeroLineaFinMatriz = (long) (Integer.parseInt(linea)*(Integer.parseInt(linea)));//Numero de linea de fin elementos de la matriz
						lNumeroLineaFinVector = lNumeroLineaFinMatriz + Integer.parseInt(linea);
						PrimeraLinea = false;
						Matriz = new MatrizMath(Integer.parseInt(linea)); //Dimension de la matriz
						Vector = new VectorMath(Integer.parseInt(linea)); //Dimension del Vector
						System.out.println("Matriz: ");
						
					}else{
						if(lNumeroLineas <= lNumeroLineaFinMatriz){ //Elementos de la matriz nxn

							linea = linea.replace(" ","");
							iFila = linea.substring(0, 1);
							jColumna = linea.substring(1, 2);
							eElemento = linea.substring(2, 3);
							
							System.out.println("Elemento i["+ iFila + "] j["+ jColumna +"]: " + eElemento);
							
							eElemento = "";
						}else{ //Elementos del vector
							if(lNumeroLineas <= lNumeroLineaFinVector){ //Elementos de la matriz nxn
								linea = linea.replace(" ","");
								eElemento = eElemento + linea;
								
							}
						}
					}
				
			}
			
			System.out.println("Vector: ["+ eElemento+"]");
			System.out.println("Fin de lectura...");
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != fr) {
					fr.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}

	}

	public void escribir() {
		FileWriter fichero = null;
		PrintWriter pw = null;
		try {
			fichero = new FileWriter("F:/Workspace_Eclipse/Palindromo/bin/palindromo/Resources/palin.out");
			pw = new PrintWriter(fichero);

			for (int i = 0; i < 10; i++)
				pw.println("Linea " + i);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {

				if (null != fichero)
					fichero.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
}
