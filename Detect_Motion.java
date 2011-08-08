import java.io.IOException;

public class Detect_Motion {

    int N = 4;

   public Image_Data Motion_Detection(Image_Data ID1, Image_Data ID2,Write Wr_Comp) throws IOException {

	int res;
	       Quantisation Q = new Quantisation();
		Zigzag Z = new Zigzag();
		Encoder en= new Encoder();
		Pixel_Data RLE[][] = new Pixel_Data[(ID1.height / N) * (ID1.width / N)][33];
	for (int i = 0; i < ID1.height - N; i += N) {
	    for (int j = 0; j < ID2.width - N; j += N) {
		res = 0;
		for (int k = i; k < i + N; k++) {
		    for (int l = j; l < j + N; l++) {
			int red = Math.abs(ID1.pix[k][l].red
				- ID2.pix[k][l].red);
			int green = Math.abs(ID1.pix[k][l].green
				- ID2.pix[k][l].green);
			int blue = Math.abs(ID1.pix[k][l].blue
				- ID2.pix[k][l].blue);
			res += (int) Math
				.sqrt(((red * red) + (green * green) + (blue * blue)) / 3);
		    }
		}
		if (res > 250) {
		    for (int k = i; k < i + N; k++) {
			for (int l = j; l < j + N; l++) {
			    int red = ID1.pix[k][l].red - ID2.pix[k][l].red;
			    int green = ID1.pix[k][l].green
				    - ID2.pix[k][l].green;
			    int blue = ID1.pix[k][l].blue - ID2.pix[k][l].blue;
			    ID1.pix[k][l].red = ID1.pix[k][l].red - red;
			    ID1.pix[k][l].green = ID1.pix[k][l].green - green;
			    ID1.pix[k][l].blue = ID1.pix[k][l].blue - blue;
			    // ID1.pix[k][l].red=255;
			    // ID1.pix[k][l].green=0;
			    // ID1.pix[k][l].blue=0;
			}
		    }
		    //sending for DCT
	        
			int k = 0;
		        ID1 = en.FORWARD_DCT(i, j, ID1);
			ID1 = Q.Quant(N, ID1, i, j);
			RLE[k][0] = new Pixel_Data();
			RLE[k++] = Z.zigzag_motion(i, j, ID1,Wr_Comp);
			//Wr_Comp.writeArrayData(RLE[k][0].red,RLE[k].length);
			
			RLE[k - 1] = Z.Inverse_RLE(RLE, k - 1);
			ID1 = Z.Inverse_zigzag(RLE, ID1, k - 1);
			ID1 = Q.I_Quanta(N, ID1, i, j);
			ID1 = en.INVERSE_DCT(i, j, ID1);
		    
		    
		//    En.FORWARD_DCT(i, j, ID1);
		    
		//    En.INVERSE_DCT(i, j, ID1);
		}
		
	    }
	}
	return ID1;
    }
}
