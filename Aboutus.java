

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Aboutus extends JFrame
{
	// Variables declaration
	private JLabel jLabel1;
	private JLabel jLabel2;
	private JLabel jLabel3;
	private JLabel jLabel4;
	private JLabel jLabel5;
	private JLabel jLabel6;
	private JLabel jLabel7;
	private JSeparator jSeparator1;
	private JSeparator jSeparator2;
	private JButton abok;
	private JPanel contentPane;
	

	public Aboutus()
	{
		super();
		initializeComponent();
	

		this.setVisible(true);
	}

	
	private void initializeComponent()
	{
		jLabel1 = new JLabel();
		jLabel2 = new JLabel();
		jLabel3 = new JLabel();
		jLabel4 = new JLabel();
		jLabel5 = new JLabel();
		jLabel6 = new JLabel();
		jLabel7 = new JLabel();
		jSeparator1 = new JSeparator();
		jSeparator2 = new JSeparator();
		abok = new JButton();
		contentPane = (JPanel)this.getContentPane();

		
		jLabel1.setText("Project Developed By ");
		jLabel1.setFont(new Font("Arial", Font.BOLD, 14));
		
		jLabel2.setText("Saurabh S. Gandhe");
		jLabel2.setFont(new Font("Arial", Font.BOLD, 12));
		jLabel3.setText("Sachin S. Jadhav");
		jLabel3.setFont(new Font("Arial", Font.BOLD, 12));
		jLabel4.setText("Nayana S. Lokhande");
		jLabel4.setFont(new Font("Arial", Font.BOLD, 12));
		jLabel5.setText("Precient Technologies ");
		jLabel5.setFont(new Font("Arial", Font.BOLD, 12));
		jLabel6.setText("VidCofED ver 1.0 ");
		jLabel6.setFont(new Font("Arial", Font.BOLD, 16));
		jLabel7.setText("Credits ");
		jLabel7.setFont(new Font("Arial", Font.BOLD, 14));
		abok.setText("Ok");
		abok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				abok_actionPerformed(e);
			}

		});
	
		contentPane.setLayout(null);
		addComponent(contentPane, jLabel1, 90,57,228,32);
		addComponent(contentPane, jLabel2, 102,198,186,18);
		addComponent(contentPane, jLabel3, 99,230,201,18);
		addComponent(contentPane, jLabel4, 98,264,210,18);
		addComponent(contentPane, jLabel5, 88,107,228,18);
		addComponent(contentPane, jLabel6, 98,21,209,17);
		addComponent(contentPane, jLabel7, 123,160,156,18);
		addComponent(contentPane, jSeparator1, 3,151,310,5);
		addComponent(contentPane, jSeparator2, -2,302,316,5);
		addComponent(contentPane, abok, 105,322,83,28);
		
		this.setTitle("About Us");
		this.setLocation(new Point(200, 50));
		this.setSize(new Dimension(320, 391));
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		this.setResizable(false);
	}

	/** Add Component Without a Layout Manager (Absolute Positioning) */
	private void addComponent(Container container,Component c,int x,int y,int width,int height)
	{
		c.setBounds(x,y,width,height);
		container.add(c);
	}

	
	private void abok_actionPerformed(ActionEvent e)
	{
	   this.dispose();
	}


//============================= Testing ================================//
//=                                                                    =//
//= The following main method is just for testing this class you built.=//
//= After testing,you may simply delete it.                            =//
//======================================================================//
	public static void main(String[] args)
	{
		JFrame.setDefaultLookAndFeelDecorated(true);
		JDialog.setDefaultLookAndFeelDecorated(true);
		try
		{
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		}
		catch (Exception ex)
		{
			System.out.println("Failed loading L&F: ");
			System.out.println(ex);
		}
		new Aboutus();
	}
//= End of Testing =


}
