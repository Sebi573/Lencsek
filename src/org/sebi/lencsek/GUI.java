/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package org.sebi.lencsek;

import java.awt.BorderLayout;
import org.sebi.components.FokuszPont;
import org.sebi.components.Kep;
import org.sebi.misc.Canvas;
import org.sebi.components.Lencse;
import org.sebi.components.Sugar;
import org.sebi.components.Targy;
import org.sebi.components.Tengely;
import org.sebi.misc.Objektum;
import org.sebi.misc.Pozicio;

/**
 *
 * @author sebes
 */
public class GUI extends javax.swing.JFrame {

    private final Canvas vaszon = new Canvas();

    private Lencse l;
    private Targy t;
    private Kep k;

    private double fokusztavolsag = 4.0;
    private double targyX = -8.0;

    private FokuszPont f;
    private FokuszPont f2;
    private FokuszPont f3;
    private FokuszPont f4;

    /**
     * Creates new form GUI
     */
    public GUI() {
        initComponents();
        this.add(vaszon, BorderLayout.CENTER);
        rajzolTengelyek();
        rajzolLencse("gyujto");
        rajzolFokuszPont();
        rajzolTargy();
        rajzolKep();
    }

    /**
     * X és Y tengelyek kirajzolása
     */
    private void rajzolTengelyek() {
        Tengely tengely = new Tengely(0, 0);
        vaszon.addObjektum(tengely);
    }

    /**
     * Lencse kirajzolása a típusnak megfelelően
     *
     * @param tipus
     */
    private void rajzolLencse(String tipus) {
        vaszon.torolLencse();
        l = new Lencse(tipus, 0, 0, (vaszon.getWidth()), (vaszon.getHeight()));
        vaszon.addObjektum(l);
    }

    /**
     * Tárgy kirajzolása az X tengelyen megadott helyen
     */
    private void rajzolTargy() {
        vaszon.torolTargy();
        t = new Targy(targyX, 0, (double) jSpinnerTargyMagassag.getValue());
        vaszon.addObjektum(t);
    }

    /**
     * A kép kirajzolása a megadott adatok alapján
     */
    private void rajzolKep() {
        vaszon.torolKep();
        k = new Kep(0, 0, getKepAllas(), getKepTipus(), t.getHeight(), Math.abs(t.getT()), f.getF());
        vaszon.addObjektum(k);
    }

    /**
     * Egyszeres és kétszeres fókuszpontok kirajzolása
     */
    private void rajzolFokuszPont() {
        vaszon.torolFokuszPontok();
        f = new FokuszPont(0, 0, fokusztavolsag, "F");
        f2 = new FokuszPont(0, 0, -1 * fokusztavolsag, "F");
        f3 = new FokuszPont(0, 0, 2 * fokusztavolsag, "2F");
        f4 = new FokuszPont(0, 0, -2 * fokusztavolsag, "2F");
        vaszon.addObjektum(f);
        vaszon.addObjektum(f2);
        vaszon.addObjektum(f3);
        vaszon.addObjektum(f4);
    }

    /**
     * A kiválasztott lencsetípus
     *
     * @return String
     */
    private String getLencseTipus() {
        return jRadioButtonGyujto.isSelected() ? "gyujto" : "szoro";
    }

    private double getFokusz() {
        return f.getF();
    }

    private double getTargyTavolsag() {
        return t.getT();
    }

    /**
     * Megadja a keletkező kép típusát.
     *
     * @return
     */
    private String getKepTipus() {
        return l.getTipus().equals("gyujto") ? (getTargyTavolsag() < getFokusz() ? "latszolagos" : "valodi") : "latszolagos";
    }

    /**
     * Visszaadja a vászon közepének pozícióját.
     *
     * @return
     */
    private Pozicio getOrigo() {
        return new Pozicio(Objektum.getVaszonSzelesseg() / 2, Objektum.getVaszonMagassag() / 2);
    }
    
