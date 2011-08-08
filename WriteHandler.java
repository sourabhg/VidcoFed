
/**
 * File : WriteHandler.java
 * Author : Bhushan
 * 	No warranty about source is provided. You are free to redistribute or modify the
 *      source. The only requirement for using this code is to distribute it as it is 
 *	orginal or modified, but free. No one should be charged for using the source. 
 * 	If modifictaions made they should be added in documentation and the file version 
 *	should be updated. Necessary help should be provided for the Changes made. 
 *	Try it, test it, and have fun!
 * Version : 1.0  Apr 22, 2007
 */

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

/**
 * @author Bhushan
 */
public final class WriteHandler {

    public static void main(String[] args) {

	try {
	    
	    FileOutputStream outFile = new FileOutputStream("test.txt");
	    FileInputStream inFile = new FileInputStream("test.txt");
	    WriteVideoData dt = new WriteVideoData(outFile,inFile);
	    int[][] st = new int[3][12];
	    for (int i = 0; i < 3; i++) {
		Arrays.fill(st[i], 0);
	    }
	    dt.writeArrayData(st);
	    /**
	     * Second One Written
	     */
	    st = new int[5][5];
	    for (int i = 0; i < 5; i++) {
		Arrays.fill(st[i], 1);
	    }
	    dt.writeArrayData(st);
	    /**
	     * Third One Written
	     */
	    st = new int[7][6];
	    for (int i = 0; i < 7; i++) {
		Arrays.fill(st[i], 2);
	    }
	    dt.writeArrayData(st);
	    /**
	     * Forth One Written
	     */
	    st = new int[40][10];
	    for (int i = 0; i < 40; i++) {
		Arrays.fill(st[i], 3);
	    }
	    dt.writeArrayData(st);
	    outFile.close();
	    /**
	     * Now Read The Buffer
	     */
	    System.out.print("First One\n");
	    int[][] sx=dt.readData();
	    for (int i = 0; i < sx.length; i++) {
		for(int j=0;j<sx[0].length;j++){
		    System.out.print(sx[i][j]+"  ");
		}
		System.out.print("\n");
	    }
	    System.out.print("Second One\n");
	    sx=dt.readData();
	    for (int i = 0; i < sx.length; i++) {
		for(int j=0;j<sx[0].length;j++){
		    System.out.print(sx[i][j]+"  ");
		}
		System.out.print("\n");
	    }
	    System.out.print("Third One\n");
	    sx=dt.readData();
	    for (int i = 0; i < sx.length; i++) {
		for(int j=0;j<sx[0].length;j++){
		    System.out.print(sx[i][j]+"  ");
		}
		System.out.print("\n");
	    }

	    System.out.print("Forth One\n");
	    sx=dt.readData();
	    for (int i = 0; i < sx.length; i++) {
		for(int j=0;j<sx[0].length;j++){
		    System.out.print(sx[i][j]+"  ");
		}
		System.out.print("\n");
	    }
	
	} catch (FileNotFoundException e) {
	    e.printStackTrace();
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }
}
