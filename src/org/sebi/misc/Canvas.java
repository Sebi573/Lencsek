/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.sebi.misc;

import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JPanel;
import org.sebi.components.FokuszPont;
import org.sebi.components.Kep;
import org.sebi.components.Lencse;
import org.sebi.components.Sugar;
import org.sebi.components.Targy;

/**
 * Egyedi vászon osztály létrehozása, az Objektum ősosztály leszármazottjait
 * képes kirajzolni.
 *
 * @author sebes
 */
public class Canvas extends JPanel {

    // ebben tároljuk a kirajzolandó objektumokat
    private final ArrayList<Objektum> obj = new ArrayList<>();

    /**
     * Az obj listában lévő objektumok kirajzolása
     *
     * @param g
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        obj.forEach(o -> o.rajzol(g));
    }

    /**
     * Objektum hozzáadása és kirajzolása a repaint miatt.
     *
     * @param o
     */
    public void addObjektum(Objektum o) {
        obj.add(o);
        repaint();
    }

    /**
     * Objektumok törlése a vászonról.
     */
    public void torolObjektumok() {
        obj.clear();
        repaint();
    }

    /**
     * A lencse törlése a vászonról.
     */
    public void torolLencse() {
        obj.removeIf(o -> o instanceof Lencse);
        repaint();
    }

    /**
     * A fókuszpontok törlése a vászonról.
     */
    public void torolFokuszPontok() {
        obj.removeAll(obj.stream().filter(v -> v instanceof FokuszPont).toList());
        repaint();
    }

    /**
     * A tárgy törlése a vászonról.
     */
    public void torolTargy() {
        obj.removeIf(o -> o instanceof Targy);
        repaint();
    }

    /**
     * A kép törlése a vászonról.
     */
    public void torolKep() {
        obj.removeIf(o -> o instanceof Kep);
        repaint();
    }

    /**
     * A fénysugarak törlése a vászonról.
     */
    public void torolSugarak() {
        obj.removeAll(obj.stream().filter(v -> v instanceof Sugar).toList());
        repaint();
    }
}
