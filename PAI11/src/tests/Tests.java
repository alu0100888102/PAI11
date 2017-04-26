package tests;

import static org.junit.Assert.*;
import modelo.*;
import org.junit.*;
import java.awt.*;
import javax.swing.*;
import graphic.*;
import java.util.ArrayList;

public class Tests {
	
	@Test
	public final void testPunto() {
		Punto test = new Punto();
		assert(test.getxCord()==0);
		assert(test.getyCord()==0);
		assert(test.getColor()==Color.RED);
	}

	@Test
	public final void testPuntoIntInt() {
		Punto test = new Punto(1,2);
		assert(test.getxCord()==1);
		assert(test.getyCord()==2);
		assert(test.getColor()==Color.RED);
	}

	@Test
	public final void testPuntoIntIntColor() {
		Punto  test = new Punto(1,2, Color.WHITE);
		assert(test.getxCord()==1);
		assert(test.getyCord()==2);
		assert(test.getColor()==Color.WHITE);
	}

	@Test
	public final void testSetColor() {
		Punto test = new Punto();
		test.setColor(Color.BLACK);
		assert(test.getColor()==Color.BLACK);
	}

	@Test
	public final void testSetxCord() {
		Punto test = new Punto();
		test.setxCord(10);
		assert(test.getxCord()==10);
	}

	@Test
	public final void testSetyCord() {
		Punto test = new Punto();
		test.setyCord(10);
		assert(test.getyCord()==10);
	}
	
	/** Test de quickHull */
	
	@Test
	public final void testQuickHull() {
		QuickHull test = new QuickHull();
		assert(test.getAlgoritmo() != null);
		assert(test.getHullPoints() != null);
		assert(test.getPuntos() != null);
	}

	@Test
	public final void testQuickHullArrayListOfPunto() {
		Punto p = new Punto(100, 100, Color.BLACK);
		ArrayList<Punto> temp = new ArrayList<Punto>();
		temp.add(p);
		QuickHull test = new QuickHull(temp);
		assert(test.getAlgoritmo() != null);
		assert(test.getHullPoints() != null);
		assert(test.getPuntos() == temp);
		assert(test.getPuntos().get(0).getColor() == Color.BLACK);
	}

	@Test
	public final void testSetPuntos() {
		Punto p = new Punto(100, 100, Color.BLACK);
		ArrayList<Punto> temp = new ArrayList<Punto>();
		temp.add(p);
		QuickHull test = new QuickHull();
		test.setPuntos(temp);
		assert(test.getPuntos() == temp);
		assert(test.getPuntos().get(0).getColor() == Color.BLACK);
	}

	@Test
	public final void testSetHullPoints() {
		Punto p = new Punto(100, 100, Color.BLACK);
		ArrayList<Punto> temp = new ArrayList<Punto>();
		temp.add(p);
		QuickHull test = new QuickHull();
		test.setHullPoints(temp);
		assert(test.getHullPoints() == temp);
		assert(test.getHullPoints().get(0).getColor() == Color.BLACK);
	}

	@Test
	public final void testReset() {
		Punto p = new Punto(100, 100, Color.BLACK);
		ArrayList<Punto> temp = new ArrayList<Punto>();
		temp.add(p);
		QuickHull test = new QuickHull();
		test.setHullPoints(temp);
		assert(test.getHullPoints() == temp);
		assert(test.getHullPoints().get(0).getColor() == Color.BLACK);
		
		test.reset();
		assert(test.getHullPoints().size() == 0);
		assert(test.getHullPoints() != temp);
	}
	
	@Test
	public final void testAlgoritmo(){
		ArrayList<Punto> puntos = new ArrayList<Punto>();
		Punto  p1 = new Punto(100,0);
		Punto  p2 = new Punto(0,100);
		Punto  p3 = new Punto(0,0);
		Punto  p4 = new Punto(100,100);
		Punto  p5 = new Punto(50,50);
		Punto  p6 = new Punto(70,10);
		Punto  p7 = new Punto(10,10);
		Punto  p8 = new Punto(10,1);
		Punto  p9 = new Punto(1,99);
		Punto  p10 = new Punto(51,49);
		Punto  p11 = new Punto(200, 90);
		puntos.add(p1);
		puntos.add(p2);
		puntos.add(p3);
		puntos.add(p4);
		puntos.add(p5);
		puntos.add(p6);
		puntos.add(p7);
		puntos.add(p8);
		puntos.add(p9);
		puntos.add(p10);
		puntos.add(p11);
		
		QuickHull test = new QuickHull(puntos);
		assert(test.getHullPoints().size() == 0);
		
		for(int i =0; i< 10; i++)
			test.nextIteration();
		
		assert(test.getHullPoints().size() == 5);
		assert(test.getHullPoints().contains(p1));
		assert(test.getHullPoints().contains(p2));
		assert(test.getHullPoints().contains(p3));
		assert(test.getHullPoints().contains(p4));
		assert(test.getHullPoints().contains(p11));
	}
	
	/** Test PanelPuntos */
	
	@Test
	public final void testPanelPuntos() {
		PanelPuntos test = new PanelPuntos();
		assert(test.getPuntos().size() == 10);
		assert(test.getHull() != null);
		assert(test.getNpuntos() == 10);
		assert(test.getThull() == 1);
		assert(test.getTimer() != null);
		assert(test.isPause() == true);
	}

	@Test
	public final void testPanelPuntosIntInt() {
		PanelPuntos test = new PanelPuntos(100, 2);
		assert(test.getPuntos().size() == 100);
		assert(test.getHull() != null);
		assert(test.getNpuntos() == 100);
		assert(test.getThull() == 2);
		assert(test.getTimer() != null);
		assert(test.isPause() == true);
	}

	@Test
	public final void testSetPause() {
		PanelPuntos test = new PanelPuntos();
		assert(test.isPause() == true);
		test.setPause(false);
		assert(test.isPause() == false);
	}

	@Test
	public final void testSetPuntosPanel() {
		PanelPuntos test = new PanelPuntos();
		ArrayList<Punto> puntos = test.getPuntos();
		ArrayList<Punto> temp = new ArrayList<Punto>();
		temp.add(new Punto());
		test.setPuntos(temp);
		assert(test.getPuntos() != puntos);
		assert(test.getPuntos() == temp);
	}

	@Test
	public final void testSetNpuntos() {
		PanelPuntos test = new PanelPuntos();
		assert(test.getNpuntos() == 10);
		test.setNpuntos(11);
		assert(test.getNpuntos() == 11);
	}

	@Test
	public final void testSetThull() {
		PanelPuntos test = new PanelPuntos();
		assert(test.getThull() == 1);
		test.setThull(11);
		assert(test.getThull() == 11);
	}
	
	/** Pruebas Frame*/
	@Test
	public final void testFramePuntos() {
		FramePuntos test = new FramePuntos(10, 1);
		assert(test.getPanel() != null);
	}

	@Test
	public final void testSetPanel() {
		FramePuntos test = new FramePuntos(10, 1);
		PanelPuntos temp = test.getPanel();
		PanelPuntos nuevo = new PanelPuntos();
		test.setPanel(nuevo);
		assert(test.getPanel() != temp);
		assert(test.getPanel() == nuevo);
	}
}