    /**
     * A kép típusától függően kirajzolja a beérkező fénysugarakat.
     */
    private void rajzolSugarak() {
        if (getLencseTipus().equals("szoro") && getKepTipus().equals("latszolagos") && k.getF() > 0) {
            k.setF(k.getF() * -1);
        }
        if (getKepTipus().equals("latszolagos")) {
            try {
                vaszon.torolSugarak();
                Sugar s1;
                Sugar s3 = null;
                Sugar s4 = null;
                Sugar s5 = null;
                Sugar s6 = null;
                Sugar s7 = null;

                if (getLencseTipus().equals("szoro")) {
                    Pozicio[] pontok1 = new Pozicio[]{t.getLocation(), new Pozicio(getOrigo().getX(), t.getLocation().getY())};
                    s1 = new Sugar(pontok1);

                    Pozicio[] pontok3 = new Pozicio[]{new Pozicio(getOrigo().getX(), k.getLocation().getY()), new Pozicio(0, k.getLocation().getY())};
                    s3 = new Sugar(pontok3, true);

                    Pozicio[] pontok4 = new Pozicio[]{new Pozicio(getOrigo().getX(), t.getLocation().getY()), new Pozicio(getOrigo().getX() - getFokusz(), getOrigo().getY())};
                    s4 = new Sugar(pontok4, true);

                    Pozicio[] pontok5 = new Pozicio[]{t.getLocation(), getOrigo()};
                    s5 = new Sugar(pontok5, true);

                } else {                    
                    Pozicio[] pontok1 = new Pozicio[]{t.getLocation(), new Pozicio(getOrigo().getX(), t.getLocation().getY()), new Pozicio(Objektum.getVaszonSzelesseg() / 2 + f.getF(), Objektum.getVaszonMagassag() / 2), new Pozicio(Objektum.getVaszonSzelesseg() / 2 + f.getF(), Objektum.getVaszonMagassag() / 2)};
                    s1 = new Sugar(pontok1);
                    
                    Pozicio[] pontok5 = new Pozicio[]{new Pozicio(getOrigo().getX(), t.getLocation().getY()), k.getLocation()};
                    s6 = new Sugar(pontok5, true);
                    
                    Pozicio[] pontok6 = new Pozicio[]{k.getLocation(), new Pozicio(getOrigo().getX(), k.getLocation().getY())};
                    s7 = new Sugar(pontok6, true);
                }

                Pozicio[] pontok2 = new Pozicio[]{t.getLocation(), new Pozicio(getOrigo().getX(), k.getLocation().getY()), new Pozicio(getOrigo().getX(), k.getLocation().getY())};
                Sugar s2 = new Sugar(pontok2);

                vaszon.addObjektum(s1);
                vaszon.addObjektum(s2);
                if (s3 != null && s4 != null && s5 != null) {
                    vaszon.addObjektum(s3);
                    vaszon.addObjektum(s4);
                    vaszon.addObjektum(s5);
                }
                if(s6 != null && s7 != null) {
                    vaszon.addObjektum(s6);
                    vaszon.addObjektum(s7);
                }
                
            } catch (Exception e) {
            }

        } else {
            try {
                vaszon.torolSugarak();
                Pozicio[] pontok1 = new Pozicio[]{t.getLocation(), new Pozicio(getOrigo().getX(), t.getLocation().getY()), k.getLocation()};
                Sugar s1 = new Sugar(pontok1);

                Pozicio[] pontok2 = new Pozicio[]{t.getLocation(), k.getLocation()};
                Sugar s2 = new Sugar(pontok2);

                Pozicio[] pontok3 = new Pozicio[]{t.getLocation(), new Pozicio(getOrigo().getX(), k.getLocation().getY()), k.getLocation()};
                Sugar s3 = new Sugar(pontok3);

                vaszon.addObjektum(s1);
                vaszon.addObjektum(s2);
                vaszon.addObjektum(s3);
            } catch (Exception e) {
            }
        }

    }

    /**
     * Megadja a keletkező kép állását, amely lehet fordított vagy megegyező
     * állású.
     *
     * @return
     */
    private String getKepAllas() {
        return getKepTipus().equalsIgnoreCase("valodi") ? "forditott" : "megegyezo";
    }

