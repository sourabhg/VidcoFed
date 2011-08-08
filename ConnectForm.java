import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.JOptionPane;

public class ConnectForm extends JFrame {

    private JLabel jLabel1;
    private JTextField IPaddress;
    private JButton conOKbutton;
    private JButton Cancbutton;
    private JPanel contentPane;
    private VideoTransmit vt;
  
    private boolean okbuttonclick;
    
    public ConnectForm() {

	super();
	initializeComponent();
	this.setVisible(false);
    }

    private void initializeComponent() {

	jLabel1 = new JLabel();
	jLabel1.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
	IPaddress = new JTextField();
	conOKbutton = new JButton();
	Cancbutton = new JButton();
	contentPane = (JPanel) this.getContentPane();
	jLabel1.setText("Enter The IP Address of The Remote Host");
	IPaddress.setBackground(new Color(252, 249, 249));
	IPaddress.addActionListener(new ActionListener() {

	    public void actionPerformed(ActionEvent e) {

		IPaddress_actionPerformed(e);
	    }
	});
	conOKbutton.setText("OK");
	conOKbutton.addActionListener(new ActionListener() {

	    public void actionPerformed(ActionEvent e) {

		conOKbutton_actionPerformed(e);
	    }
	});
	Cancbutton.setText("Cancel");
	Cancbutton.addActionListener(new ActionListener() {

	    public void actionPerformed(ActionEvent e) {

		Cancbutton_actionPerformed(e);
	    }
	});
	contentPane.setLayout(null);
	contentPane.setBorder(BorderFactory.createRaisedBevelBorder());
	contentPane.setBackground(new Color(196, 222, 213));
	addComponent(contentPane, jLabel1, 89, 16, 236, 18);
	addComponent(contentPane, IPaddress, 82, 47, 234, 22);
	addComponent(contentPane, conOKbutton, 106, 86, 83, 28);
	addComponent(contentPane, Cancbutton, 206, 86, 83, 28);
	this.setTitle("Connecting to the Remote Host........");
	this.setLocation(new Point(200, 150));
	this.setSize(new Dimension(427, 154));
	this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	this.setResizable(false);
    }

    private void addComponent(Container container, Component c, int x, int y,
	    int width, int height) {

	c.setBounds(x, y, width, height);
	container.add(c);
    }

    private void IPaddress_actionPerformed(ActionEvent e) {

    }

    private void conOKbutton_actionPerformed(ActionEvent e) {
	this.dispose();
	String str="Connection Established with the Remote Host";
        JOptionPane.showMessageDialog(null, "Message","Connection Established", JOptionPane.INFORMATION_MESSAGE); 
        
	 }

    private void Cancbutton_actionPerformed(ActionEvent e) {

	this.dispose();
    }

    // ============================= Testing
    // ================================//
    // = =//
    // = The following main method is just for testing this class you
    // built.=//
    // = After testing,you may simply delete it. =//
    // ======================================================================//
    public static void main(String[] args) {

	JFrame.setDefaultLookAndFeelDecorated(true);
	JDialog.setDefaultLookAndFeelDecorated(true);
	try {
	    UIManager
		    .setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
	} catch (Exception ex) {
	    System.out.println("Failed loading L&F: ");
	    System.out.println(ex);
	}
	new ConnectForm();
    }
    // = End of Testing =
}
