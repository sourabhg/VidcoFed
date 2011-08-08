import java.io.IOException;

public class Zigzag {

    public int N = 4;
    public int row = 0, col = 0;
    public int p2[] = new int[N * N];
    public Pixel_Data[] Zig=new Pixel_Data[N*N];
    // public int p1[]=new int [N*N];
    int In_RLE[] = new int[N * N];
    public int In_zig[][] = new int[N][N];
    static int RunLength = 0;
    public int OutRunLen[] = new int[N * N];
    public int value[] = new int[N * N], t = 0;
    int k = 0, temp = 0;
    int ZigZag[] = { 0, 0, 0, 1, 1, 0, 2, 0, 1, 1, 0, 2, 0, 3, 1, 2, 2, 1, 3,
	    0, 3, 1, 2, 2, 1, 3, 2, 3, 3, 2, 3, 3 };

    public Pixel_Data[] Calculate_zero(Pixel_Data RLE[], Write wr,int pos_v,int pos_h) throws IOException {

	
	int RunLengthR,RunLengthG,RunLengthB;
	int Kr=2,Kg=2,Kb=2;
	Pixel_Data value[] = new Pixel_Data[35];
	for (int i = 0; i < 35; i++)
	{
	    value[i]=new Pixel_Data();
	    value[i].red = -999;
	    value[i].green = -999;
	    value[i].blue = -999;
	}
	value[0].red=RLE[0].red;
	value[1].red=RLE[1].red;
	value[0].green=RLE[0].green;
	value[1].green=RLE[1].green;
	value[0].blue=RLE[0].blue;
	value[1].blue=RLE[1].blue;
	
	RunLengthR = 0;RunLengthG = 0;RunLengthB = 0;
	for (int i = 2; i < 16+2; i++) {
	    if (RLE[i].red == 0) {
		RunLengthR++;
	    }
	    if (RLE[i].green == 0) {
		RunLengthG++;
	    }
	    if (RLE[i].blue == 0) {
		RunLengthB++;
	    }
	    if (RLE[i].red != 0) {
		if (RunLengthR != 0) {
		    value[Kr++].red = 0;
		    value[Kr++].red = RunLengthR;
		    value[Kr++].red = RLE[i].red;
		    value[Kr++].red = 0;
		    RunLengthR = 0;
		} else {
		    value[Kr++].red = RLE[i].red;
		    value[Kr++].red = 0;
		    RunLengthR = 0;
		}
	    }
	    if (i == 15+2) {
		if (RunLengthR != 0) {
		    value[Kr++].red = 0;
		    value[Kr++].red = RunLengthR;
		    RunLengthR = 0;
		}
	    }
	    
	    

	    if (RLE[i].green != 0) {
		if (RunLengthG != 0) {
		    value[Kg++].green= 0;
		    value[Kg++].green = RunLengthG;
		    value[Kg++].green = RLE[i].green;
		    value[Kg++].green= 0;
		    RunLengthG = 0;
		} else {
		    value[Kg++].green = RLE[i].green;
		    value[Kg++].green= 0;
		    RunLengthG = 0;
		}
	    }
	    if (i == 15+2) {
		if (RunLengthG != 0) {
		    value[Kg++].green = 0;
		    value[Kg++].green= RunLengthG;
		    RunLengthG = 0;
		}
	    }
	    

	    if (RLE[i].blue != 0) {
		if (RunLengthB != 0) {
		    value[Kb++].blue  = 0;
		    value[Kb++].blue = RunLengthB;
		    value[Kb++].blue  = RLE[i].blue;
		    value[Kb++].blue  = 0;
		    RunLengthB = 0;
		} else {
		    value[Kb++].blue  = RLE[i].blue;
		    value[Kb++].blue  = 0;
		    RunLengthB = 0;
		}
	    }
	    if (i == 15+2) {
		if (RunLengthB != 0) {
		    value[Kb++].blue  = 0;
		    value[Kb++].blue  = RunLengthB;
		    RunLengthB = 0;
		}
	    }
	}
//	System.out.println();
	int comp_Data[]= new int[100];
	int k=0,i;
	   comp_Data[k++]= pos_v;
	   comp_Data[k++]= pos_h;
	for(i=0;value[i].red!=-999;i++)
	   comp_Data[k++]=value[i].red;
	   comp_Data[k++]=-999;
	for(i=0;value[i].green!=-999;i++)
	    comp_Data[k++]=value[i].green;
	    comp_Data[k++]=-999;
	for(i=0;value[i].blue!=-999;i++)
	    comp_Data[k++]=value[i].blue;
	    comp_Data[k++]=-999;
	
	    wr.writeArrayData(comp_Data,k);	
	
	return value;
    }