    /**
     * Megadja a keletkező kép nagyítását.
     *
     * @return
     */
    private String getKepNagyitas() {
        return k.getNagyitas() >= 1 ? "nagyitott" : "kicsinyitett";
    }

    /**
     * Megjeleníti a kép jellemzőit egy TextArea komponensben.
     */
    private void kiirJellemzes() {
        jTextAreaKepJellemzo.setText("%s\n%s\n%s".formatted(getKepTipus(), getKepAllas(), getKepNagyitas()));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        buttonGroupLencseTipus = new javax.swing.ButtonGroup();
        jPanelBeallitasok = new javax.swing.JPanel();
        jLabelTargytavolsag = new javax.swing.JLabel();
        jSpinnerTargyTavolsag = new javax.swing.JSpinner();
        jLabelFokusztavolsag = new javax.swing.JLabel();
        jSpinnerFokuszTavolsag = new javax.swing.JSpinner();
        jLabelTargymagassag = new javax.swing.JLabel();
        jSpinnerTargyMagassag = new javax.swing.JSpinner();
        jRadioButtonSzoro = new javax.swing.JRadioButton();
        jRadioButtonGyujto = new javax.swing.JRadioButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaKepJellemzo = new javax.swing.JTextArea();
        jLabelKepJellemzes = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Lencsék");
        setMinimumSize(new java.awt.Dimension(489, 400));
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                formComponentResized(evt);
            }
        });

        jPanelBeallitasok.setBorder(javax.swing.BorderFactory.createTitledBorder("Beállítások"));
        jPanelBeallitasok.setLayout(new java.awt.GridBagLayout());

        jLabelTargytavolsag.setText("Tárgytávolság");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanelBeallitasok.add(jLabelTargytavolsag, gridBagConstraints);

        jSpinnerTargyTavolsag.setModel(new javax.swing.SpinnerNumberModel(8.0d, 0.0d, null, 0.01d));
        jSpinnerTargyTavolsag.setMinimumSize(new java.awt.Dimension(80, 35));
        jSpinnerTargyTavolsag.setPreferredSize(new java.awt.Dimension(80, 35));
        jSpinnerTargyTavolsag.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSpinnerTargyTavolsagStateChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanelBeallitasok.add(jSpinnerTargyTavolsag, gridBagConstraints);

        jLabelFokusztavolsag.setText("Fókusztávolság");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanelBeallitasok.add(jLabelFokusztavolsag, gridBagConstraints);

        jSpinnerFokuszTavolsag.setModel(new javax.swing.SpinnerNumberModel(4.0d, null, null, 0.1d));
        jSpinnerFokuszTavolsag.setMinimumSize(new java.awt.Dimension(80, 35));
        jSpinnerFokuszTavolsag.setPreferredSize(new java.awt.Dimension(80, 35));
        jSpinnerFokuszTavolsag.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSpinnerFokuszTavolsagStateChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanelBeallitasok.add(jSpinnerFokuszTavolsag, gridBagConstraints);

        jLabelTargymagassag.setText("Tárgy magassága");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanelBeallitasok.add(jLabelTargymagassag, gridBagConstraints);

        jSpinnerTargyMagassag.setModel(new javax.swing.SpinnerNumberModel(2.5d, null, null, 0.1d));
        jSpinnerTargyMagassag.setMinimumSize(new java.awt.Dimension(80, 35));
        jSpinnerTargyMagassag.setPreferredSize(new java.awt.Dimension(80, 35));
        jSpinnerTargyMagassag.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSpinnerTargyMagassagStateChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanelBeallitasok.add(jSpinnerTargyMagassag, gridBagConstraints);

        buttonGroupLencseTipus.add(jRadioButtonSzoro);
        jRadioButtonSzoro.setText("Szórólencse");
        jRadioButtonSzoro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonSzoroActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.insets = new java.awt.Insets(8, 8, 8, 8);
        jPanelBeallitasok.add(jRadioButtonSzoro, gridBagConstraints);

        buttonGroupLencseTipus.add(jRadioButtonGyujto);
        jRadioButtonGyujto.setSelected(true);
        jRadioButtonGyujto.setText("Gyüjtőlencse");
        jRadioButtonGyujto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonGyujtoActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.insets = new java.awt.Insets(8, 8, 8, 8);
        jPanelBeallitasok.add(jRadioButtonGyujto, gridBagConstraints);

        jTextAreaKepJellemzo.setEditable(false);
        jTextAreaKepJellemzo.setColumns(20);
        jTextAreaKepJellemzo.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTextAreaKepJellemzo.setRows(5);
        jScrollPane1.setViewportView(jTextAreaKepJellemzo);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 6;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 0);
        jPanelBeallitasok.add(jScrollPane1, gridBagConstraints);

        jLabelKepJellemzes.setText("Kép jellemzése");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 0);
        jPanelBeallitasok.add(jLabelKepJellemzes, gridBagConstraints);

        getContentPane().add(jPanelBeallitasok, java.awt.BorderLayout.PAGE_END);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentResized
        if (vaszon.getWidth() != 0 && vaszon.getHeight() != 0) {
            Objektum.setVaszonMagassag(vaszon.getHeight());
            Objektum.setVaszonSzelesseg(vaszon.getWidth());
        }
        rajzolTengelyek();
        rajzolLencse(getLencseTipus());
        rajzolSugarak();
    }//GEN-LAST:event_formComponentResized

    private void jSpinnerTargyTavolsagStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSpinnerTargyTavolsagStateChanged
        targyX = (double) jSpinnerTargyTavolsag.getValue() * -1;
        if (getKepTipus().equals("latszolagos")) {
            k.setAllas("megegyezo");
        }
        rajzolTargy();
        rajzolKep();
        rajzolSugarak();
        kiirJellemzes();
    }//GEN-LAST:event_jSpinnerTargyTavolsagStateChanged

    private void jSpinnerTargyMagassagStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSpinnerTargyMagassagStateChanged
        rajzolTargy();
        rajzolKep();
        rajzolSugarak();
        if (getKepTipus().equals("latszolagos")) {
            k.setAllas("megegyezo");
        }
        kiirJellemzes();
    }//GEN-LAST:event_jSpinnerTargyMagassagStateChanged

    private void jRadioButtonGyujtoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonGyujtoActionPerformed
        if (getKepTipus().equals("latszolagos")) {
            k.setAllas("megegyezo");
        }
        rajzolLencse(getLencseTipus());
        rajzolTargy();
        rajzolKep();
        rajzolSugarak();
        kiirJellemzes();
    }//GEN-LAST:event_jRadioButtonGyujtoActionPerformed

    private void jRadioButtonSzoroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonSzoroActionPerformed
        k.setTipus("latszolagos");
        k.setAllas("megegyezo");
        rajzolLencse(getLencseTipus());
        rajzolTargy();
        rajzolKep();
        rajzolSugarak();
        kiirJellemzes();
    }//GEN-LAST:event_jRadioButtonSzoroActionPerformed

    private void jSpinnerFokuszTavolsagStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSpinnerFokuszTavolsagStateChanged
        this.fokusztavolsag = (double) jSpinnerFokuszTavolsag.getValue();
        rajzolFokuszPont();
        rajzolKep();
        rajzolTargy();
        rajzolSugarak();
        kiirJellemzes();
    }//GEN-LAST:event_jSpinnerFokuszTavolsagStateChanged

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new GUI().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroupLencseTipus;
    private javax.swing.JLabel jLabelFokusztavolsag;
    private javax.swing.JLabel jLabelKepJellemzes;
    private javax.swing.JLabel jLabelTargymagassag;
    private javax.swing.JLabel jLabelTargytavolsag;
    private javax.swing.JPanel jPanelBeallitasok;
    private javax.swing.JRadioButton jRadioButtonGyujto;
    private javax.swing.JRadioButton jRadioButtonSzoro;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSpinner jSpinnerFokuszTavolsag;
    private javax.swing.JSpinner jSpinnerTargyMagassag;
    private javax.swing.JSpinner jSpinnerTargyTavolsag;
    private javax.swing.JTextArea jTextAreaKepJellemzo;
    // End of variables declaration//GEN-END:variables
}
