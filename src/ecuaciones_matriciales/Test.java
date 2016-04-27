package ecuaciones_matriciales;

public class Test {
	public static void main(String[] args) {

	  Archivo Matriz1 = new Archivo();
	  Matriz1.leer(); //Lee y carga la matriz y el vector en memoria
	  
//	  MatrizMath m1 = new MatrizMath(2);
//	  VectorMath v1 = new VectorMath(2);
//    m1.cargarMatriz();
//	  m1.imprimirMatriz();
//	  v1.cargarVector();
//	  v1.imprimirVector();
	  
	  
	  
//	  System.out.println("\nInversa: " + MatrizMath.inversa(m1)); <<<<-----PROBLEMA GASTON
	  //m1 no existe , la matriz fue leida por la clase archivo y esta cargada adentro se llama Matriz,
	  //ahora para calcular la inversa se hace desde la clase MatrizMath como no la conoce....no puede calcular
	  //Posible solucion copiar la matriz llamada Matriz desde la clase Archivo a la clase MatrizMath 
	  
//      MatrizMath.sistemaEcuacionesLineales(MatrizMath.inversa(m1), v1).imprimirVector();
//      System.out.println(m1.productoEntreMatrices(MatrizMath.inversa(m1)));
     // m1.calculoDeError(m1);
	}

}
