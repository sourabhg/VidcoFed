import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.media.Buffer;
import javax.media.format.YUVFormat;
import javax.media.protocol.PushBufferStream;
import javax.media.util.BufferToImage;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.UIManager;
import javax.swing.JOptionPane;

public class vidcofed extends JFrame {

	
    private static final long serialVersionUID = 1L;

    public static void main(String[] args) {

	JFrame.setDefaultLookAndFeelDecorated(true);
	JDialog.setDefaultLookAndFeelDecorated(true);
	
          /*try { UIManager
          .setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel"); }
          catch (Exception ex) { System.out.println("Failed loading L&F: ");
          System.out.println(ex); }*/
         
	new vidcofed();
    }
        private MyScreen screen;
        private JMenuBar mymenu;
        private JMenu mainmenu;
        private JMenuItem conmenu;
        private JMenuItem disconmenu;
        private JMenuItem exmenu;
        private JMenu viewmenu;
        private JMenuItem rmviewmenu;
        private JMenuItem lviewmenu;
	private JMenu toolsmenu;
	private JMenu themesmenu;
	private JMenuItem motifmenu;
	private JMenuItem metalmenu;
	private JMenuItem windowsmenu;
	private JMenu helpmenu;
	private JMenuItem contentsmenu;
	private JMenuItem aboutmenu;
	private JPopupMenu.Separator separator1;
	private JToolBar toolBar1;
	private JButton conbutton;
	private JButton disconbutton;
	private JButton helpbutton;
	private JButton Exitbutton;
	private JLabel label1;
	public JCanvas jcan;
	
	    private BufferToImage conv;
	    private Buffer b;
	    private Thread ImgSource;
	    private PushBufferStream strms;
	    private boolean stopme=false;
	    
	    private FileOutputStream outFile;

	public vidcofed() {

    	super();
    	initComponents();
    	this.setVisible(true);
        }
	private void conmenuActionPerformed(ActionEvent e) throws FileNotFoundException {
		JOptionPane.showMessageDialog(null, "Message","Connection Established", JOptionPane.INFORMATION_MESSAGE);
		
		strms=VidcofedManager.getVideoStream();
		YUVFormat rgbf = (YUVFormat) strms.getFormat();
		conv = new BufferToImage(rgbf);
		b = new Buffer();
		outFile = new FileOutputStream(new File("c:\\\\VIDCONFED123.ns"));
		
		
		ImgSource = new Thread(new Runnable() {


		    public void run() {
			Image img, ref;
			Encoder EN = new Encoder();
			Encoder.Matrix_Initialisation();
			Quantisation.Matrix_Initialisation();
			Image_Data ID = new Image_Data();
			Image_Data ID1 = new Image_Data();
			Detect_Motion Dt = new Detect_Motion();
			Write wr= new Write(outFile);
						
			try {
			    strms.read(b);
			    ref = conv.createImage(b);
			    

			} catch (IOException e1) {
			    // TODO Auto-generated catch block
			    e1.printStackTrace();
			}

			for (int i = 0; stopme != true; i++) {
			    try {
				strms.read(b);
				img = conv.createImage(b);
				
				Runnable r= new Img_wrt(img,wr,i);
				Thread t=new Thread(r);
				t.start();
				
			//	ID.getImageData(wr,img);
				
				jcan.setImage(img);
				
				System.out.println(i);

			    } catch (IOException e) {
				stopme = true;
				e.printStackTrace();
			    }
			}
		    }
		});
		ImgSource.start();
		
	}
	 private void addComponent(Container container, Component c, int x, int y,int width, int height) {

		c.setBounds(x, y, width, height);
		container.add(c);
	    }

	private void disconmenuActionPerformed(ActionEvent e) {
		// TODO add your code here
	}

	private void exmenuActionPerformed(ActionEvent e) {
	    VidcofedManager.stopCaptureProcess();
		this.dispose();
	}

	private void rmviewmenuActionPerformed(ActionEvent e) {
		// TODO add your code here
	}

	private void lviewmenuActionPerformed(ActionEvent e) throws FileNotFoundException {
	    screen = new MyScreen();
	}

	private void motifmenuActionPerformed(ActionEvent e) {
		// TODO add your code here
	}

	private void metalmenuActionPerformed(ActionEvent e) {
		// TODO add your code here
	}

