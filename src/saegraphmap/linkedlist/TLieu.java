package saegraphmap.linkedlist;

import saegraphmap.window.RoundJToggleButton;

import java.util.Objects;

public class TLieu {
    private String nomLieu;
    private char type; //V (ville), R (restaurant) ou L (lieu de loisir)
    private TLieu suivant;
    private TRoute tetelisteroutes;

    private RoundJToggleButton rJTogBtn;

    private int fx = 0, fy=0;

    public TLieu(String nomLieu, char type, TRoute tetelisteroutes) {
        this.nomLieu = nomLieu;
        this.type = type;
        this.tetelisteroutes = tetelisteroutes;
        this.rJTogBtn = new RoundJToggleButton();
        this.rJTogBtn.setBounds((int) Math.floor(Math.random()*(1000-0+1)+0),(int) Math.floor(Math.random()*(1000-0+1)+0),15,15);
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

     public void setNomLieu(String nomLieu) {
        this.nomLieu = nomLieu;
    }

    public void setType(char type) {
        this.type = type;
    }

    public void setSuivant(TLieu suivant) {
        this.suivant = suivant;
    }


    public int getX() {
        return this.rJTogBtn.getBounds().x;
    }

    public int getY() {
        return this.rJTogBtn.getBounds().y;
    }

    public void setX(int x) {
        this.rJTogBtn.setBounds(x,this.rJTogBtn.getBounds().y, 15, 15);
    }

    public void setY(int y) {
        this.rJTogBtn.setBounds(this.rJTogBtn.getBounds().y,y, 15, 15);
    }

    public RoundJToggleButton getrJTogBtn() {
        return rJTogBtn;
    }

    public int getFx() {
        return fx;
    }

    public int getFy() {
        return fy;
    }

    public void setFx(int fx) {
        this.fx = fx;
    }

    public void setFy(int fy) {
        this.fy = fy;
    }

    public void setTetelisteroutes(TRoute tetelisteroutes) {
        this.tetelisteroutes = tetelisteroutes;
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



