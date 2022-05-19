package saegraphmap.window;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;

public class RoundJToggleButton extends JToggleButton {
    public RoundJToggleButton(){
        setContentAreaFilled(false);
    }
    @Override
    protected void paintComponent(Graphics g)
    {
        if (this.isSelected()) {
            g.setColor(new Color(5,5,100));

        } else {
            g.setColor(new Color(10,10,150));
        }
        g.fillOval(0, 0, getSize().width-1,getSize().height-1);

        super.paintComponent(g);
    }



    @Override
    protected void paintBorder(Graphics g) {
        g.setColor(Color.red);
        g.drawOval(0, 0, getSize().width-1,getSize().height-1);
    }

    private Shape shape;
    @Override
    public boolean contains(int x, int y) {
        if (shape == null ||
                !shape.getBounds().equals(getBounds())) {
            shape = new Ellipse2D.Float(0, 0, getWidth(), getHeight());
        }
        return shape.contains(x, y);
    }
}