    Pixel_Data[] Inverse_RLE(Pixel_Data RLE[][],int k) {

	int j = 0;
	int i = 0;
	//int p1[] = new int[N * N];
	Pixel_Data p1[] = new Pixel_Data[(N * N)+2];
	for(i=0;i<(N*N)+2;i++)
	    p1[i]=new Pixel_Data();
	int t = 2;
	j=2;
	//System.out.print(RLE[k][t].red);
	p1[0].red=RLE[k][0].red;
	p1[1].red=RLE[k][1].red;
	while (RLE[k][t].red == -999)
	    t++;
	while (RLE[k][t].red != -999) {
	    if (RLE[k][t].red == 0) { 
		for (int n = 0; n < RLE[k][t + 1].red; n++) {
		    p1[j].red = RLE[k][t].red;
		    j++;
		}
		RLE[k][t].red = -999;
		RLE[k][t + 1].red = -999;
		t += 2;
	    } else {
		p1[j].red = RLE[k][t].red;
		RLE[k][t].red = -999;
		RLE[k][t + 1].red = -999;
		t += 2;
		j++;
	    }
	}
	p1[0].green=RLE[k][0].green;
	p1[1].green=RLE[k][1].green;
	t = 2;
	j=2;
	while (RLE[k][t].green == -999)
	    t++;
	while (RLE[k][t].green != -999) {
	    if (RLE[k][t].green == 0) {
		for (int n = 0; n < RLE[k][t + 1].green; n++) {
		    p1[j].green = RLE[k][t].green;
		    j++;
		}
		RLE[k][t].green = -999;
		RLE[k][t + 1].green = -999;
		t += 2;
	    } else {
		p1[j].green = RLE[k][t].green;
		RLE[k][t].green = -999;
		RLE[k][t + 1].green = -999;
		t += 2;
		j++;
	    }
	}
	p1[0].blue=RLE[k][0].blue;p1[1].blue=RLE[k][1].blue;
	
	t = 2;j=2;
	while (RLE[k][t].blue == -999)
	    t++;
	while (RLE[k][t].blue != -999) {
	    if (RLE[k][t].blue == 0) {
		for (int n = 0; n < RLE[k][t + 1].blue; n++) {
		    p1[j].blue = RLE[k][t].blue;
		    j++;
		}
		RLE[k][t].blue = -999;
		RLE[k][t + 1].blue = -999;
		t += 2;
	    } else {
		p1[j].blue = RLE[k][t].blue;
		RLE[k][t].blue = -999;
		RLE[k][t + 1].blue = -999;
		t += 2;
		j++;
	    }
	}
	
	return p1;
    }

//    int[][] Inverse_zigzag(Pixel_Data[] Inv_RLE[]) {
//
//	int j = 0;
//	int p[][] = new int[N][N];
//	for (int i = 0; i < (32); i++) {
//	    row = ZigZag[i];
//	    i++;
//	    col = ZigZag[i];
//	    p[row][col] = datas[j];
//	    j++;
//	}
//	return p;
//    }

    Image_Data Inverse_zigzag(Pixel_Data RLE[][],Image_Data ID,int k) {

	int pos_v=RLE[k][0].red;
	int pos_h=RLE[k][1].red;
	//System.out.print(pos_v+" "+pos_h);
	int j=2;
	for (int i = 0; i < (32); i++) 
	{
	    row = ZigZag[i];
	    i++;
	    col = ZigZag[i];
	    ID.pix[row+pos_v][col+pos_h].red = RLE[k][j].red;
	    ID.pix[row+pos_v][col+pos_h].green = RLE[k][j].green;
	    ID.pix[row+pos_v][col+pos_h].blue = RLE[k][j].blue;
//	    System.out.println(RLE[k][j].red+" "+RLE[k][j].green+" "+RLE[k][j].blue);
	    j++;
	}
	return ID;
    }

   
    Pixel_Data[] zigzag_motion(int pos_v,int pos_h,Image_Data ID, Write wr) throws IOException 
    {
	Pixel_Data RLE[]= new Pixel_Data[35];
	for(int i=0;i<35;i++)
	{
	    RLE[i]=new Pixel_Data();
	}
    	RLE[0].red=pos_v;
	RLE[1].red=pos_h;
	RLE[0].green=pos_v;
	RLE[1].green=pos_h;
	RLE[0].blue=pos_v;
	RLE[1].blue=pos_h;
	
	int j = 2;
	for (int i = 0; i < (32); i++) {
	    row = ZigZag[i++];
	    col = ZigZag[i];
	    RLE[j].red = ID.pix[row+pos_v][col+pos_h].red;
	    RLE[j].green = ID.pix[row+pos_v][col+pos_h].green;
	    RLE[j++].blue = ID.pix[row+pos_v][col+pos_h].blue;
	}
	  RLE= Calculate_zero(RLE,wr,pos_v,pos_h);
	return RLE;
    }
}