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


package graphic;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.Timer;
import modelo.*;
import java.util.*;

public class PanelPuntos extends JPanel {
	private ArrayList<Punto> puntos;
	private QuickHull hull;
	private int npuntos;
	private int thull;
	private boolean pause;
	private Timer timer;
	
	/** Constructores */
	public PanelPuntos(){
		setNpuntos(10);
		setThull(1);
		initializePuntos();
		setPause(true);
		setTimer(new Timer(1000/getThull(), new TimeListener()));
	}
	public PanelPuntos(int puntos, int time){
		setNpuntos(puntos);
		setThull(time);
		initializePuntos();
		setPause(true);
		setTimer(new Timer(1000/time, new TimeListener()));
	}
	
	public void initializePuntos(){
		setPuntos(new ArrayList<Punto>());
		Random randomGenerator = new Random();
		for(int i =0; i<getNpuntos(); i++){
			int x = randomGenerator.nextInt(500);
			int y = randomGenerator.nextInt(500);
			getPuntos().add(new Punto(40+x,20+y));
		}
		setHull(new QuickHull(getPuntos()));
		this.repaint();
		setPause(true);
	}
	
	
	/** Setters y getters*/
	public ArrayList<Punto> getPuntos() {
		return puntos;
	}
	public Timer getTimer() {
		return timer;
	}
	public void setTimer(Timer timer) {
		this.timer = timer;
	}
	public boolean isPause() {
		return pause;
	}
	public void setPause(boolean pause) {
		this.pause = pause;
	}
	public void setPuntos(ArrayList<Punto> puntos) {
		this.puntos = puntos;
	}
	public QuickHull getHull() {
		return hull;
	}
	public void setHull(QuickHull hull) {
		this.hull = hull;
	}
	public int getNpuntos() {
		return npuntos;
	}
	public void setNpuntos(int npuntos) {
		this.npuntos = npuntos;
	}
	public int getThull() {
		return thull;
	}
	public void setThull(int thull) {
		this.thull = thull;
	}

	public void start(){
		getTimer().start();
	}
	
	public void nextStep(){
		getHull().nextIteration();
		this.repaint();
	}

	/** Metodo para pintar*/
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		for(Punto punto: getPuntos()){
			g.setColor(punto.getColor());
			g.fillOval(punto.getxCord()-5, punto.getyCord()-5, 10, 10);
		}
		getHull().draw(g);
	}
	
	/**Clases privada*/
	private class TimeListener implements ActionListener{
		
		public void actionPerformed(ActionEvent e) {
			if(!isPause())
				nextStep();
		}
	}	
}
