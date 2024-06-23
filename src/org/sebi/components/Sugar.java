/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.sebi.components;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import org.sebi.misc.Objektum;
import org.sebi.misc.Pozicio;

/**
 *
 * @author sebes
 */
public class Sugar extends Objektum {

    private final Pozicio[] pontok;
    private final boolean dashed;

    public Sugar(Pozicio[] pontok) {
        super(0, 0);
        this.pontok = pontok;
        this.dashed = false;
    }

    public Sugar(Pozicio[] pontok, boolean dashed) {
        super(0, 0);
        this.pontok = pontok;
        this.dashed = dashed;
    }

    @Override
    public void rajzol(Graphics g) {
        if (dashed) {
            
            //creates a copy of the Graphics instance
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.setColor(Color.cyan);
            Stroke d = new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0);
            g2d.setStroke(d);
            for (int i = 0; i < pontok.length - 1; i++) {
                g2d.drawLine((int) pontok[i].getX(), (int) pontok[i].getY(), (int) pontok[i + 1].getX(), (int) pontok[i + 1].getY());
            }
            g2d.setColor(Color.BLACK);
        } else {
            g.setColor(Color.RED);
            for (int i = 0; i < pontok.length - 1; i++) {
                g.drawLine((int) pontok[i].getX(), (int) pontok[i].getY(), (int) pontok[i + 1].getX(), (int) pontok[i + 1].getY());
            }
            g.setColor(Color.BLACK);
        }
    }

}
