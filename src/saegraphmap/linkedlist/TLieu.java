package saegraphmap.linkedlist;

import saegraphmap.window.RoundJToggleButton;

import java.util.ArrayList;
import java.util.Objects;

public class TLieu {
    public String nomLieu;
    private final char type; //V (ville), R (restaurant) ou L (lieu de loisir)
    private TLieu suivant;
    private TRoute tetelisteroutes;

    private RoundJToggleButton rJTogBtn;

    private double fx = 0, fy=0;

    private double x ,y;

    public TLieu(String nomLieu, char type, TRoute tetelisteroutes) {
        this.nomLieu = nomLieu;
        this.type = type;
        this.tetelisteroutes = tetelisteroutes;
        this.x = (int) Math.floor(Math.random()*(1000+1)+0);
        this.y = (int) Math.floor(Math.random()*(1000+1)+0);
        this.rJTogBtn= new RoundJToggleButton((int)this.x, (int)this.y, this.type);
    }

    public TLieu() {
        this.nomLieu = null;
        this.type = 'z';
        this.suivant = null;
        this.tetelisteroutes = null;
    }

    public String getNomLieu() {
        return nomLieu;
    }

    public char getType() {
        return type;
    }

    public TLieu getSuivant() {
        return suivant;
    }

    public TRoute getTetelisteroutes() {
        return tetelisteroutes;
    }

    public void setSuivant(TLieu suivant) {
        this.suivant = suivant;
    }


    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }

    public void setX(double x) {
        this.x = x;
        this.rJTogBtn.setX((int) x);
    }

    public void setY(double y) {
        this.y = y;
        this.rJTogBtn.setY((int) y);
    }

    public RoundJToggleButton getrJTogBtn() {
        return rJTogBtn;
    }

    public double getFx() {
        return fx;
    }

    public double getFy() {
        return fy;
    }

    public void setFx(double fxt) {
        this.fx = fxt;
    }

    public void setFy(double fyt) {
        this.fy = fyt;
    }

    public ArrayList<TLieu> unDistance() {
        ArrayList<TLieu> listUnDistance = new ArrayList<>();
        for(TRoute route = this.getTetelisteroutes(); route != null; route= route.getSuivant()){
            listUnDistance.add(route.getLieuRejoint2());
        }
        return listUnDistance;
    }

    public void ajoutRoute(TRoute routeAjoute){
        TRoute celluleRoute = this.tetelisteroutes;
        if (celluleRoute == null){
            this.tetelisteroutes = routeAjoute;
        }
        else{
            while (celluleRoute.getSuivant() != null){
                celluleRoute = celluleRoute.getSuivant();
            }
            celluleRoute.setSuivant(routeAjoute);
        }

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TLieu)) return false;
        TLieu tlieu = (TLieu) o;
        return type == tlieu.type && Objects.equals(nomLieu, tlieu.nomLieu);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nomLieu, type);
    }
}