	private void windowsmenuActionPerformed(ActionEvent e) {
		// TODO add your code here
	}

	private void contentsmenuActionPerformed(ActionEvent e) {
		// TODO add your code here
	}

	private void aboutmenuActionPerformed(ActionEvent e) {
		// TODO add your code here
	}

	private void conbuttonActionPerformed(ActionEvent e) {
		// TODO add your code here
	}

	private void disconbuttonActionPerformed(ActionEvent e) {
		// TODO add your code here
	}

	private void helpbuttonActionPerformed(ActionEvent e) {
		// TODO add your code here
	}

	private void ExitbuttonActionPerformed(ActionEvent e) {
		// TODO add your code here
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		// Generated using JFormDesigner Evaluation license - saurabh gandhe
		mymenu = new JMenuBar();
		mainmenu = new JMenu();
		conmenu = new JMenuItem();
		disconmenu = new JMenuItem();
		exmenu = new JMenuItem();
		viewmenu = new JMenu();
		rmviewmenu = new JMenuItem();
		lviewmenu = new JMenuItem();
		toolsmenu = new JMenu();
		themesmenu = new JMenu();
		motifmenu = new JMenuItem();
		metalmenu = new JMenuItem();
		windowsmenu = new JMenuItem();
		helpmenu = new JMenu();
		contentsmenu = new JMenuItem();
		aboutmenu = new JMenuItem();
		separator1 = new JPopupMenu.Separator();
		toolBar1 = new JToolBar();
		conbutton = new JButton();
		disconbutton = new JButton();
		helpbutton = new JButton();
		Exitbutton = new JButton();
		label1 = new JLabel();

		//======== vidcofed ========
		{
		    Container vidcofedContentPane = this.getContentPane();
			vidcofedContentPane.setLayout(null);
		    this.setVisible(false);
			this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
			this.setFont(new Font("Palatino Linotype", Font.BOLD | Font.ITALIC, 16));
			this.setTitle("VidCofED - Real Time Compression System");
			this.setIconImage(new ImageIcon("images1\\pc-webcam.png").getImage());
			this.setLocation(new Point(200, 50));
			this.setSize(new Dimension(510, 498));
			jcan = new JCanvas(null);
			addComponent(vidcofedContentPane, jcan, 55, 31, 271, 229);
			
			jcan.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			jcan.setBackground(new Color(4, 0, 0));
			
			while (!VidcofedManager.getState()) {
			    VidcofedManager.initializeCaptureProcess();
			}
			//======== mymenu ========
			{

				//======== mainmenu ========
				{
					mainmenu.setText("Main");

					//---- conmenu ----
					conmenu.setText("Connect");
					conmenu.setToolTipText("Connect To the Remote machine");
					conmenu.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							try {
								conmenuActionPerformed(e);
							} catch (FileNotFoundException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
					});
					mainmenu.add(conmenu);

					//---- disconmenu ----
					disconmenu.setText("Disconnect");
					disconmenu.setToolTipText("Disconnects from the Remote machine");
					disconmenu.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							disconmenuActionPerformed(e);
						}
					});
					mainmenu.add(disconmenu);
					mainmenu.addSeparator();

