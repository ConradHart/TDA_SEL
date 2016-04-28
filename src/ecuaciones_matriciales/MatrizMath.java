package ecuaciones_matriciales;

public class MatrizMath implements Cloneable {

	private Integer dimension; //Dimension de la matriz
	private Double[][] matriz; //Matriz
	private Integer fila;
	private Integer columna;

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
		this.setMatriz(otraMatriz);//Copia a matriz, la Matriz otraMatriz
		this.setDimension(otraMatriz.length);//Cargo dimension
		this.setFila(dimension);
		this.setColumna(dimension);
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

	//Cargar Matriz desde archivo
	public void cargarMatrizArchivo(Integer iFila, Integer jColumna, Double eElemento){
				matriz[iFila][jColumna]=eElemento;
	}//fin cargar

	public void imprimirMatriz(){
		System.out.println("\nMatriz cargada: ");
		for(int i=0; i<this.getFila();i++){
			for(int j=0; j<this.getColumna(); j++){
				System.out.print(matriz[i][j] + " ");
			}
			System.out.println();
		}
	}//fin imprimir

	/** Imprime un numero real, formateado con 4 decimales, sin cambiar de linea.
	 * Es ecesario para que todas las filas de la matriz queden uniformes.
	 * @param d Numero que se imprimira
	 */
	public void imprimeElemento(double d) {
		System.out.print( String.format("%1$+01.4g", d) + " ");
	}


	/**    
	 * Imprime una matriz por pantalla.
	 * Imprime una fila en cada linea. En cada fila, imprime el borde izquierdo ("|"), 
	 * luego cada uno de los elementos de la fila y, finalmente el borde derecho ("|").
	 * La matriz aparece rodeada por sendas lineas en blanco arriba y abajo.
	 * Por ejemplo:
	 * <pre>
	 * |+1.000 +2.000 +3.000 +4.000 |
	 * |+5.000 +3.000 -2.000 +1.000 |
	 * |+2.000 +7.000 +4.000 +3.250 |
	 * |-2.000 -3.142 +7.300 +1.333 |
	 * </pre>
	 */
	public void imprime() {

		String borde = "|";

		System.out.println(); // Imprime una linea en blanco al principio
		for (int i=0; i<this.getDimension(); i++) {
			System.out.print(borde); // Imprime el borde izquierdo sin cambiar de linea
			Double[] filai = this.getMatriz()[i];
			for (int j=0; j<filai.length; j++) {
				imprimeElemento(this.getMatriz()[i][j]); // Imprime el elemento i,j sin cambiar de linea
			}            
			System.out.print(borde); // Imprime el borde derecho
			System.out.println(); // Salta a una nueva linea
		}
		System.out.println(); // Imprime una linea en blanco al final
	}//fin imprime

	//Clonación de la Matriz
	public Object clone(){
		MatrizMath obj=null;
		try{
			obj=(MatrizMath)super.clone();
		}catch(CloneNotSupportedException ex){
			System.out.println("Objeto NO se puede Clonar");
		}
		//aquí está la clave  para clonar la matriz bidimensional
		obj.matriz=(Double[][])obj.getMatriz().clone();
		for(int i=0; i<obj.getDimension(); i++){
			obj.getMatriz()[i]=(Double[])obj.getMatriz()[i].clone();
		}
		return obj;
	}

	/////////////************//PRODUCTO MATRIZ POR ESCALAR//****************//////////////

	public MatrizMath productoPorEscalar(Double alfa){
		MatrizMath matrizEscalar=new MatrizMath(this.getDimension());
		for(int i=0; i<this.getDimension(); i++){
			for(int j=0; j<this.getDimension(); j++){
				matrizEscalar.getMatriz()[i][j]=this.getMatriz()[i][j]*alfa;
			}
		}
		return matrizEscalar;
	}

	////////////***************//DETERMINANTE//************************///////////////////	
	/**
	 * Menor complementario de un elemento en la matriz.
	 * @param ni Fila del elemento cuyo menor complementario se quiere obtener
	 * @param nj Columna del elemento cuyo menor complementario se quiere obtener
	 * @return Menor complementario del elemento ni, nj de la matriz original.
	 * Se trata de una matriz igual que la original pero de la que se han eliminado la fila ni y 
	 * la columna nj.
	 */
	public MatrizMath complementaria(int ni, int nj) {
		if (this.getFila()>0 && this.getColumna()>0) {        
			MatrizMath resultado = new MatrizMath(this.getDimension()-1);
			for (int i=0, ri=0; ri<this.getFila()-1; i++, ri++) {
				if (i==ni) {
					i++;
				}
				for (int j=0, rj=0; rj<this.getColumna()-1; j++, rj++) {
					if (j==nj) {
						j++;
					}
					resultado.getMatriz()[ri][rj] = this.getMatriz()[i][j];
				}
			}
			return resultado;
		} else return null;
	}

