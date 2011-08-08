import java.awt.*;
import java.awt.event.*;
import java.awt.image.PixelGrabber;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.media.Buffer;
import javax.media.format.YUVFormat;
import javax.media.protocol.PushBufferStream;
import javax.media.util.BufferToImage;
import javax.swing.*;

public class MyScreen extends JFrame {

    /**
         * 
         */
    private static final long serialVersionUID = 1L;

    private JButton Donebutton;

    private JPanel contentPane;

    private int max = 0;

    public JCanvas jcan;

    private FileOutputStream outFile;
    
    private FileOutputStream CompFile;

    private FileInputStream inFile;

    private BufferToImage conv;

    private Buffer b;

    private Thread ImgSource;

    private PushBufferStream strms;

    private boolean stopme = false;

    public MyScreen() throws FileNotFoundException {
	outFile = new FileOutputStream(new File("c:\\\\VIDCONFED.ns"));
	CompFile = new FileOutputStream(new File("c:\\\\VIDCONFEDCOM.ns"));
//	inFile=new FileInputStream(new File("VIDCONFED1.ns"));
	//super();
	initializeComponent();
	this.setVisible(true);
    }

    private void initializeComponent() {

	jcan = new JCanvas(null);
	Donebutton = new JButton();
	contentPane = (JPanel) this.getContentPane();
	Donebutton.setText("Done");
	Donebutton.addActionListener(new ActionListener() {

	    public void actionPerformed(ActionEvent e) {

		Donebutton_actionPerformed(e);
	    }
	});
	contentPane.setLayout(null);
	contentPane.setBorder(BorderFactory.createRaisedBevelBorder());
	addComponent(contentPane, Donebutton, 133, 279, 83, 28);
	addComponent(contentPane, jcan, 55, 31, 271, 229);
	jcan.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
	jcan.setBackground(new Color(4, 0, 0));
	// jPanel1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
	// jPanel1.setBackground(new Color(4, 0, 0));
	this.setTitle("My Screen");
	this.setLocation(new Point(650, 50));
	this.setSize(new Dimension(379, 353));
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	this.setResizable(false);
	max = 0;
	strms = VidcofedManager.getVideoStream();
	YUVFormat rgbf = (YUVFormat) strms.getFormat();
	conv = new BufferToImage(rgbf);
	b = new Buffer();
	/*
         * String name1="D:\\beforecom.DAT"; final File f1 = new File(name1);
         * try { if(f1.exists()==true) f1.delete(); f1.createNewFile();
         *  } catch (IOException e) { e.printStackTrace(); } String
         * name2="D:\\aftercom.DAT"; final File f2 = new File(name2); try {
         * if(f2.exists()==true) f2.delete(); f2.createNewFile();
         * 
         *  } catch (IOException e) { e.printStackTrace(); }
         */

	ImgSource = new Thread(new Runnable() {

	    public void run() {
		Image img, ref;
		Encoder EN = new Encoder();
		Encoder.Matrix_Initialisation();
		Quantisation.Matrix_Initialisation();
		Image_Data ID = new Image_Data();
		Image_Data ID1 = new Image_Data();
		Detect_Motion Dt = new Detect_Motion();
		Write wr = new Write(outFile);
		Write wr_Cp = new Write(CompFile);
		
		
		try {
		    strms.read(b);
		    ref = conv.createImage(b);
		    ID.getImageData(ref,wr);

		} catch (IOException e1) {
		    // TODO Auto-generated catch block
		    e1.printStackTrace();
		}

		for (int i = 0; stopme != true; i++) {
		    try {
		    	strms.read(b);
		    	img = conv.createImage(b);
		    	ID1.getImageData(img,wr);
// we have to write data into file.....
		    	ID=Dt.Motion_Detection(ID, ID1,wr_Cp);		
		    	img=ID.CreateImage();
			/*// ID.getImageData(img);
			 ID=EN.transform(ID,wr_Cp);
			// img=ID.CreateImage();*/
		    	jcan.setImage(img);
			// ID.getImageData(img,f1);
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

    private void addComponent(Container container, Component c, int x, int y,
	    int width, int height) {

	c.setBounds(x, y, width, height);
	container.add(c);
    }

    private void Donebutton_actionPerformed(ActionEvent e) {

	// VidcofedManager.stopCaptureProcess();
	stopme = true;
	this.dispose();
    }

    // ============================= Testing
    // ================================//
    // = =//
    // = The following main method is just for testing this class you
    // built.=//
    // = After testing,you may simply delete it. =//
    // ======================================================================//
    public static void main(String[] args) throws FileNotFoundException {

	JFrame.setDefaultLookAndFeelDecorated(true);
	JDialog.setDefaultLookAndFeelDecorated(true);
	try {
	    UIManager
		    .setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
	} catch (Exception ex) {
	    System.out.println("Failed loading L&F: ");
	    System.out.println(ex);
	}
	new MyScreen();
    }
    // = End of Testing =
}

class write_Image implements Runnable {
    public write_Image(Image img, int i) {
	IMG = img;
	x = i;
    }

    public void run() {

	System.out.println(chk);
	Image_Data ID = new Image_Data();
	// ID.getImageData(IMG);
	// while(chk!=x);
	chk++;

	// int height=IMG.getHeight(null);
	// int width=IMG.getWidth(null);
	// int pixel[] = new int[IMG.getHeight(null)*IMG.getWidth(null)];
	// PixelGrabber pg;
	// pg = new PixelGrabber(IMG, 0, 0, width, height, pixel, 0,
	// width);
	// while(chk!=20);
	// Encoder E=new Encoder();
	// E.write1(pixel,pixel.length, F);

	// TODO Auto-generated method stub

    }

    public Image IMG;

    public static int chk = 0;

    public int x;
}
