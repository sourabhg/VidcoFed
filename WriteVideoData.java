

/**
 * File : WriteVideoData.java
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
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.*;
import java.nio.channels.*;

/**
 * @author Bhushan
 */
final public class WriteVideoData {

    public WriteVideoData(FileOutputStream outFile, FileInputStream inFile) {

	this.inFile = inFile;
	writer = outFile.getChannel();
	reader = this.inFile.getChannel();
	head = ByteBuffer.allocate(4);
    }

    /**
     * This method writes whole two dimensional array to file
     * which is open by FileOutputSteam provided in the constructor
     * 
     * @param st
     * @throws IOException
     */
    public void writeArrayData(int[][] st) throws IOException {

	short row = (short)st.length;
	short col = (short)st[0].length;
	temp = IntBuffer.allocate((row * col));
	data = ByteBuffer.allocateDirect((row * col * 4) + 4);
	data.putShort(row);
	data.putShort(col);
	for (int i = 0; i < row; i++) {
	    temp.put(st[i]);
	}
	temp.flip();
	while (temp.hasRemaining()) {
	    data.putInt(temp.get());
	}
	data.flip();
	while (data.hasRemaining()) {
	    writer.write(data);
	}
    }

    /**
     * This method is for testing the file data.
     * So only FileInputStream Object is required.
     * IMPORTANT: Reads only the file written by same class, with using 
     * method writeData, and returns the two dimensional array
     * 
     * 
     * @throws IOException
     */
    public int[][] readData() throws IOException {

	int[][] tm = null;
	int row, col, i = 0;
	if (inFile.available() > 0) {
	    System.out.println("---------------------------------------------");
	    System.out.println("\t Remaining Bytes : " + inFile.available());
	    System.out.println("---------------------------------------------");
	    head.clear();
	    reader.read(head);
	    head.compact();
	    row = head.getShort();
	    col = head.getShort();
	    tm = new int[row][col];
	    i = 0;
	    data = ByteBuffer.allocate(col * 4);
	    while (row != 0) {
		reader.read(data);
		data.compact();
		IntBuffer ix = data.asIntBuffer();
		ix.get(tm[i++]);
		data.clear();
		row--;
	    }
	}
	return tm;
    }
    private FileChannel writer, reader;
    private FileInputStream inFile;
    private ByteBuffer head;
    private ByteBuffer data;
    private IntBuffer temp;
}
