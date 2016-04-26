package ecuaciones_matriciales;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

public class Archivo {
	
	private static String Resource1 = "";
	private static String Resource2 = "";
	private static String Resource3 = "";
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
			System.out.println("Leyendo matriz del archivo...");
			
			
			while ((linea = br.readLine()) != null){
			
				if(PrimeraLinea == true){
						System.out.println("El orden de la matriz: " + linea);
						PrimeraLinea = false;
						MatrizMath Matriz = new MatrizMath(Integer.parseInt(linea)); //Orden de la matriz
					}else{
						System.out.println("Contenido: " + linea);	
					}
				
				

				
				//Escribo la linea leida en la clase Palindromo
				//Palindromo palin = new Palindromo(linea.length(), linea);
					
				//System.out.println("La linea " + "'" + linea + "'" + " fue escrita.");
			}
			
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
        try
        {
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
