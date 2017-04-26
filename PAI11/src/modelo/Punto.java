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

package modelo;

import java.awt.*;

public class Punto {
	private int xCord;
	private int yCord;
	private Color color;
	
	/** Constructores*/
	public Punto(){
		setxCord(0);
		setyCord(0);
		setColor(Color.RED);
	}
	public Punto(int x, int y){
		setxCord(x);
		setyCord(y);
		setColor(Color.RED);
	}
	public Punto(int x, int y, Color c){
		setxCord(x);
		setyCord(y);
		setColor(c);
	}
	
	/** Getters y setters*/
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	public int getxCord() {
		return xCord;
	}
	public void setxCord(int xCord) {
		this.xCord = xCord;
	}
	public int getyCord() {
		return yCord;
	}
	public void setyCord(int yCord) {
		this.yCord = yCord;
	}
}
