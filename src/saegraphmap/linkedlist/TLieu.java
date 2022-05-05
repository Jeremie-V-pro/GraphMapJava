package saegraphmap.linkedlist;

public class TLieu {
    private String nomLieu;
    private char type; //V (ville), R (restaurant) ou L (lieu de loisir)
    private TLieu precedent;
    private TLieu suivant;
    private TRoute tetelisteroutes;

    public TLieu(String nomLieu, char type, TLieu suivant, TLieu precedent, TRoute tetelisteroutes) {
        this.nomLieu = nomLieu;
        this.type = type;
        this.suivant = suivant;
        this.precedent = precedent;
        this.tetelisteroutes = tetelisteroutes;
    }

    public TLieu() {
        this.nomLieu = null;
        this.type = 'z';
        this.suivant = null;
        this.precedent = null;
        this.tetelisteroutes = null;
    }

    public String getNomLieu() {
        return nomLieu;
    }

    public char getType() {
        return type;
    }

    public TLieu getPrecedent() {
        return precedent;
    }

    public TLieu getSuivant() {
        return suivant;
    }

    public TRoute getTetelisteroutes() {
        return tetelisteroutes;
    }


}
