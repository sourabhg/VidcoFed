import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.channels.FileChannel;

public class Read {

    private FileChannel reader;

    private ByteBuffer head;

    private ByteBuffer data;

    private IntBuffer temp;

    public Read(FileInputStream inFile) {

	try {
	    System.out.println("FILE HAS: " + inFile.available());
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	reader = inFile.getChannel();
	head = ByteBuffer.allocate(4);
    }

    public int[] readArrayData(int[] out) throws IOException {

	reader.read(head);
	head.compact();
	int size = head.getInt();
	head.clear();
	data = ByteBuffer.allocateDirect((size * 4));
	reader.read(data);
	data.compact();
	data.position(0);
	temp = data.asIntBuffer();
	int[] dt = new int[size];
	temp.get(dt);
	return dt;
    }

}