	/**
	 * Determinante de una matriz. Se supone la matriz cuadrada.
	 * El determinante de una matriz se calcula siguiendo la regla de Laplace, según:
	 * <ul>
	 * <li>Si la matriz tiene dimensión 0 (0 filas por 0 columnas), el determinante vale 1.</li>
	 * <li>Si la matriz tiene dimensión mayor que cero, el determinante se calcula mediante el método de los adjuntos.
	 * El adjunto de un elemento es el producto de:
	 *      <ul>
	 *      <li>el determinante de la matriz complementaria del elemento, </li>
	 *      <li>por el valor del elemento</li>
	 *      <li>por un factor de signo que vale (+1) si la paridad del índice de la fila y de la columna son iguales 
	 *      <li>o (-1) si la paridad del índice de la fila y de la columna son distintos.</li>
	 *      </ul>
	 * El determinante de la matriz será igual al sumatorio de los adjuntos de los elementos de la primera fila.
	 * </li>
	 * </ul>
	 * @return Determinante de la matriz dada por el método de la complementaria.
	 * @see <a href="http://en.wikipedia.org/wiki/Laplace_expansion">Expansión de Laplace</a>
	 */
	public double determinante(){

		if (this.getFila()==0||this.getColumna()==0) {
			return 1;
		} else {
			double resultado = 0;
			for (int i=0; i<this.getFila(); i++){
				int j=0;
				int signo;
				if ((i+j)%2 == 0) {
					signo = 1;
				}else {
					signo = -1;
				}
				resultado += signo * this.getMatriz()[i][j] * complementaria(i, j).determinante();
			}
			return resultado;
		}
	}

	//Determinante por otro método
	public Double determinante2(){
		if(this.getFila()==this.getColumna()){
			MatrizMath matrizAux=(MatrizMath)this.clone();//Casteo a Matriz de obj que devuelve el método clone.
			double deter=1;
			for(int k=0; k<this.getDimension()-1; k++){
				for(int i=k+1; i<this.getDimension(); i++){
					for(int j=k+1; j<this.getDimension(); j++){
						matrizAux.getMatriz()[i][j]-=matrizAux.getMatriz()[i][k]*matrizAux.getMatriz()[k][j]/matrizAux.getMatriz()[k][k];
					}
				}
			}

			for(int i=0; i<this.getDimension(); i++){
				deter*=matrizAux.getMatriz()[i][i];
			}
			return deter;
		}else{System.out.println("No es una matriz cuadrada. No puede calcularse su determinante");
		return null;}
	}

	///////////////*************//TRASPUESTA-COFACTORES-ADJUNTA//***************************/////////////////
	public MatrizMath transponerMatriz(MatrizMath matriz) {

		MatrizMath matrizTranspuesta = new MatrizMath(matriz.getDimension());
		for(int i = 0; i < matriz.getFila(); i++){
			for(int j = 0; j < matriz.getColumna(); j++) {
				matrizTranspuesta.matriz[j][i] = matriz.matriz[i][j];
			}
		}

		return matrizTranspuesta;
	}	

	public MatrizMath calcularCofactoresMatriz(MatrizMath matriz){
		MatrizMath mCofactor=new MatrizMath(matriz.getDimension());
		for(int i=0;i<matriz.getFila();i++) {
			for(int j=0;j<matriz.getColumna();j++) {
				MatrizMath det= new MatrizMath(matriz.getDimension()-1);
				Double detValor;
				for(int k=0;k<matriz.getDimension();k++) {
					if(k!=i) {
						for(int l=0;l<matriz.getDimension();l++) {
							if(l!=j) {
								int indice1=k<i ? k : k-1 ;
								int indice2=l<j ? l : l-1 ;
								det.matriz[indice1][indice2]=matriz.matriz[k][l];
							}
						}
					}
				}
				detValor=matriz.determinante();
				mCofactor.matriz[i][j]=detValor * (double)Math.pow(-1, i+j+2);
			}
		}
		return mCofactor;
	}

	public MatrizMath calcularMatrizAdjunta(MatrizMath matriz){
		return matriz.transponerMatriz(calcularCofactoresMatriz(matriz));
	}

