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
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import modelo.*;

public class FramePuntos extends JFrame {
	private PanelPuntos panel; /** Panel con la bola */
	
	public FramePuntos(int puntos, int velocidad){
		this.setLayout(new BorderLayout());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		panel = new PanelPuntos(puntos, velocidad);
		this.add(panel, BorderLayout.CENTER);
		
		/** Creacion de los botones y el panel que los contendrá */
		JButton botonInizializar = new JButton("Inizializar");
		JButton botonEjecutar = new JButton("Ejecutar");
		JButton botonParar = new JButton("Parar");
		JButton botonPaso = new JButton("Paso");
		JPanel botones = new JPanel();
		
		botones.setLayout(new GridLayout(1,4));
		botones.add(botonInizializar);
		botones.add(botonEjecutar);
		botones.add(botonParar);
		botones.add(botonPaso);

		
		/** Añadimos los listener */
		botonInizializar.addActionListener(new InizializarListener());
		botonEjecutar.addActionListener(new EjecutarListener());
		botonParar.addActionListener(new ParartListener());
		botonPaso.addActionListener(new PasoListener());
		
		add(botones, BorderLayout.SOUTH);
	}
	
	
	/** Setters y getters */
	public PanelPuntos getPanel() {
		return panel;
	}
	public void setPanel(PanelPuntos panel) {
		this.panel = panel;
	}

	/** Clases Listener privadas */
	private class InizializarListener implements ActionListener{
		
		public void actionPerformed(ActionEvent e) {
			getPanel().initializePuntos();
		}
		
	}
	
	private class EjecutarListener implements ActionListener{
		
		public void actionPerformed(ActionEvent e) {
			getPanel().setPause(false);
			getPanel().start();
		}
		
	}
	
	private class ParartListener implements ActionListener{
		
		public void actionPerformed(ActionEvent e) {
			getPanel().setPause(!getPanel().isPause());
		}
		
	}
	
	private class PasoListener implements ActionListener{
		
		public void actionPerformed(ActionEvent e) {
			getPanel().nextStep();
		}
		
	}
}
