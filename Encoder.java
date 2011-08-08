import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class Encoder {

    private static int N = 4;
    private static double C[][] = new double[N][N];
    private static double Ct[][] = new double[N][N];

    public static void Matrix_Initialisation() {

	int i, j;
	double n = (double) (N);
	for (i = 0; i < N; i++) {
	    C[0][i] = 1.0 / Math.sqrt(n);
	    Ct[i][0] = C[0][i];
	}
	for (i = 1; i < N; i++) {
	    for (j = 0; j < N; j++) {
		double jj = (double) j;
		double ii = (double) i;
		C[i][j] = Math.sqrt(2.0 / n)
			* Math.cos(((2.0 * jj + 1.0) * ii * Math.PI)
				/ (2.0 * n));
		Ct[j][i] = C[i][j];
	    }
	}
    }
    
    /* --------------------------------------------------------
     * Method for DCT transformation  
     * 
     ----------------------------------------------------------*/

    public Image_Data FORWARD_DCT(int pos_v, int pos_h, Image_Data ID) {

	double temp_R[][] = new double[N][N];
	double temp_G[][] = new double[N][N];
	double temp_B[][] = new double[N][N];
	double tempR, tempG, tempB;
	int i;
	int j;
	int k;
	for (i = 0; i < N; i++) {
	    for (j = 0; j < N; j++) {
		temp_R[i][j] = 0.0;
		temp_G[i][j] = 0.0;
		temp_B[i][j] = 0.0;
		for (k = 0; k < N; k++) {
		    temp_R[i][j] += (((int) (ID.pix[pos_v + i][pos_h + k].red) - 128) * Ct[k][j]);
		    temp_G[i][j] += (((int) (ID.pix[pos_v + i][pos_h + k].green) - 128) * Ct[k][j]);
		    temp_B[i][j] += (((int) (ID.pix[pos_v + i][pos_h + k].blue) - 128) * Ct[k][j]);
		}
	    }
	}
	for (i = 0; i < N; i++) {
	    for (j = 0; j < N; j++) {
		tempR = 0.0;
		tempG = 0.0;
		tempB = 0.0;
		for (k = 0; k < N; k++) {
		    tempR += (C[i][k] * temp_R[k][j]);
		    tempG += (C[i][k] * temp_G[k][j]);
		    tempB += (C[i][k] * temp_B[k][j]);
		}
		ID.pix[pos_v + i][pos_h + j].red = (int) Math.round(tempR);
		ID.pix[pos_v + i][pos_h + j].green = (int) Math.round(tempG);
		ID.pix[pos_v + i][pos_h + j].blue = (int) Math.round(tempB);
		// System.out.println((pos_v+i)+" "+(pos_)
	    }
	}
	return ID;
    }
/*----------------------------------------------------------------
     Method for Inverse DCT Transformation  
------------------------------------------------------------------*/    
    Image_Data INVERSE_DCT(int pos_v, int pos_h, Image_Data ID) {

	double temp_R[][] = new double[N][N];
	double temp_G[][] = new double[N][N];
	double temp_B[][] = new double[N][N];
	double tempR, tempG, tempB;
	int i;
	int j;
	int k;
	for (i = 0; i < N; i++) {
	    for (j = 0; j < N; j++) {
		temp_R[i][j] = 0.0;
		temp_G[i][j] = 0.0;
		temp_B[i][j] = 0.0;
		for (k = 0; k < N; k++) {
		    temp_R[i][j] += ID.pix[pos_v + i][pos_h + k].red * C[k][j];
		    temp_G[i][j] += ID.pix[pos_v + i][pos_h + k].green
			    * C[k][j];
		    temp_B[i][j] += ID.pix[pos_v + i][pos_h + k].blue * C[k][j];
		}
	    }
	}
	for (i = 0; i < N; i++) {
	    for (j = 0; j < N; j++) {
		tempR = 0.0;
		tempG = 0.0;
		tempB = 0.0;
		for (k = 0; k < N; k++) {
		    tempR += Ct[i][k] * temp_R[k][j];
		    tempG += Ct[i][k] * temp_G[k][j];
		    tempB += Ct[i][k] * temp_B[k][j];
		}
		tempR += 128.0;
		tempG += 128.0;
		tempB += 128.0;
		if (tempR < 0) {
		    ID.pix[pos_v + i][pos_h + j].red = 0;
		} else if (tempR > 255) {
		    ID.pix[pos_v + i][pos_h + j].red = 255;
		} else {
		    ID.pix[pos_v + i][pos_h + j].red = (int) Math.round(tempR);
		}
		if (tempG < 0) {
		    ID.pix[pos_v + i][pos_h + j].green = 0;
		} else if (tempG > 255) {
		    ID.pix[pos_v + i][pos_h + j].green = 255;
		} else {
		    ID.pix[pos_v + i][pos_h + j].green = (int) Math
			    .round(tempG);
		}
		if (tempB < 0) {
		    ID.pix[pos_v + i][pos_h + j].blue = 0;
		} else if (tempB > 255) {
		    ID.pix[pos_v + i][pos_h + j].blue = 255;
		} else {
		    ID.pix[pos_v + i][pos_h + j].blue = (int) Math.round(tempB);
		}
	    }
	}
	return ID;
    }

    
    
    public Image_Data transform(Image_Data ID,Write Wr_Comp) throws IOException {

    	// ID is provided by Original data file......
    	Quantisation Q = new Quantisation();
    	Zigzag Z = new Zigzag();
    	Pixel_Data RLE[][] = new Pixel_Data[(ID.height / N) * (ID.width / N)][33];
		int k = 0;
		for (int i = 0; i <= ID.height - N; i += N) {
			for (int j = 0; j <= ID.width - N; j += N) {
				ID = FORWARD_DCT(i, j, ID);
				ID = Q.Quant(N, ID, i, j);
				RLE[k][0] = new Pixel_Data();
				RLE[k++] = Z.zigzag_motion(i, j, ID,Wr_Comp);       // Write Data into compress file......
		
//		//Wr_Comp.writeArrayData(RLE[k][0].red,RLE[k].length);
//		// after this add write code
//		RLE[k - 1] = Z.Inverse_RLE(RLE, k - 1);
//		ID = Z.Inverse_zigzag(RLE, ID, k - 1);
//		ID = Q.I_Quanta(N, ID, i, j);
//		ID = INVERSE_DCT(i, j, ID);
		
	    }
	}
	return ID;
   }
}