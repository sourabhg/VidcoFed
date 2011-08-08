


/*
 *  Method 
 * 
 */
public class Quantisation {

    static int N = 4;
    static int Q[][] = new int[N][N];
    static int Quality = 10;

    public static void Matrix_Initialisation() {

	int i, j;
	Q = new int[N][N];
	for (i = 0; i < N; i++) {
	    for (j = 0; j < N; j++) {
		Q[i][j] = 1 + ((1 + i + j) * Quality);
	    }
	}
    }

    Image_Data Quant(int N, Image_Data ID, int pos_v, int pos_h) {

	/** *********************************Quantisation************** */
	int i, j;
	for (i = 0; i < N; i++) {
	    for (j = 0; j < N; j++) {
		ID.pix[i + pos_v][j + pos_h].red = (int) (ID.pix[i + pos_v][j
			+ pos_h].red / Q[i][j]);
		ID.pix[i + pos_v][j + pos_h].green = (int) (ID.pix[i + pos_v][j
			+ pos_h].green / Q[i][j]);
		ID.pix[i + pos_v][j + pos_h].blue = (int) (ID.pix[i + pos_v][j
			+ pos_h].blue / Q[i][j]);
		//System.out.println(i+" "+j+" "+ID.pix[i + pos_v][j + pos_h].red+" "+ID.pix[i + pos_v][j + pos_h].green+" "+ID.pix[i + pos_v][j + pos_h].blue);
	    }
	}
	return (ID);
    }

    Image_Data I_Quanta(int N, Image_Data ID, int pos_v, int pos_h) {

	/** *********************************I-Quantisation************** */
	for (int i = 0; i < N; i++) {
	    for (int j = 0; j < N; j++) {
		ID.pix[i + pos_v][j + pos_h].red = (int) (ID.pix[i + pos_v][j
			+ pos_h].red * Q[i][j]);
		ID.pix[i + pos_v][j + pos_h].green = (int) (ID.pix[i + pos_v][j
			+ pos_h].green * Q[i][j]);
		ID.pix[i + pos_v][j + pos_h].blue = (int) (ID.pix[i + pos_v][j
			+ pos_h].blue * Q[i][j]);
	    }// System.out.println("\n ");
	}
	return ID;
    }
}
