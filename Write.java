import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.channels.FileChannel;

public class Write {

    private FileChannel writer, reader;

    private ByteBuffer head;

    private ByteBuffer data;

    private IntBuffer temp;

    public Write(FileOutputStream outFile) {

	writer = outFile.getChannel();
	head = ByteBuffer.allocate(8);
    }

    public void writeArrayData(int[] st, int end) throws IOException {

	int index = (end * 4) + 4;
	data = ByteBuffer.allocateDirect(index);
	temp = data.asIntBuffer();
	temp.put(end);
	temp.put(st, 0, end);
	data.limit(index);
	data.position(0);
	while (data.hasRemaining()) {

	    writer.write(data);
	}
    }

    public void readData(FileInputStream inFile) throws IOException {
	reader = inFile.getChannel();
	while (inFile.available() > 0) {
	    // if (inFile.available() > 0) {
	    // System.out.println("---------------------------------------------");
	    // System.out.println("\t Remaining Bytes :
	    // "+inFile.available());
	    // System.out.println("---------------------------------------------");
	    // }
	    head.clear();
	    reader.read(head);
	    head.compact();
	    int index = head.getInt();
	    int col = head.getInt();
	    data = ByteBuffer.allocate(col * 4);
	    while (index != 0) {
		reader.read(data);
		data.compact();
		while (data.hasRemaining()) {
		    System.out.print("  " + data.getInt());
		}
		data.clear();
		index--;
		// System.out.print("\n");
	    }
	}
    }

}
