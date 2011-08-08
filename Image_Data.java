

import java.awt.Image;
import java.awt.image.MemoryImageSource;
import java.awt.image.PixelGrabber;
import javax.swing.JFrame;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Writer;

public class Image_Data extends JFrame {

    /**
     * 
     */
    
    private static final long serialVersionUID = 1L;
    int height, width;
    private Write wr;
    Pixel_Data RLE[]=new Pixel_Data[(height/4)*(width/4)];
    Pixel_Data pix[][];

    public Image_Data(FileOutputStream F)
    {
	Write wr= new Write(F);
	
    }
    public Image_Data()
    {
		
    }
    
    void getImageData(Image img, Write wr) throws IOException {

	height = img.getHeight(null);
	width = img.getWidth(null);
	int pixel[] = new int[height * width];
	pix = new Pixel_Data[height][width];
	PixelGrabber pg;
	pg = new PixelGrabber(img, 0, 0, width, height, pixel, 0,
		width);
	
	
	//Encoder E=new Encoder();
//	wr.writeArrayData(pixel,pixel.length);
	
	try {
	    if (pg.grabPixels()) {
		//System.out.println("Done");
	    }
	} catch (InterruptedException e) {
	    e.printStackTrace();
	    e.getMessage();
	}
	
	
	int k = 0,l=0;
	int data[]=new int[height*width*3];
	for (int i = 0; i < height; i++) {
	    for (int j = 0; j < width; j++) {
		pix[i][j] = new Pixel_Data();
		pix[i][j].alpha = (pixel[k] >> 24) & 0xff;
		data[l++]=pix[i][j].red = (pixel[k] >> 16) & 0xff;
		data[l++]=pix[i][j].green = (pixel[k] >> 8) & 0xff;
		data[l++]=pix[i][j].blue = (pixel[k++]) & 0xff;
		
		
	    }
	}
	wr.writeArrayData(data,data.length);
	//System.out.println("original_length: "+data.length );
    }

    
    void getImageData( Write wr,Image img) throws IOException {

    	height = img.getHeight(null);
    	width = img.getWidth(null);
    	int pixel[] = new int[height * width];
    	pix = new Pixel_Data[height][width];
    	PixelGrabber pg;
    	pg = new PixelGrabber(img, 0, 0, width, height, pixel, 0,
    		width);
    	
    	
    	//Encoder E=new Encoder();
    	wr.writeArrayData(pixel,pixel.length);
    	
    	try {
    	    if (pg.grabPixels()) {
    		//System.out.println("Done");
    	    }
    	} catch (InterruptedException e) {
    	    e.printStackTrace();
    	    e.getMessage();
    	}
    	
    	
//    	int k = 0,l=0;
//    	int data[]=new int[height*width*3];
//    	for (int i = 0; i < height; i++) {
//    	    for (int j = 0; j < width; j++) {
//    		pix[i][j] = new Pixel_Data();
//    		pix[i][j].alpha = (pixel[k] >> 24) & 0xff;
//    		data[l++]=pix[i][j].red = (pixel[k] >> 16) & 0xff;
//    		data[l++]=pix[i][j].green = (pixel[k] >> 8) & 0xff;
//    		data[l++]=pix[i][j].blue = (pixel[k++]) & 0xff;
//    		
//    		
//    	    }
//    	}
//    	wr.writeArrayData(data,data.length);
    	//System.out.println("original_length: "+data.length );
        }

	
	
    
    
    Image CreateImage() {

	int pixel[] = new int[height * width];
	int k = 0;
	for (int i = 0; i < height; i++) {
	    for (int j = 0; j <width; j++) {
		pixel[k] = (0x00000000 | pix[i][j].alpha << 24
			| pix[i][j].red << 16 | pix[i][j].green << 8 | pix[i][j].blue);
		k++;
	    }
	}
	Image img = createImage(new MemoryImageSource(width , height,
		pixel, 0, width ));
	return img;
    }
}




