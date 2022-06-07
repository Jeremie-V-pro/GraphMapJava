package saegraphmap.window;

import java.awt.*;

public class RoundJToggleButton {
   private int x,y;
   private final int taillePts = 24;
   private int etatBtn = 0;
   private Color couleurPts;
   private boolean visible = true;


    public RoundJToggleButton(int x, int y, char typeVille) {
        this.x = x;
        this.y = y;
        switch (typeVille) {
            case 'V' : this.couleurPts = new Color(92, 92, 92);break;
            case 'R' : this.couleurPts = new Color(73, 38, 24);break;
            case 'L' : this.couleurPts = new Color(230, 168, 0);break;
        }
    }

    public boolean isVisible() {
        return visible;
    }
    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getTaillePts() {
        return taillePts;
    }

    public Color getCouleurPts() {
        return couleurPts;
    }

    public int isSelected() {
        return etatBtn;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setEtatBtn(int etatBtn) {
        this.etatBtn = etatBtn;
    }
}
