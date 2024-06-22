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
public class Lencse extends Objektum {

    private String tipus;
    private int width;
    private int height;

    public Lencse(String tipus, double x, double y, int width, int height) {
        super(x, y);
        this.tipus = tipus;
        if (tipus.equalsIgnoreCase("gyujto")) {
            this.width = (int) (width * 0.04);
            this.height = (int) (height * 0.6);
        } else {
            this.width = (int) (width*0.08);
            this.height = (int) (height*0.3);
        }
    }

    public String getTipus() {
        return tipus;
    }

    public void setTipus(String tipus) {
        this.tipus = tipus;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * Kétféle lencsetípus kirajzolása a vászon közepére.
     *
     * @param g
     */
    @Override
    public void rajzol(Graphics g) {
        if ("gyujto".equals(tipus)) {
            g.drawArc(vaszonSzelesseg / 2 - width / 2, vaszonMagassag / 2 - height / 2, width, height, 0, 360);
        } else {
            g.drawLine(vaszonSzelesseg / 2 - width / 2, vaszonMagassag / 2 - height, vaszonSzelesseg / 2 + width / 2, vaszonMagassag / 2 - height);
            g.drawLine(vaszonSzelesseg / 2 - width / 2, vaszonMagassag / 2 + height, vaszonSzelesseg / 2 + width / 2, vaszonMagassag / 2 + height);
            g.drawArc(vaszonSzelesseg / 2 - width + (int) (vaszonSzelesseg*0.02), vaszonMagassag / 2 - height, width / 2, height*2, 90, -180);
            g.drawArc(vaszonSzelesseg / 2 + width - (int) (vaszonSzelesseg*0.06), vaszonMagassag / 2 - height, width / 2, height*2, 90, 180);
        }
    }

}
