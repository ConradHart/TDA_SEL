package ecuaciones_matriciales;


import java.util.Scanner;

public class MatrizMath implements Cloneable {

	private Integer dimension; //Dimension de la matriz
	private Double[][] matriz; //Matriz
	private Integer fila;
	private Integer columna;
	//static Scanner teclado = new Scanner(System.in);

	//Constructor Matriz Cuadrada
	public MatrizMath(Integer dimension) {
		this.setDimension(dimension);
		this.setFila(dimension);
		this.setColumna(dimension);
		this.setMatriz(new Double[dimension][dimension]);
		for(int i=0; i<this.getDimension(); i++){
			for(int j=0; j<this.getDimension(); j++){
				matriz[i][j]=0.0;
			}//Fin inicializacion de Matriz Cuadrada
		}
	}

	//Constructor a partir de una Matriz Cuadrada
	public MatrizMath(Double[][] otraMatriz) {
		this.matriz=otraMatriz;//Copia a matriz, la Matriz otraMatriz
		this.setDimension(otraMatriz.length);//Cargo dimensi�n
		this.setFila(dimension);
		this.setColumna(dimension);
	}

	//Constructor de Matriz No Cuadrada
	public MatrizMath(Integer fila, Integer columna) {
		this.setFila(fila);
		this.setColumna(columna);
		this.setMatriz(new Double[fila][columna]);
		for(int i=0; i<this.getFila(); i++){
			for(int j=0; j<this.getColumna(); j++){
				matriz[i][j]=0.0;
			}//Fin inicializacion de Matriz 
		}
	}

	/////////////////////////////////////////
	//Getters y Setters
	public Integer getDimension() {
		return dimension;
	}

	public void setDimension(Integer dimension) {
		this.dimension = dimension;
	}

	public Double[][] getMatriz() {
		return matriz;
	}

	public void setMatriz(Double[][] matriz) {
		this.matriz = matriz;
	}

	public Integer getFila() {
		return fila;
	}

	public void setFila(Integer fila) {
		this.fila = fila;
	}

	public Integer getColumna() {
		return columna;
	}

	public void setColumna(Integer columna) {
		this.columna = columna;
	}
	////////////////////////////////////////////////////////

	//Cargar Matriz()
	public void cargarMatriz(){
		System.out.println("Matriz de dimension (" + this.getFila() + "x" + this.getColumna() + ")");
		System.out.println((this.getFila()==this.getColumna())?"Matriz Cuadrada":"Matriz NO Cuadrada");
		System.out.println(" Ingrese elementos a cargar: ");
		for(int i=0; i<matriz.length;i++){
			for(int j=0; j<matriz[i].length; j++){
				System.out.print("fila [" + i + "] columna [" + j + "] :");    			
				matriz[i][j]=teclado.nextDouble();
			}
		}

	}//fin cargar

	public MatrizMath matrizIdentidad(){
		MatrizMath mIdent = new MatrizMath(this.dimension);
		for(int i=0; i<this.getDimension(); i++){
			for(int j=0; j<this.getDimension(); j++){
				mIdent.matriz[i][i]=1.0;
			}//Fin inicializaci�n de Matriz Cuadrada
	       }
		return mIdent;
	}
	
	public void imprimirMatriz(){
		System.out.println("\nMatriz cargada:");
		for(int i=0; i<this.getFila();i++){
			for(int j=0; j<this.getColumna(); j++){
				System.out.print(matriz[i][j] + " ");
			}
			System.out.println();
		}
	}//fin imprimir

	//Clonaci�n de la Matriz
	public Object clone(){
		MatrizMath obj=null;
		try{
			obj=(MatrizMath)super.clone();
		}catch(CloneNotSupportedException ex){
			System.out.println(" No se puede duplicar");
		}
		//aqu� est� la clave  para clonar la matriz bidimensional
		obj.matriz=(Double[][])obj.matriz.clone();
		for(int i=0; i<obj.matriz.length; i++){
			obj.matriz[i]=(Double[])obj.matriz[i].clone();
		}
		return obj;
	}

	//M�TODOS
	//C�lculo de la traza: sumatoria de los elementos de la "Diagonal"
	Double traza(){
		if(this.getFila()==this.getColumna()){
			double tr=0.0;
			for(int i=0; i<dimension; i++){
				tr+=matriz[i][i];
			}
			return tr;
		}else{System.out.println("No es una matriz cuadrada. No puede calcularse su Traza");
		return null;}
	}

