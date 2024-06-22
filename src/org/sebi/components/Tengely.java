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
public class Tengely extends Objektum {
    
    
    public Tengely(double x, double y) {
        super(x, y);
    }

    @Override
    public void rajzol(Graphics g) {
        g.drawLine(0, vaszonMagassag / 2, vaszonSzelesseg, vaszonMagassag / 2);
        g.drawLine(vaszonSzelesseg / 2, 0, vaszonSzelesseg / 2, vaszonMagassag);
    }

}
