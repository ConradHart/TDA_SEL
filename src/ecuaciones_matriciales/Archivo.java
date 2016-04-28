package ecuaciones_matriciales;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

public class Archivo {


	private static String Resource1 = "Resources//Entrada//04_caso2x2cCasiLDsimple.in";
	private static String Resource2 = "Resources//Entrada//05_caso3x3.in";
	private static String ResourceSolucion = "Resources//Salida//solucion.out";
	
	private MatrizMath matriz;
	private VectorMath vector;
	private VectorMath incognitasResueltasAGrabar;
	private Double errorCalculoSELaGrabar;
	private Integer dimensionAGarabar;
	
	/////////////////////////////////////////////////////////////////////
	public MatrizMath getMatriz() {
		return matriz;
	}
	public void setMatriz(MatrizMath matriz) {
		this.matriz = matriz;
	}
	public VectorMath getVector() {
		return vector;
	}
	public void setVector(VectorMath vector) {
		this.vector = vector;
	}
	public VectorMath getIncognitasResueltasAGrabar() {
		return incognitasResueltasAGrabar;
	}
	public void setIncognitasResueltasAGrabar(VectorMath incognitasResueltasAGrabar) {
		this.incognitasResueltasAGrabar = incognitasResueltasAGrabar;
	}
	public Double getErrorCalculoSELaGrabar() {
		return errorCalculoSELaGrabar;
	}
	public void setErrorCalculoSELaGrabar(Double errorCalculoSELaGrabar) {
		this.errorCalculoSELaGrabar = errorCalculoSELaGrabar;
	}
	public Integer getDimensionAGarabar() {
		return dimensionAGarabar;
	}
	public void setDimensionAGarabar(Integer dimensionAGarabar) {
		this.dimensionAGarabar = dimensionAGarabar;
	}
	///////////////////////////////////////////////////////////////////////
	
	public void leer() {
		
		File archivo = null;
		FileReader fr = null;
		BufferedReader br = null;
		

		try {

			archivo = new File(Resource2);
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
			Integer posVector = 0; //Posicion del Vector
			System.out.println("Leyendo matriz del archivo...");
			MatrizMath Matriz = null;
			VectorMath Vector = null;

			int iFila = 0;
			int jColumna = 0;
			double eElemento = 0;
			
			
			while ((linea = br.readLine()) != null){ //Primera lectura obtiene la dimension de la matriz y vector
				lNumeroLineas++; //Contador de Lineas la primera lectura
				if(PrimeraLinea == true){
						this.setDimensionAGarabar(Integer.parseInt(linea));
						System.out.println("La dimension de la matriz cuadrada es " + linea + "x" + linea);
						System.out.println("La dimension del vector es " + linea);
						lNumeroLineaFinMatriz = (long) (Integer.parseInt(linea)*(Integer.parseInt(linea)));//Numero de linea de fin elementos de la matriz
						lNumeroLineaFinVector = lNumeroLineaFinMatriz + Integer.parseInt(linea);
						PrimeraLinea = false;
						
						this.setMatriz(new MatrizMath(Integer.parseInt(linea))); //Dimensiono la matriz
						this.setVector(new VectorMath(Integer.parseInt(linea))); //Dimensiono el vector
						this.setIncognitasResueltasAGrabar(new VectorMath(Integer.parseInt(linea))); //Dimensiono el Vector de Incognitas Resueltas 
						
//						Matriz = new MatrizMath(Integer.parseInt(linea)); //Dimensiono la matriz
//						Vector = new VectorMath(Integer.parseInt(linea)); //Dimension el vector
						System.out.println("Matriz: ");
						
					}else{
						
						if(lNumeroLineas <= lNumeroLineaFinMatriz){ //Elementos de la matriz nxn	
//							linea = linea.replace(" ","");
//							iFila = linea.substring(0, 1);
//							jColumna = linea.substring(1, 2);
//							eElemento = linea.substring(2, 3);
							
							String[] lineaSpliteada = linea.split(" ");
							iFila = Integer.parseInt(lineaSpliteada[0]);
							jColumna = Integer.parseInt(lineaSpliteada[1]);
							eElemento = Double.parseDouble(lineaSpliteada[2]);
							
							//Cargo matriz leida
//							Matriz.cargarMatrizArchivo(Integer.parseInt(iFila),Integer.parseInt(jColumna), Double.parseDouble(eElemento));						
							this.getMatriz().cargarMatrizArchivo(iFila,jColumna,eElemento);
							
							System.out.println("Elemento i["+ iFila + "] j["+ jColumna +"]: " + eElemento);
							
//							eElemento = "";
						}else{ //Elementos del vector
							if(lNumeroLineas <= lNumeroLineaFinVector){ //Elementos del vector
//								linea = linea.replace(" ","");
//								eElemento = eElemento + linea;
								
//								linea = linea.replace(" ","");
//								eElemento = linea;
								eElemento = Double.parseDouble(linea);
								
								//Cargo el vector leido
//								Vector.cargarVectorArchivo(posVector, Double.parseDouble(eElemento));
								this.getVector().cargarVectorArchivo(posVector, eElemento);
								
								posVector++;
								
							}
						}
					}
				
			}
			
			System.out.println("Vector: ["+ eElemento+"]");
			this.getMatriz().imprimirMatriz();
			this.getVector().imprimirVector();		
			
			System.out.println("\nFin de lectura...");
			
			
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

	}//fin leer
	

	public void escribir() {
		FileWriter fichero = null;
		PrintWriter pw = null;
		try {
			fichero = new FileWriter(ResourceSolucion);
			pw = new PrintWriter(fichero);

			pw.println(this.getDimensionAGarabar());
			pw.println(this.getIncognitasResueltasAGrabar());
            pw.println(this.getErrorCalculoSELaGrabar());
            
            System.out.println("\nEl archivo solucion fue grabado con exito.");
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
	}//fin escribir
	

}