	//suma de dos matrices
	//static: lo llamo desde Matriz. M�todo de clase.
	static MatrizMath suma(MatrizMath a, MatrizMath b){
		if((a.getFila()==b.getFila())&&(a.getColumna()==b.getColumna())){
			MatrizMath matrizSuma=new MatrizMath(a.dimension);//Llama al constructor que pide "dimension".
			for(int i=0; i<a.dimension; i++){
				for(int j=0; j<a.dimension; j++){
					matrizSuma.matriz[i][j]=a.matriz[i][j]+b.matriz[i][j];
				}
			}
			return matrizSuma;
		}else{System.out.println("Las matrices no tienen el mismo \"orden\". No puede calcularse su Suma");
		return null;}
	}

	//resta de dos matrices
	//static: lo llamo desde Matriz. M�todo de clase.
//	static MatrizMath resta(MatrizMath a, MatrizMath b){
//		if((a.getFila()==b.getFila())&&(a.getColumna()==b.getColumna())){
//			MatrizMath matrizResta=new MatrizMath(a.dimension);//Llama al constructor que pide "dimension".
//			for(int i=0; i<a.dimension; i++){
//				for(int j=0; j<a.dimension; j++){
//					matrizResta.matriz[i][j]=a.matriz[i][j]-b.matriz[i][j];
//				}
//			}
//			return matrizResta;
//		}else{System.out.println("Las matrices no tienen el mismo \"orden\". No puede calcularse su Resta");
//		return null;}
//	}

	public MatrizMath resta(MatrizMath b){
		if((this.getFila()==b.getFila())&&(this.getColumna()==b.getColumna())){
			MatrizMath matrizResta=new MatrizMath(this.dimension);//Llama al constructor que pide "dimension".
			for(int i=0; i<this.dimension; i++){
				for(int j=0; j<this.dimension; j++){
					matrizResta.matriz[i][j]=this.matriz[i][j]-b.matriz[i][j];
				}
			}
			return matrizResta;
		}else{System.out.println("Las matrices no tienen el mismo \"orden\". No puede calcularse su Resta");
		return null;}
	}

	
	
	//producto de dos matrices
	//static: lo llamo desde Matriz. M�todo de clase.
//	static MatrizMath productoEntreMatrices(MatrizMath a, MatrizMath b){
//		if(a.getColumna()==b.getFila()){
//			MatrizMath matrizProducto=new MatrizMath(a.dimension);
//			for(int i=0; i<a.dimension; i++){
//				for(int j=0; j<a.dimension; j++){
//					for(int k=0; k<a.dimension; k++){
//						matrizProducto.matriz[i][j]+=a.matriz[i][k]*b.matriz[k][j];
//					}
//				}
//			}
//			return matrizProducto;
//		}else{System.out.println("No es una matriz cuadrada. No puede calcularse su Producto");
//		return null;}
//	}

	public MatrizMath productoEntreMatrices(MatrizMath b){
		if(this.getColumna()==b.getFila()){
			MatrizMath matrizProducto=new MatrizMath(this.dimension);
			for(int i=0; i<this.dimension; i++){
				for(int j=0; j<this.dimension; j++){
					for(int k=0; k<this.dimension; k++){
						matrizProducto.matriz[i][j]+=this.matriz[i][k]*b.matriz[k][j];
					}
				}
			}
			return matrizProducto;
		}else{System.out.println("No es una matriz cuadrada. No puede calcularse su Producto");
		return null;}
	}
	
	
	
	
	static VectorMath sistemaEcuacionesLineales(MatrizMath matrizInversa, VectorMath terminosIndep){
		VectorMath vectorIncognitas= new VectorMath(terminosIndep.getDimensionVector());
		for(int i=0; i<matrizInversa.getDimension(); i++){
			for(int j=0; j<matrizInversa.getDimension(); j++){
				vectorIncognitas.getVector()[i]+=matrizInversa.matriz[i][j]*terminosIndep.getVector()[j];
			}
		}

		return vectorIncognitas;
	}


