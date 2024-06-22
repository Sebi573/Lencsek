/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.sebi.components;

import java.awt.Graphics;
import org.sebi.misc.Objektum;
import org.sebi.misc.Pozicio;

/**
 *
 * @author sebes
 */
public class Targy extends Objektum {

    private double height;
    private int t;

    public Targy(double x, double y, double height) {
        super(x, y);
        this.height = height*skalazasiTenyezo;
        this.t = (int) (Math.ceil(x * skalazasiTenyezo))*-1;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public int getT() {
        return t;
    }

    public void setT(int t) {
        this.t = t;
    }
    
    public Pozicio getLocation() {
        return new Pozicio(vaszonSzelesseg / 2 - t, vaszonMagassag / 2 - (int) (height));
    }
    
    
    @Override
    public void rajzol(Graphics g) {
        super.setG(g);
        g.drawLine(vaszonSzelesseg / 2 - t, vaszonMagassag / 2, vaszonSzelesseg / 2 - t, vaszonMagassag / 2 - (int) (height));
    }

}
