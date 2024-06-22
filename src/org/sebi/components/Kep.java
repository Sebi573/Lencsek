/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.sebi.components;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import org.sebi.misc.Objektum;
import org.sebi.misc.Pozicio;

/**
 *
 * @author sebes
 */
public final class Kep extends Objektum {

    private final double t;
    private final double targyMagassag;
    private double f;

    private String allas;
    private String tipus;

    public Kep(double x, double y, String allas, String tipus, double targyMagassag, double t, double f) {
        super(x, y);
        this.t = t;
        this.targyMagassag = targyMagassag;
        this.f = f;
        this.allas = allas;
        this.tipus = tipus;
    }

    public int getKepTavolsag() {
        return "latszolagos".equals(tipus) ? ((int) ((t * f) / (t - f))) : ((int) ((t * f) / (t - f)));
    }

    public double getNagyitas() {
        return Math.abs(getKepTavolsag() / t);
    }

    public double getMagassag() {
        return targyMagassag * getNagyitas();
    }

    public Pozicio getLocation() {
        return allas.equals("forditott") ? new Pozicio(vaszonSzelesseg / 2 + getKepTavolsag(), vaszonMagassag / 2 + (int) (targyMagassag * getNagyitas())) : new Pozicio(vaszonSzelesseg / 2 + getKepTavolsag(), vaszonMagassag / 2 - (int) (targyMagassag * getNagyitas()));

    }

    public String getAllas() {
        return allas;
    }

    public void setAllas(String allas) {
        this.allas = allas;
    }

    public String getTipus() {
        return tipus;
    }

    public void setTipus(String tipus) {
        this.tipus = tipus;
    }

    public void setF(double f) {
        this.f = f;
    }

    public double getF() {
        return f;
    }

    @Override
    public void rajzol(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(3));
        g2.setColor(Color.BLUE);

        if (allas.equals("forditott")) {
            g2.drawLine(vaszonSzelesseg / 2 + getKepTavolsag(), vaszonMagassag / 2, vaszonSzelesseg / 2 + getKepTavolsag(), vaszonMagassag / 2 + (int) (targyMagassag * getNagyitas()));
        } else {
            g2.drawLine(vaszonSzelesseg / 2 + getKepTavolsag(), vaszonMagassag / 2, vaszonSzelesseg / 2 + getKepTavolsag(), vaszonMagassag / 2 - (int) (targyMagassag * getNagyitas()));
        }
        g2.setColor(Color.BLACK);
        g2.setStroke(new BasicStroke(1));
    }

}