	//producto de una matriz por una escalar
	//static: lo llamo desde Matriz. M�todo de clase.
	static MatrizMath productoPorEscalar(MatrizMath a, double alfa){
		MatrizMath matrizEscalar=new MatrizMath(a.dimension);
		for(int i=0; i<a.dimension; i++){
			for(int j=0; j<a.dimension; j++){
				matrizEscalar.matriz[i][j]=a.matriz[i][j]*alfa;
			}
		}
		return matrizEscalar;
	}

	//producto de un vector fila por una matriz da un vector fila (1xn) (nxn)= (1xn)
	public VectorMath productoVecFilPorMatriz(VectorMath vectorFila, MatrizMath a){
		VectorMath vectorAuxFil=new VectorMath(vectorFila.getDimensionVector());
		int n=vectorFila.getDimensionVector();  //dimensi�n
		for(int i=0; i<n; i++){
			for(int j=0; j<n; j++){
				vectorAuxFil.getVector()[i]+=vectorFila.getVector()[j]*a.matriz[j][i];
			}
		}
		return vectorAuxFil;
	}
	//producto de una matriz por un vector columna (nxn) (nx1)= (nx1)
	static VectorMath productoVecColPorMatriz(MatrizMath a, VectorMath vectorColumna){
		int n=vectorColumna.getDimensionVector();  //dimensi�n
		VectorMath vectorAuxCol= new VectorMath(n);
		for(int i=0; i<n; i++){
			for(int j=0; j<n; j++){
				vectorAuxCol.getVector()[i]+=a.matriz[i][j]*vectorColumna.getVector()[j];
			}
		}
		return vectorAuxCol;
	}

	//determinante de una matriz: funci�n que a cada matriz le asigna un n� real
	Double determinante(){
		if(this.getFila()==this.getColumna()){
			MatrizMath matrizAux=(MatrizMath)clone();//Casteo a Matriz de obj que devuelve el m�todo clone.
			double deter=1.0;
			for(int k=0; k<dimension-1; k++){
				for(int i=k+1; i<dimension; i++){
					for(int j=k+1; j<dimension; j++){
						matrizAux.matriz[i][j]-=matrizAux.matriz[i][k]*matrizAux.matriz[k][j]/matrizAux.matriz[k][k];
					}
				}
			}

			for(int i=0; i<dimension; i++){
				deter*=matrizAux.matriz[i][i];
			}
			return deter;
		}else{System.out.println("No es una matriz cuadrada. No puede calcularse su determinante");
		return null;}
	}


	//matriz inversa
	static MatrizMath inversa(MatrizMath matrizAInvertir){
		if(matrizAInvertir.getFila()==matrizAInvertir.getColumna()){
			if(matrizAInvertir.determinante()!=0){
				int dimAux=matrizAInvertir.dimension;  //dimensi�n de la matriz
				MatrizMath matrizAux=(MatrizMath)matrizAInvertir.clone();
				MatrizMath matrizIdentidad=new MatrizMath(dimAux);   //matriz de los t�rminos independientes
				MatrizMath matrizInversa=new MatrizMath(dimAux);   //matriz de las inc�gnitas

				//generar matriz Identridad
				for(int i=0; i<dimAux; i++){
					matrizIdentidad.matriz[i][i]=1.0;
				}
				//transformaci�n de la matriz y de los t�rminos independientes
				for(int k=0; k<dimAux-1; k++){
					for(int i=k+1; i<dimAux; i++){
						//t�rminos independientes
						for(int j=0; j<dimAux; j++){
							matrizIdentidad.matriz[i][j]-=matrizAux.matriz[i][k]*matrizIdentidad.matriz[k][j]/matrizAux.matriz[k][k];
						}
						//elementos de la matriz
						for(int j=k+1; j<dimAux; j++){
							matrizAux.matriz[i][j]-=matrizAux.matriz[i][k]*matrizAux.matriz[k][j]/matrizAux.matriz[k][k];
						}
					}
				}
				//c�lculo de las inc�gnitas, elementos de la matriz inversa
				for(int j=0; j<dimAux; j++){
					matrizInversa.matriz[dimAux-1][j]=matrizIdentidad.matriz[dimAux-1][j]/matrizAux.matriz[dimAux-1][dimAux-1];
					for(int i=dimAux-2; i>=0; i--){
						matrizInversa.matriz[i][j]=matrizIdentidad.matriz[i][j]/matrizAux.matriz[i][i];
						for(int k=dimAux-1; k>i; k--){
							matrizInversa.matriz[i][j]-=matrizAux.matriz[i][k]*matrizInversa.matriz[k][j]/matrizAux.matriz[i][i];
						}
					}
				}
				return matrizInversa;
			}else{System.out.println("Su determinante es 0. No puede calcularse su Inversa");
			return null;}
		}else{System.out.println("No es una matriz cuadrada. No puede calcularse su Inversa");
		return null;}
	}


