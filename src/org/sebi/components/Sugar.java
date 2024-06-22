/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.sebi.components;

import java.awt.Color;
import java.awt.Graphics;
import org.sebi.misc.Objektum;
import org.sebi.misc.Pozicio;

/**
 *
 * @author sebes
 */
public class Sugar extends Objektum {

    private final Pozicio[] pontok;
    
    
    public Sugar(Pozicio[] pontok) {
        super(0, 0);
        this.pontok = pontok;
    }

    @Override
    public void rajzol(Graphics g) {
        g.setColor(Color.RED);
        for (int i = 0; i < pontok.length-1; i++) {
            g.drawLine((int) pontok[i].getX(), (int) pontok[i].getY(), (int) pontok[i+1].getX(), (int) pontok[i+1].getY());   
        }
        g.setColor(Color.BLACK);
    }

}
