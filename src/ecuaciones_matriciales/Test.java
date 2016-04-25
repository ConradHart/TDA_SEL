package ecuaciones_matriciales;

public class Test {
	public static void main(String[] args) {
		MatrizMath m1= new MatrizMath(2);
		VectorMath v1= new VectorMath(2);
     	m1.cargarMatriz();
		m1.imprimirMatriz();
		v1.cargarVector();
		v1.imprimirVector();
		System.out.println("\nInversa: " + MatrizMath.inversa(m1));
      MatrizMath.sistemaEcuacionesLineales(MatrizMath.inversa(m1), v1).imprimirVector();
      System.out.println(m1.productoEntreMatrices(MatrizMath.inversa(m1)));
      m1.calculoDeError(m1);
	}

}
