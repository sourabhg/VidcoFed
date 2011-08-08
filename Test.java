import java.awt.Image;
import java.awt.image.MemoryImageSource;
import java.io.*;


import javax.swing.ImageIcon;
import javax.swing.JFrame;

class Test extends JCanvas
{
	Test(Image i) {
		super(i);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static void main(String S[]) throws IOException
	{
		Image img;
		img = new ImageIcon("F://Image Processing//Video_Compression//images/don6.jpg").getImage();
		Image_Data ID=new Image_Data();
		Encoder.Matrix_Initialisation();
		Quantisation.Matrix_Initialisation();
		FileOutputStream outFile = new FileOutputStream(new File("c:\\\\testVIDCONFED1.ns"));
		FileOutputStream comFile = new FileOutputStream(new File("c:\\\\testVIDCONFED2.ns"));
		FileOutputStream com1File = new FileOutputStream(new File("c:\\\\testVIDCONFED3.ns"));
		Write wr = new Write(outFile);
		Write Wr_Comp = new Write(comFile);
		Write Wr_Comp1 = new Write(com1File);
		Encoder En= new Encoder();
		ID.getImageData(img,wr);
		En.transform(ID, Wr_Comp);
		ID.getImageData(Wr_Comp1, img);
		
		FileInputStream inFile = new FileInputStream(new File("c:\\\\testVIDCONFED1.ns"));
		int[] data= new int [img.getHeight(null)*img.getWidth(null)*3];
		Read r= new Read(inFile);
		data=r.readArrayData(data);
		
		
		
	}
}