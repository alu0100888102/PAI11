/**
 * PRACTICA 11
 * 
 * Esta practica nos pide hacer una representación gráfica del algortimo quickhull
 * 
 * @author alu0100888102
 * @version 1.0
 * Ángel Hamilton Lopez
 * alu0100888102@ull.es
 */

package controlador;

import graphic.*;
import modelo.*;

public class Main {
	public static void main(String args[]){
		int puntos = 10;
		int tiempo = 2;
		try{
			puntos = Integer.parseInt(args[0]);
			tiempo = Integer.parseInt(args[1]);
		}
		catch(Exception e){
			System.out.println("EROR: La aplicachión ha de utilizarse: nombre numeroPuntos factorDeTiempo");
			System.exit(1);
		}
		FramePuntos frame = new FramePuntos(puntos, tiempo);
		frame.setLocationRelativeTo(null); // Center the frame
	    frame.setSize(600, 600);
	    frame.setVisible(true);
	}
}