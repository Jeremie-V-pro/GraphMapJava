/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package saegraphmap.window;

import saegraphmap.linkedlist.TLieu;
import saegraphmap.linkedlist.TListe;
import saegraphmap.linkedlist.TRoute;

import java.awt.*;
import java.util.ArrayList;

/**
 *
 * @author neo
 */
public class GraphPanel extends javax.swing.JPanel {

    /**
     * Creates new form graphPanel
     */
    public GraphPanel() {
        this.listPts = new TListe("src/saegraphmap/data/SAE_graph.csv");
        initComponents();
        TLieu lieu = listPts.getListe();
        while(lieu != null)
        {
            this.add(lieu.getrJTogBtn());       
            lieu = lieu.getSuivant();
        }
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
            try{
                Thread.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        lieu =listPts.getListe();
        System.out.println("test 1");
        while(lieu !=null){
            if     (lieu.getX()<minX) minX = lieu.getX();
            else if(lieu.getX()>maxX) maxX = lieu.getX();
            if     (lieu.getY()<minY) minY = lieu.getY();
            else if(lieu.getY()>maxY) maxY = lieu.getY();
            lieu = lieu.getSuivant();
        }
        lieu =listPts.getListe();
        System.out.println("test 2");
        while(lieu !=null){
            lieu.setX(lieu.getX() - minX + 50 );
            lieu.setY(lieu.getY() - minY + 50 );
            lieu = lieu.getSuivant();
        }
        System.out.println("test 3");
        if(maxX - minX > this.getWidth()) this.setSize((int) (maxX-minX+50), this.getHeight());
        if(maxY - minY > this.getHeight()) this.setSize(this.getWidth(), (int) (maxY-minY+50));
        System.out.println("test 4");
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
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(java.awt.RenderingHints.KEY_ANTIALIASING, java.awt.RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(Color.white);
        g2d.fillRect(0,0,this.getWidth(),this.getHeight());
        TRoute route;
        TLieu lieu = listPts.getListe();
        while (lieu != null){
            route = lieu.getTetelisteroutes();
            while (route != null){
                if(route.getTypeRoute() == 'A' && afficheAutoroute) {
                    g2d.setColor(Color.blue);
                    g2d.drawLine((int)route.getLieuRejoint1().getX() + 7, (int)route.getLieuRejoint1().getY() +7, (int)route.getLieuRejoint2().getX() +7, (int)route.getLieuRejoint2().getY() +7);
                }
                else if(route.getTypeRoute() == 'N' && afficheNationale){
                    g2d.setColor(Color.red);
                    g2d.drawLine((int)route.getLieuRejoint1().getX() + 7, (int)route.getLieuRejoint1().getY() +7, (int)route.getLieuRejoint2().getX() +7, (int)route.getLieuRejoint2().getY() +7);
                }
                else if(route.getTypeRoute() == 'D' && afficheDepartemental){
                    g2d.setColor(Color.green);
                    g2d.drawLine((int)route.getLieuRejoint1().getX() + 7, (int)route.getLieuRejoint1().getY() +7, (int)route.getLieuRejoint2().getX() +7, (int)route.getLieuRejoint2().getY() +7);
                }

                route = route.getSuivant();
            }
            lieu = lieu.getSuivant();
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

    private final double longueurVisee = 50;
    private final TListe listPts;

    private boolean afficheAutoroute = true;
    private boolean afficheNationale =true;
    private boolean afficheDepartemental = true;
}