	//matriz traspuesta
	static MatrizMath traspuesta(MatrizMath matrizATrasponer){
		int n=matrizATrasponer.dimension;    //dimensi�n
		MatrizMath matrizTraspuesta=new MatrizMath(matrizATrasponer.dimension);
		for(int i=0; i<n; i++){
			for(int j=0; j<n; j++){
				matrizTraspuesta.matriz[i][j]=matrizATrasponer.matriz[j][i];
			}
		}
		return matrizTraspuesta;
	}
	
	/**
	 * Devuelve el valor ubicado en las posiciones indicadas por i y j
	 * 
	 * @param i
	 *            numero de fila
	 * @param j
	 *            numero de columna
	 * @return valor almacenado en (i,j)
	 */
	public double get(int i, int j) {
		return matriz[i][j];
	}
	

	/**
	 * Realiza la norma uno de la matriz (Suma absoluta de columnas)
	 * 
	 * @return (Suma absoluta de columnas)
	 */
	public double normaUno() {
		double maximo = 0;
		double valor;
		for (int j = 0; j < this.getFila(); j++) {
			valor = 0;
			for (int i = 0; i < this.getColumna(); i++) {
				valor += Math.abs(get(i,j));
				if (valor > maximo)
					maximo = valor;
			}
		}
		return maximo;
	}
	
	/**
	 * Realiza la norma dos de la matriz, tambien llamada norma de Frobenius
	 * 
	 * @return
	 */
	public double normaDos() {
		double resultado = 0;
		for (int i = 0; i < this.getFila(); i++)
			for (int j = 0; j < this.getColumna(); j++)
				resultado += get(i, j) * get(i, j);
		return Math.sqrt(resultado);
	}
	
	/**
	 * Realiza la norma infinito de la matriz (Suma absoluta de filas)
	 * 
	 * @return (Suma absoluta de filas)
	 */
	public double normaInfinito() {
		double maximo = 0;
		double valor;
		for (int i = 0; i < this.getFila(); i++) {
			valor = 0;
			for (int j = 0; j < this.getColumna(); j++) {
				valor += Math.abs(get(i, j));
				if (valor > maximo)
					maximo = valor;
			}
		}
		return maximo;
	}
     
	// Calculo de error
	// ---------------------------------------------------------------------
	public void calculoDeError(MatrizMath matriz){
	try {
				System.out.println("Calculo de error");
				// System.out.println("m1= " + matriz1.producto(matriz4.inversa()) +
				// " - Esperado 10.77032961");
//				MatrizMath identidad= new MatrizMath(matriz.getDimension());
//				identidad.cargarMatriz();
				
				
//				System.out.println("m2= " + matriz2.getIdentidad().resta(matriz2.producto(matriz2.inversa())).normaDos()
//						+ " - Esperado < 1E-12");
								
				//System.out.println("m2= " + (this.productoEntreMatrices(MatrizMath.inversa(matriz)).resta(this.matrizIdentidad())).normaDos()
				//		+ " - Esperado < 1E-12");
				
//				System.out.println("m3= " + matriz3.getIdentidad().resta(matriz3.producto(matriz3.inversa())).normaDos()
//						+ " - Esperado < 1E-12");
//				System.out.println("m4= " + matriz4.getIdentidad().resta(matriz4.producto(matriz4.inversa())).normaDos()
//						+ " - Esperado < 1E-12");
				System.out.println(matriz.productoEntreMatrices(MatrizMath.inversa(matriz)));
				
				
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
	
	public String toString(){
		String texto="\n";
		for(int i=0; i<dimension; i++){
			for(int j=0; j<dimension; j++){
				texto+="\t "+(double)Math.round(1000*matriz[i][j])/1000;
			}
			texto+="\n";
		}
		texto+="\n";
		return texto;
	}

}

