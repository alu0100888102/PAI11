package modelo;

import java.awt.*;
import java.awt.event.ActionEvent;
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

import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.Timer;
import java.util.*;

public class QuickHull {
	private ArrayList<Punto> puntos;
	private ArrayList<Punto> hullPoints;
	private Algorithm algoritmo;
	
	/** Constructores */
	public QuickHull(){
		setPuntos(new ArrayList<Punto>());
		reset();
	}
	public QuickHull(ArrayList<Punto> puntos){
		setPuntos(puntos);
		reset();
	}
	
	/** Setters y getters */
	public ArrayList<Punto> getPuntos() {
		return puntos;
	}
	public Algorithm getAlgoritmo() {
		return algoritmo;
	}
	
	public void setPuntos(ArrayList<Punto> puntos) {
		this.puntos = puntos;
	}
	public ArrayList<Punto> getHullPoints() {
		return hullPoints;
	}
	public void setHullPoints(ArrayList<Punto> hullPoints) {
		this.hullPoints = hullPoints;
	}
	
	public void reset(){
		setHullPoints(new ArrayList<Punto>());
		algoritmo = new Algorithm();
	}
	
	/** Algoritmo constructor */
	
	public void nextIteration(){
		if(getHullPoints().size()==0){
			getAlgoritmo().quickhull();
		}
		else{
			getAlgoritmo().findhull();
		}
	}
	
	public void draw(Graphics g){
		Polygon hull = new Polygon();
		for(Punto punto: getHullPoints() )
			hull.addPoint(punto.getxCord(), punto.getyCord());
		
		 Graphics2D g2 = (Graphics2D) g;
		    g2.setStroke(new BasicStroke(3));
		
		g.setColor(Color.CYAN);
		g.drawPolygon(hull);
	}
	
	/** Clases privadas */
	
	private class Algorithm{
		/** parametros para ir paso a paso*/
		private ArrayList<ArrayList<Punto>> segpoints;
		private ArrayList<Punto> inicioSeg;
		private ArrayList<Punto> finalSeg;
		
		public Algorithm(){
			setSegpoints(new ArrayList<ArrayList<Punto>>());
			setInicioSeg(new ArrayList<Punto>());
			setFinalSeg(new ArrayList<Punto>());
		}
		
		/** Getters y setters */
		public ArrayList<ArrayList<Punto>> getSegpoints() {
			return segpoints;
		}
		public void setSegpoints(ArrayList<ArrayList<Punto>> spoints) {
			this.segpoints = spoints;
		}
		public ArrayList<Punto> getInicioSeg() {
			return inicioSeg;
		}
		public void setInicioSeg(ArrayList<Punto> inicioSeg) {
			this.inicioSeg = inicioSeg;
		}
		public ArrayList<Punto> getFinalSeg() {
			return finalSeg;
		}
		public void setFinalSeg(ArrayList<Punto> finalSeg) {
			this.finalSeg = finalSeg;
		}
		
		public int calculateSide(int x1, int y1, int x2, int y2, int xp, int yp){
			return (x2 - x1)*(yp - y1) - (xp - x1)*(y2 - y1);
		}
		
		private void quickhull(){
			int leftxcord = 500, rightxcord = 0;
			Punto left = new Punto(), right = new Punto();
			for(Punto punto : getPuntos()){
				if(punto.getxCord() < leftxcord){
					left = punto;
					leftxcord = punto.getxCord();
				}
				if(punto.getxCord() > rightxcord){
					right = punto;
					rightxcord = punto.getxCord();
				}
			}
			left.setColor(Color.BLUE);
			right.setColor(Color.BLUE);
			getHullPoints().add(left);
			getHullPoints().add(right);
			ArrayList<Punto> lado1 = new ArrayList<Punto>();
			ArrayList<Punto> lado2 = new ArrayList<Punto>();
			
			for(Punto p: getPuntos()){
				if(!getHullPoints().contains(p)){
					int calcpos = calculateSide(left.getxCord(), left.getyCord(), right.getxCord(), right.getyCord(), p.getxCord(), p.getyCord());
					if(calcpos >0){
						lado1.add(p);
					}
					if(calcpos < 0){
						lado2.add(p);
					}
				}
			}
			/** añade los valores a la cola para continuar con el algoritmo cuando se le llame*/
			getSegpoints().add(lado1);
			getSegpoints().add(lado2);
			getInicioSeg().add(left);
			getInicioSeg().add(right);
			getFinalSeg().add(right);
			getFinalSeg().add(left);	
		}
		
		public double distPtoR(int x1, int y1, int x2, int y2, int xp, int yp){
			double abs = Math.abs((xp-x1)*(y2-y1)-(yp-y1)*(x2-x1));
			double mod = Math.sqrt((x2-x1)*(x2-x1) + (y2-y1)*(y2-y1));
			return abs/mod;
		}
		
		public void findhull(){
			if(getSegpoints().size() == 0)
				return;
			ArrayList<Punto> area = getSegpoints().get(0);
			Punto inicio = getInicioSeg().get(0);
			Punto fin = getFinalSeg().get(0);
			getSegpoints().remove(0);
			getInicioSeg().remove(0);
			getFinalSeg().remove(0);
			if(area.size() <1){
				findhull();
				return;
			}
			double maxdist = Double.MIN_VALUE;
			Punto nextPoint=null;
			for(Punto punto : area){
				double dist = distPtoR(inicio.getxCord(), inicio.getyCord(), fin.getxCord(), fin.getyCord(), punto.getxCord(), punto.getyCord());
				if(dist > maxdist){
					maxdist = dist;
					nextPoint = punto;
				}
			}
			
			if(nextPoint == null)
				return;
			getHullPoints().add(getHullPoints().indexOf(fin), nextPoint);
			nextPoint.setColor(Color.BLUE);
			ArrayList<Punto> lado1 = new ArrayList<Punto>();
			ArrayList<Punto> lado2 = new ArrayList<Punto>();
			for(Punto p: area){
				if(!getHullPoints().contains(p)){
					int calcpos = calculateSide(inicio.getxCord(), inicio.getyCord(), nextPoint.getxCord(), nextPoint.getyCord(), p.getxCord(), p.getyCord());
					if(calcpos >0){
						lado1.add(p);
					}
				}
			}
			getSegpoints().add(lado1);
			getInicioSeg().add(inicio);
			getFinalSeg().add(nextPoint);
			
			for(Punto p: area){
				if(!getHullPoints().contains(p)){
					int calcpos = calculateSide(nextPoint.getxCord(), nextPoint.getyCord(), fin.getxCord(), fin.getyCord(), p.getxCord(), p.getyCord());
					if(calcpos >0){
						lado2.add(p);
					}
				}
			}
			getSegpoints().add(lado2);
			getInicioSeg().add(nextPoint);
			getFinalSeg().add(fin);
		}
	}
}
