/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package saegraphmap.window;

import saegraphmap.linkedlist.TLieu;
import saegraphmap.linkedlist.TListe;
import saegraphmap.linkedlist.TRoute;
import saegraphmap.window.listener.GraphPanelListener;

import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author neo
 */
public class GraphPanel extends javax.swing.JPanel implements MouseListener,MouseMotionListener {


    public GraphPanel() {
        initComponents();
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
    }
    /**
     * Creates new form graphPanel
     */
    public GraphPanel(TListe listPts) {
        this.listPts = listPts;
        initComponents();
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        generationGraph();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setLayout(null);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

    //https://www1.pub.informatik.uni-wuerzburg.de/demos/forceDirected.html
    public void generationGraph(){
        double nbIterrationMax=1000 , iterration = 1, maxForce = 1000, force;
        double temp = 1;
        double minX=Integer.MAX_VALUE , minY=Integer.MAX_VALUE, maxX=Integer.MIN_VALUE , maxY = Integer.MIN_VALUE;
        TLieu lieu;
        TLieu lieu2;
        TRoute route;
        double epsilon = 0.1;
        while (iterration < nbIterrationMax && maxForce > epsilon){
            lieu = listPts.getListe();
            maxForce = 0;
            iterration++;
            while (lieu != null){
                lieu.setFx(0);
                lieu.setFy(0);
                lieu2 = listPts.getListe();
                while (lieu2 != null){
                    if(lieu2 != lieu){
                        lieu.setFx( lieu.getFx() +  (forceRep(lieu, lieu2) * normX(lieu2, lieu)));
                        lieu.setFy( lieu.getFy() +  (forceRep(lieu, lieu2) * normY(lieu2, lieu)));
                    }
                    lieu2 = lieu2.getSuivant();
                }
                lieu = lieu.getSuivant();
            }
            lieu = listPts.getListe();
            while (lieu != null){
                route = lieu.getTetelisteroutes();
                while (route != null){
                    lieu.setFx( lieu.getFx() +  (forceAttr(route.getLieuRejoint1(), route.getLieuRejoint2()) * normX(route.getLieuRejoint1(), route.getLieuRejoint2())));
                    lieu.setFy( lieu.getFy() +  (forceAttr(route.getLieuRejoint1(), route.getLieuRejoint2()) * normY(route.getLieuRejoint1(), route.getLieuRejoint2())));

                    route = route.getSuivant();
                }
                lieu = lieu.getSuivant();
            }

            lieu = listPts.getListe();
            while (lieu != null){
                force =  vecLength(lieu.getFx(), lieu.getFy());

                if (force > 0){
                    lieu.setFx(applyTemp(lieu.getFx(), force, temp));
                    lieu.setFy(applyTemp(lieu.getFy(), force, temp));
                }

                force = vecLength(lieu.getFx(), lieu.getFy());
                maxForce = Math.max(maxForce, force);
                lieu.setX(lieu.getX() + lieu.getFx());
                lieu.setY(lieu.getY() + lieu.getFy());
                lieu = lieu.getSuivant();
            }
            temp = cool(temp);
            this.repaint();

        }
        lieu =listPts.getListe();
        while(lieu !=null){
            if     (lieu.getX()<minX) minX = lieu.getX();
            else if(lieu.getX()>maxX) maxX = lieu.getX();
            if     (lieu.getY()<minY) minY = lieu.getY();
            else if(lieu.getY()>maxY) maxY = lieu.getY();
            lieu = lieu.getSuivant();
        }
        lieu =listPts.getListe();
        while(lieu !=null){
            lieu.setX(lieu.getX() - minX + 50 );
            lieu.setY(lieu.getY() - minY + 50 );
            lieu = lieu.getSuivant();
        }
        this.setPreferredSize(new Dimension((int) (maxX-minX+100), (int) (maxY-minY+100)));
    }

    private double forceRep(TLieu lieu1, TLieu lieu2){
        return (Math.pow(this.longueurVisee,2) / Math.max(this.dist(lieu1, lieu2),0.01));
    }

    private double forceAttr(TLieu lieu1, TLieu lieu2){
        return  Math.pow(this.dist(lieu1, lieu2), 2) /  longueurVisee;
    }

    private double dist(TLieu lieu1, TLieu lieu2){
        return vecLength(lieu2.getX() - lieu1.getX(), lieu2.getY() - lieu1.getY());
    }

    private double vecLength(double x , double y){
        double ssum = Math.pow(x,2) + Math.pow(y,2);
        return Math.sqrt(ssum);
    }

    private double normX(TLieu lieu1, TLieu lieu2){
        if(dist(lieu1, lieu2) == 0){
            return 0;
        }
        return (lieu2.getX() - lieu1.getX()) / this.dist(lieu1, lieu2);
    }

    private double normY(TLieu lieu1, TLieu lieu2){
        if(dist(lieu1, lieu2) == 0){
            return 0;
        }
        return (lieu2.getY() - lieu1.getY()) / this.dist(lieu1, lieu2);
    }

    private double applyTemp(double dir, double force, double temp){
        return ( dir /  force * Math.min(force, temp * this.longueurVisee * 2));
    }

    private double cool(double temp){
        double delta = 0.975;
        return delta * temp;
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(java.awt.RenderingHints.KEY_ANTIALIASING, java.awt.RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(Color.white);
        g2d.fillRect(0,0,this.getWidth(),this.getHeight());
        if(listPts!=null){
            for(TLieu lieu = listPts.getListe(); lieu != null; lieu =lieu.getSuivant()){
                for(TRoute route = lieu.getTetelisteroutes(); route != null; route = route.getSuivant()){
                    if(route.getTypeRoute() == 'A' && afficheAutoroute && route.getLieuRejoint1().getrJTogBtn().isVisible() && route.getLieuRejoint2().getrJTogBtn().isVisible()) {
                        g2d.setColor(Color.blue);
                        g2d.drawLine((int)route.getLieuRejoint1().getX(), (int)route.getLieuRejoint1().getY(), (int)route.getLieuRejoint2().getX(), (int)route.getLieuRejoint2().getY());
                    }
                    else if(route.getTypeRoute() == 'N' && afficheNationale && route.getLieuRejoint1().getrJTogBtn().isVisible() && route.getLieuRejoint2().getrJTogBtn().isVisible()){
                        g2d.setColor(Color.red);
                        g2d.drawLine((int)route.getLieuRejoint1().getX(), (int)route.getLieuRejoint1().getY(), (int)route.getLieuRejoint2().getX(), (int)route.getLieuRejoint2().getY());
                    }
                    else if(route.getTypeRoute() == 'D' && afficheDepartemental && route.getLieuRejoint1().getrJTogBtn().isVisible() && route.getLieuRejoint2().getrJTogBtn().isVisible()){
                        g2d.setColor(Color.green);
                        g2d.drawLine((int)route.getLieuRejoint1().getX(), (int)route.getLieuRejoint1().getY(), (int)route.getLieuRejoint2().getX(), (int)route.getLieuRejoint2().getY());
                    }
                }
            }

            for(TLieu lieu = listPts.getListe(); lieu != null; lieu = lieu.getSuivant()){
                if(lieu.getrJTogBtn().isVisible()){
                    if(lieu.getrJTogBtn().isSelected() == 1){
                        g2d.setColor(Color.BLACK);
                        g2d.fillOval(lieu.getrJTogBtn().getX() - lieu.getrJTogBtn().getTaillePts()/2 -2 ,lieu.getrJTogBtn().getY() -lieu.getrJTogBtn().getTaillePts()/2 -2 ,lieu.getrJTogBtn().getTaillePts()+4,lieu.getrJTogBtn().getTaillePts()+4);
                    }
                    if(lieu.getrJTogBtn().isSelected() == 2){
                        g2d.setColor(new Color(94, 3, 252));
                        g2d.fillOval(lieu.getrJTogBtn().getX() - lieu.getrJTogBtn().getTaillePts()/2 -2 ,lieu.getrJTogBtn().getY() -lieu.getrJTogBtn().getTaillePts()/2 -2 ,lieu.getrJTogBtn().getTaillePts()+4,lieu.getrJTogBtn().getTaillePts()+4);
                    }
                    g2d.setColor(lieu.getrJTogBtn().getCouleurPts());
                    g2d.fillOval(lieu.getrJTogBtn().getX() - lieu.getrJTogBtn().getTaillePts()/2  ,lieu.getrJTogBtn().getY() -lieu.getrJTogBtn().getTaillePts()/2 ,lieu.getrJTogBtn().getTaillePts(),lieu.getrJTogBtn().getTaillePts());
                }
            }
        }
    }

    public void setAfficheAutoroute(boolean afficheAutoroute) {
        this.afficheAutoroute = afficheAutoroute;
    }

    public void setAfficheNationale(boolean fficheNationale) {
        this.afficheNationale = fficheNationale;
    }

    public void setAfficheDepartemental(boolean afficheDepartemental) {
        this.afficheDepartemental = afficheDepartemental;
    }

    public void viderListPts(){
        this.listPts = null;
        repaint();
    }

    public void ajoutListePts(TListe liste){
        this.listPts = liste;
        generationGraph();
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        TLieu lieu = listPts.getListe();
        TLieu lieuChangeEtatBtn = listPts.getListe();
        while (lieu != null){
            if(Math.sqrt(Math.pow(lieu.getrJTogBtn().getX()-mouseEvent.getX(),2) +Math.pow(lieu.getrJTogBtn().getY()-mouseEvent.getY(),2)) < lieu.getrJTogBtn().getTaillePts()/2 && lieu.getrJTogBtn().isVisible()){

                if(mouseEvent.isShiftDown()){
                    if(lieu.getrJTogBtn().isSelected() != 2) lieu.getrJTogBtn().setEtatBtn(2);
                    else lieu.getrJTogBtn().setEtatBtn(0);
                    while (lieuChangeEtatBtn != null){
                        if(!(lieuChangeEtatBtn.equals(lieu) || lieuChangeEtatBtn.getrJTogBtn().isSelected() == 1)) lieuChangeEtatBtn.getrJTogBtn().setEtatBtn(0);
                        lieuChangeEtatBtn = lieuChangeEtatBtn.getSuivant();
                    }
                }
                else {
                    if(lieu.getrJTogBtn().isSelected() != 1) lieu.getrJTogBtn().setEtatBtn(1);
                    else lieu.getrJTogBtn().setEtatBtn(0);
                    while (lieuChangeEtatBtn != null){
                        if(!(lieuChangeEtatBtn.equals(lieu) || lieuChangeEtatBtn.getrJTogBtn().isSelected() == 2)) lieuChangeEtatBtn.getrJTogBtn().setEtatBtn(0);
                        lieuChangeEtatBtn = lieuChangeEtatBtn.getSuivant();
                    }
                }

                this.repaint();
                lieuChangeEtatBtn = listPts.getListe();
                ArrayList<TLieu> lieuEvent = new ArrayList<TLieu>();
                while (lieuChangeEtatBtn != null)
                {
                    if (lieuChangeEtatBtn.getrJTogBtn().isSelected() ==1){
                        lieuEvent.add(0,lieuChangeEtatBtn);
                    }
                    else  if (lieuChangeEtatBtn.getrJTogBtn().isSelected() == 2){
                        lieuEvent.add(lieuChangeEtatBtn);
                    }
                    lieuChangeEtatBtn = lieuChangeEtatBtn.getSuivant();
                }
                for (GraphPanelListener listener : listeners) {
                    listener.lieuSelectedChanged(lieuEvent);
                }
                break;
            }
            lieu = lieu.getSuivant();
        }
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseDragged(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {
        if(listPts!=null){
            TLieu lieu = listPts.getListe();
            while (lieu != null){
                if(Math.sqrt(Math.pow(lieu.getrJTogBtn().getX()-mouseEvent.getX(),2) +Math.pow(lieu.getrJTogBtn().getY()-mouseEvent.getY(),2)) < lieu.getrJTogBtn().getTaillePts()/2 && lieu.getrJTogBtn().isVisible()){
                    this.setToolTipText(lieu.getNomLieu());
                    return;
                }
                lieu = lieu.getSuivant();
            }
            this.setToolTipText(null);
        }
    }

    public void addGraphPanelListener(GraphPanelListener listener){
        this.listeners.add(listener);
    }
    private final double longueurVisee = 50;
    private TListe listPts;
    private boolean afficheAutoroute = true;
    private boolean afficheNationale =true;

    private boolean afficheDepartemental = true;
    
    private List<GraphPanelListener> listeners = new ArrayList<>();
}
