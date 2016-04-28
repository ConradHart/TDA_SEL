package ecuaciones_matriciales;
import java.util.GregorianCalendar;
import java.util.Calendar;

public class Test {
	public static void main(String[] args) {

	  Calendar tIni = new GregorianCalendar();
	  
		  Archivo archivo1 = new Archivo();
		  archivo1.leer(); //Lee y carga la matriz y el vector en memoria
		  
		  archivo1.setIncognitasResueltasAGrabar(archivo1.getMatriz().resolverSistemaEcuacionesLineales(archivo1.getMatriz().matrizInversaPorGaussJordan(), archivo1.getVector()));
		  archivo1.setErrorCalculoSELaGrabar(archivo1.getMatriz().calculoDeError(archivo1.getMatriz()));
		  
		  archivo1.escribir(); //Graba el archivo de solucion
		  
		  Calendar tFin = new GregorianCalendar();
	  
	  long diff = tFin.getTimeInMillis() - tIni.getTimeInMillis();
	  System.out.println("El rendimiento de algoritmos fue de: " + diff + " milisegundos");
			  
	  
	}

}
