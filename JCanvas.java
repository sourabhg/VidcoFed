import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class JCanvas extends JComponent implements MouseListener, FocusListener {

    boolean error = true;

    JCanvas(Image i) {

	this.setPreferredSize(new Dimension(500, 400));
	this.setBorder(new LineBorder(Color.LIGHT_GRAY, 2));
	img = i;
	repaint();
    }

    public void update(Graphics g) {

	paint(g);
    }

    void setImage(Image i) {

	this.img = i;
	repaint();
    }

    public void paintComponent(Graphics g) {

	super.paintComponent(g);
	g.drawImage(img, 10, 10, 480, 360, null);
    }

    /*
         * public void paint(Graphics g) { g.drawImage(img, 10, 10, 480, 360,
         * null); }
         */
    public boolean imageUpdate(Image img, int flags, int x, int y, int W, int h) {

	if ((flags & ALLBITS) != 0) {
	    repaint();
	} else if ((flags & ABORT) != 0) {
	    error = true;
	    repaint();
	}
	return (flags & (ALLBITS | ABORT | ERROR)) == 0;
    }


    public void mouseClicked(MouseEvent arg0) {

	// TODO Auto-generated method stub
    }

    public void mouseEntered(MouseEvent arg0) {

	// TODO Auto-generated method stub
    }

    public void mouseExited(MouseEvent arg0) {

	// TODO Auto-generated method stub
    }

    public void mousePressed(MouseEvent arg0) {

	// TODO Auto-generated method stub
    }

    public void mouseReleased(MouseEvent arg0) {

	// TODO Auto-generated method stub
    }

    public void focusGained(FocusEvent arg0) {

    }

    public void focusLost(FocusEvent arg0) {

    }
    private Image img;
}