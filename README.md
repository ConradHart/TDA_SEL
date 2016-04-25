# TDA_SEL
TP1 Sistemas de Ecuaciones Lineales
TP1 Sistemas de Ecuaciones Lineales
	Implementar el TDA SEL utilizando objetos MatrizMath y VectorMAth
	Realizar un Diagrama de clases
	Análizar el rendimiento (Tiempo como función de la dimensión del SEL). 
Se espera que sea posible resolver sistemas de dimensión 1000 o superior. 
	Graficar.
Fecha de entrega

Nota: En todo lo anterior, trabajar siguiendo nuestra metodología. 
Notación matricial de un SEL:  a*x=b

formato de archivos de entrada y salida
sel.in	 
 n
.
.
i j aij
.
.
.
b0
.
.
.
bn-1	 

solucion.out
n
x0
.
.
.
.
xn-1

error

Ejemplo:

 sel.in	
 3
0 0 2
0 1 9
0 2 9
1 0 3
1 1 9
1 2 2
2 0 1
2 1 0
2 2 1
0
1
2	 

solucion.out
3
1.875
-0.5416666666666666
0.125

0.0




Uso de Calendar para medir rendimiento de algoritmos:

import java.util.GregorianCalendar;
import java.util.Calendar;

Calendar tIni = new GregorianCalendar();

// start code      
…
// end code

Calendar tFin = new GregorianCalendar();

long diff = tFin.getTimeInMillis() – tIni.getTimeInMillis();
System.out.println(diff);