					//---- exmenu ----
					exmenu.setText("Exit");
					exmenu.setToolTipText("Exit From the Application");
					exmenu.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							exmenuActionPerformed(e);
						}
					});
					mainmenu.add(exmenu);
				}
				mymenu.add(mainmenu);

				//======== viewmenu ========
				{
					viewmenu.setText("View");

					//---- rmviewmenu ----
					rmviewmenu.setText("Remote View");
					rmviewmenu.setToolTipText("Shows Remote View window");
					rmviewmenu.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							rmviewmenuActionPerformed(e);
						}
					});
					viewmenu.add(rmviewmenu);

					//---- lviewmenu ----
					lviewmenu.setText("Local View");
					lviewmenu.setToolTipText("Shows Local View Window");
					lviewmenu.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							try {
							    lviewmenuActionPerformed(e);
							} catch (FileNotFoundException e1) {
							    // TODO Auto-generated catch block
							    e1.printStackTrace();
							}
						}
					});
					viewmenu.add(lviewmenu);
				}
				mymenu.add(viewmenu);

				//======== toolsmenu ========
				{
					toolsmenu.setText("Tools");

					//======== themesmenu ========
					{
						themesmenu.setText("Themes");

						//---- motifmenu ----
						motifmenu.setText("Motif");
						motifmenu.setToolTipText("Changes Theme to motif");
						motifmenu.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								motifmenuActionPerformed(e);
							}
						});
						themesmenu.add(motifmenu);

						//---- metalmenu ----
						metalmenu.setText("Metal");
						metalmenu.setToolTipText("Changes Theme to Metal");
						metalmenu.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								metalmenuActionPerformed(e);
							}
						});
						themesmenu.add(metalmenu);

						//---- windowsmenu ----
						windowsmenu.setText("Windows");
						windowsmenu.setToolTipText("Changes to Default windows theme");
						windowsmenu.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								windowsmenuActionPerformed(e);
							}
						});
						themesmenu.add(windowsmenu);
					}
					toolsmenu.add(themesmenu);
				}
				mymenu.add(toolsmenu);

				//======== helpmenu ========
				{
					helpmenu.setText("Help");

					//---- contentsmenu ----
					contentsmenu.setText("Contents");
					contentsmenu.setToolTipText("whats in the vidCofED");
					contentsmenu.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							contentsmenuActionPerformed(e);
						}
					});
					helpmenu.add(contentsmenu);
					helpmenu.addSeparator();

					//---- aboutmenu ----
					aboutmenu.setText("About Us");
					aboutmenu.setToolTipText("General Information");
					aboutmenu.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							aboutmenuActionPerformed(e);
						}
					});
					helpmenu.add(aboutmenu);
				}
				mymenu.add(helpmenu);
			}
			this.setJMenuBar(mymenu);
			vidcofedContentPane.add(separator1);
			separator1.setBounds(new Rectangle(new Point(35, 45), separator1.getPreferredSize()));

			//======== toolBar1 ========
			{
				toolBar1.setBorder(new BevelBorder(BevelBorder.RAISED));
				toolBar1.setBackground(new Color(0, 204, 255));

				//---- conbutton ----
				conbutton.setIcon(new ImageIcon("images1\\mac.gif"));
				conbutton.setOpaque(false);
				conbutton.setToolTipText("Connect to the Remote Machine");
				conbutton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						conbuttonActionPerformed(e);
					}
				});
				toolBar1.add(conbutton);

				//---- disconbutton ----
				disconbutton.setIcon(new ImageIcon("images1\\network.gif"));
				disconbutton.setOpaque(false);
				disconbutton.setToolTipText("Disconnects From The remote machine");
				disconbutton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						disconbuttonActionPerformed(e);
					}
				});
				toolBar1.add(disconbutton);

				//---- helpbutton ----
				helpbutton.setIcon(new ImageIcon("images1\\icon_information_32x.gif"));
				helpbutton.setOpaque(false);
				helpbutton.setToolTipText("Get Help");
				helpbutton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						helpbuttonActionPerformed(e);
					}
				});
				toolBar1.add(helpbutton);
				toolBar1.addSeparator(new Dimension(15, 15));

				//---- Exitbutton ----
				Exitbutton.setIcon(new ImageIcon("images1\\cloapp.gif"));
				Exitbutton.setOpaque(false);
				Exitbutton.setToolTipText("Exit From The application");
				Exitbutton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						ExitbuttonActionPerformed(e);
					}
				});
				toolBar1.add(Exitbutton);
			}
			vidcofedContentPane.add(toolBar1);
			toolBar1.setBounds(new Rectangle(new Point(0, 0), toolBar1.getPreferredSize()));

			//---- label1 ----
			label1.setText("Remote View");
			vidcofedContentPane.add(label1);
			label1.setBounds(185, 75, 120, label1.getPreferredSize().height);

			
		}
		
	}

	
	
	
}


class Img_wrt implements Runnable
{
public Image Img;
public Write Fl;
public int x;
static int m=0;

	public Img_wrt(Image img,Write out,int i)
	{
		Img=img;
		Fl = out;
		x=i;
		
	}
	public void run() {
		Image_Data ID = new Image_Data();
		while(m!=x);
		System.out.println("in Tread "+x);
		m++;
		
		try {
			ID.getImageData(Fl,Img);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		
	}
	
}
