/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package saegraphmap.window;

import saegraphmap.linkedlist.TLieu;
import saegraphmap.linkedlist.TListe;
import saegraphmap.linkedlist.TRoute;

import java.awt.*;

/**
 *
 * @author neo
 */
public class GraphPanel extends javax.swing.JPanel {

    /**
     * Creates new form graphPanel
     */
    public GraphPanel() {

        initComponents();
        RoundJToggleButton jtbtn = new RoundJToggleButton();
        this.add(jtbtn);
        jtbtn.setBounds(10, 10, 100, 100);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setLayout(null);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables


    public void generationGraph(TListe listPts){
        int fx = 0, fy = 0, longueurVisee = 35, longueurMinStop = 40, nbIterrationMax=500 , iterration = 0;
        TLieu lieu = null;
        TRoute route = null;
        lieu = listPts.getListe();

//https://www1.pub.informatik.uni-wuerzburg.de/demos/forceDirected.html
        while (iterration < nbIterrationMax && longueurMax() > longueurMinStop){
            while ( lieu != null){
                route = lieu.getTetelisteroutes();
                while (route !=null){
                    route = route.getSuivant();
                }
                fx = 0;
                fy = 0;
                lieu = lieu.getSuivant();
            }
        }
    }

    private int longueurMax(){
        return 1;
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setColor(Color.white);
        g2d.fillRect(0,0,this.getWidth(),this.getHeight());
    }
}