	/////////////////*************//MATRIZ IDENTIDAD//*************///////////////////////
	public MatrizMath matrizIdentidad(){
		MatrizMath mIdent = new MatrizMath(this.getDimension());
		for(int i=0; i<this.getDimension(); i++){
			for(int j=0; j<this.getDimension(); j++){
				mIdent.matriz[i][i]=1.0;
			}//Fin inicializaciï¿½n de Matriz Cuadrada
		}
		return mIdent;
	}

	/////////////////**********//INVERSA//**********************///////////////////////
	public MatrizMath matrizInversaPorAdjunta(MatrizMath matriz){
		Double det=1/matriz.determinante();
		MatrizMath adjunta=calcularMatrizAdjunta(matriz);
		MatrizMath inversa= adjunta.productoPorEscalar(det);
		return inversa;
	}
	

	public MatrizMath matrizInversaPorGaussJordan(){
		if(this.getFila()==this.getColumna()){
			if(this.determinante2()!=0){
				MatrizMath matrizAux=(MatrizMath)this.clone();
				MatrizMath matrizIdentidad=this.matrizIdentidad();   //matriz de los términos independientes
				MatrizMath matrizInversa=new MatrizMath(this.getDimension());   //matriz de las incógnitas

				//transformación de la matriz y de los términos independientes
				for(int k=0; k<this.getDimension()-1; k++){
					for(int i=k+1; i<this.getDimension(); i++){
						//términos independientes
						for(int j=0; j<this.getDimension(); j++){
							matrizIdentidad.getMatriz()[i][j]-=matrizAux.getMatriz()[i][k]*matrizIdentidad.getMatriz()[k][j]/matrizAux.getMatriz()[k][k];
						}
						//elementos de la matriz
						for(int j=k+1; j<this.getDimension(); j++){
							matrizAux.getMatriz()[i][j]-=matrizAux.getMatriz()[i][k]*matrizAux.getMatriz()[k][j]/matrizAux.getMatriz()[k][k];
						}
					}
				}
				//cálculo de las incógnitas, elementos de la matriz inversa
				for(int j=0; j<this.getDimension(); j++){
					matrizInversa.getMatriz()[this.getDimension()-1][j]=matrizIdentidad.getMatriz()[this.getDimension()-1][j]/matrizAux.getMatriz()[this.getDimension()-1][this.getDimension()-1];
					for(int i=this.getDimension()-2; i>=0; i--){
						matrizInversa.getMatriz()[i][j]=matrizIdentidad.getMatriz()[i][j]/matrizAux.getMatriz()[i][i];
						for(int k=this.getDimension()-1; k>i; k--){
							matrizInversa.getMatriz()[i][j]-=matrizAux.getMatriz()[i][k]*matrizInversa.getMatriz()[k][j]/matrizAux.getMatriz()[i][i];
						}
					}
				}
				return matrizInversa;
			}else{System.out.println("Su determinante es 0. No puede calcularse su Inversa");
			return null;}
		}else{System.out.println("No es una matriz cuadrada. No puede calcularse su Inversa");
		return null;}
	}	

	//////////////******************//**SEL**//****************************///////////////////////
	public VectorMath resolverSistemaEcuacionesLineales(MatrizMath matrizInversa, VectorMath terminosIndep){
		VectorMath vectorIncognitas= new VectorMath(terminosIndep.getDimensionVector());
		for(int i=0; i<matrizInversa.getDimension(); i++){
			for(int j=0; j<matrizInversa.getDimension(); j++){
				vectorIncognitas.getVector()[i]+=matrizInversa.getMatriz()[i][j]*terminosIndep.getVector()[j];
			}
		}
		return vectorIncognitas;
	}

	/////////////////**************//PRODUCTO ENTRE MATRICES//******************////////////////////
	public MatrizMath productoEntreMatrices(MatrizMath b){
		if(this.getColumna()==b.getFila()){
			MatrizMath matrizProducto=new MatrizMath(this.getDimension());
			for(int i=0; i<this.getDimension(); i++){
				for(int j=0; j<this.getDimension(); j++){
					for(int k=0; k<this.getDimension(); k++){
						matrizProducto.getMatriz()[i][j]+=this.getMatriz()[i][k]*b.getMatriz()[k][j];
					}
				}
			}
			return matrizProducto;
		}else{System.out.println("No es una matriz cuadrada. No puede calcularse su Producto");
		return null;}
	}

