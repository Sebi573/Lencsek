/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.sebi.misc;

import java.awt.Graphics;

/**
 *
 * @author sebes
 */
public abstract class Objektum extends Pozicio {

    protected Graphics g;
    protected int skalazasiTenyezo = 30;

    protected static int vaszonSzelesseg;
    protected static int vaszonMagassag;

    public Objektum(double x, double y) {
        super(x, y);
    }
    
    

    public Graphics getG() {
        return g;
    }

    public void setG(Graphics g) {
        this.g = g;
    }

    public int getSkalazasiTenyezo() {
        return skalazasiTenyezo;
    }

    public void setSkalazasiTenyezo(int skalazasiTenyezo) {
        this.skalazasiTenyezo = skalazasiTenyezo;
    }

    public static int getVaszonSzelesseg() {
        return vaszonSzelesseg;
    }

    public static void setVaszonSzelesseg(int vaszonSzelesseg) {
        Objektum.vaszonSzelesseg = vaszonSzelesseg;
    }

    public static int getVaszonMagassag() {
        return vaszonMagassag;
    }

    public static void setVaszonMagassag(int vaszonMagassag) {
        Objektum.vaszonMagassag = vaszonMagassag;
    }

    public abstract void rajzol(Graphics g);

}
