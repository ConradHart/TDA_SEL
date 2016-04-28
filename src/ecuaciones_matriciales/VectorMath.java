package ecuaciones_matriciales;

import java.util.Arrays;

public class VectorMath {
	private Integer dimensionVector;
	private Double[] vector;
		
	public VectorMath(Integer dimension){
		this.setDimensionVector(dimension);
	    this.setVector(new Double[dimensionVector]);	
	    for(int i=0; i<vector.length; i++)
	    	vector[i]=0.0;
	}

	
	//Cargar Vector desde archivo
	public void cargarVectorArchivo(Integer cCoordenada, Double cComponente){
		vector[cCoordenada]=cComponente;
	}
	
	public void imprimirVector(){
		System.out.println("\nVector cargado: ");
		for(int i=0; i<vector.length; i++)
			System.out.println(vector[i] + " ");
	}
	
	
	public Integer getDimensionVector() {
		return dimensionVector;
	}

	public void setDimensionVector(Integer dimensionVector) {
		this.dimensionVector = dimensionVector;
	}

	public Double[] getVector() {
		return vector;
	}

	public void setVector(Double[] vector) {
		this.vector = vector;
	}

	@Override
	public String toString() {
		return "Vector = " + Arrays.toString(vector) + " ";
	}

	
}