	////////////////****************//RESTA - SUMA//*********************///////////////////
	public MatrizMath restarMatrices(MatrizMath b){
		if((this.getFila()==b.getFila())&&(this.getColumna()==b.getColumna())){
			MatrizMath matrizResta=new MatrizMath(this.getDimension());//Llama al constructor que pide "dimension".
			for(int i=0; i<this.getDimension(); i++){
				for(int j=0; j<this.getDimension(); j++){
					matrizResta.getMatriz()[i][j]=this.getMatriz()[i][j]-b.getMatriz()[i][j];
				}
			}
			return matrizResta;
		}else{System.out.println("Las matrices no tienen el mismo \"orden\". No puede calcularse su Resta");
		return null;}
	}

	public MatrizMath sumarMatrices(MatrizMath b){
		if((this.getFila()==b.getFila())&&(this.getColumna()==b.getColumna())){
			MatrizMath matrizSuma=new MatrizMath(this.getDimension());//Llama al constructor que pide "dimension".
			for(int i=0; i<this.getDimension(); i++){
				for(int j=0; j<this.getDimension(); j++){
					matrizSuma.getMatriz()[i][j]=this.getMatriz()[i][j]+b.getMatriz()[i][j];
				}
			}
			return matrizSuma;
		}else{System.out.println("Las matrices no tienen el mismo \"orden\". No puede calcularse su Resta");
		return null;}
	}
	

	/////////////******************//CALCULO DE ERROR//**************************///////////////////////
	public Double calculoDeError(MatrizMath matriz){
		Double error=0.0;
		
		try {
			//					 System.out.println("m1= " + matriz.productoEntreMatrices(matriz.inversa()) +
			//					 " - Esperado 10.77032961");
			//					MatrizMath identidad= new MatrizMath(matriz.getDimension());
			//					identidad.cargarMatriz();

//			System.out.println("m2= " + this.matrizIdentidad().restarMatrices(matriz.productoEntreMatrices(matriz.matrizInversaPorGaussJordan())).normaDos()
//					+ " - Esperado < 1E-12");

			//					System.out.println("m2= " + (this.productoEntreMatrices(matriz.inversa()).resta(this.matrizIdentidad())).normaDos()
			//							+ " - Esperado < 1E-12");
			//					
			//					System.out.println("m3= " + matriz3.getIdentidad().resta(matriz3.producto(matriz3.inversa())).normaDos()
			//							+ " - Esperado < 1E-12");
			//					System.out.println("m4= " + matriz4.getIdentidad().resta(matriz4.producto(matriz4.inversa())).normaDos()
			//							+ " - Esperado < 1E-12");
			//System.out.println(matriz.productoEntreMatrices(this.inversa()));
            error=this.matrizIdentidad().restarMatrices(matriz.productoEntreMatrices(matriz.matrizInversaPorGaussJordan())).normaDos();
            
		} catch (Exception e) {
			e.printStackTrace();
		}
		return error;
	}

	/////////////////*****************//NORMA//******************************//////////////////////
	/**
	 * Devuelve el valor ubicado en las posiciones indicadas por i y j
	 * 
	 * @param i
	 *            numero de fila
	 * @param j
	 *            numero de columna
	 * @return valor almacenado en (i,j)
	 */
	public double valorEnIndice(int i, int j){
		return this.matriz[i][j];
	}

	/**
	 * Realiza la norma uno de la matriz (Suma absoluta de columnas)
	 * 
	 * @return (Suma absoluta de columnas)
	 */
	public double normaUno(){
		double maximo=0;
		double valor;
		for (int j=0; j< this.getFila(); j++) {
			valor=0;
			for (int i=0; i<this.getColumna(); i++) {
				valor += Math.abs(valorEnIndice(i,j));
				if (valor > maximo)
					maximo=valor;
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
		double resultado=0.0;
		for (int i=0; i<this.getFila(); i++)
			for (int j=0; j<this.getColumna(); j++)
				resultado += valorEnIndice(i, j) * valorEnIndice(i, j);
		return Math.sqrt(resultado);
	}

	/**
	 * Realiza la norma infinito de la matriz (Suma absoluta de filas)
	 * 
	 * @return (Suma absoluta de filas)
	 */
	public double normaInfinito() {
		double maximo=0;
		double valor;
		for (int i=0; i< this.getFila(); i++) {
			valor=0;
			for (int j=0; j<this.getColumna(); j++) {
				valor += Math.abs(valorEnIndice(i, j));
				if (valor>maximo)
					maximo=valor;
			}
		}
		return maximo;
	}


} 
