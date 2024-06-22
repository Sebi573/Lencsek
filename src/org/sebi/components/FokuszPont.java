/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.sebi.components;

import java.awt.Graphics;
import org.sebi.misc.Objektum;

/**
 *
 * @author sebes
 */
public class FokuszPont extends Objektum {

    private int fokusz;
    private final String szoveg;
    
    /**
     * FokuszPont objektum konstruktora.
     * A fókusztávolság beállítása
     * @param x
     * @param y
     * @param f
     * @param szoveg 
     */
    public FokuszPont(double x, double y, double f, String szoveg) {
        super(x, y);
        this.fokusz = f < 0 ? ((int) (Math.floor(f * skalazasiTenyezo))) : ((int) (Math.ceil(f * skalazasiTenyezo)));
        this.szoveg = szoveg;
    }

    public void setF(int f) {
        this.fokusz = f;
    }
    
    /**
     * Fókuszpont kirajzolása és alá a hozzátartozó szöveg.
     * @param g 
     */
    @Override
    public void rajzol(Graphics g) {
        g.drawLine(vaszonSzelesseg / 2 + fokusz, vaszonMagassag / 2 - 10, vaszonSzelesseg / 2 + fokusz, vaszonMagassag / 2 + 10);
        g.drawString(szoveg, vaszonSzelesseg / 2 + fokusz, vaszonMagassag / 2 + 30);

    }

    public double getF() {
        return fokusz;
    }

}
