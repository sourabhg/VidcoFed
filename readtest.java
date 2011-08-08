import java.io.*;

class readtest {
    public static void main(String str[]) throws IOException {
	FileOutputStream outFile = new FileOutputStream(new File(
		"c:\\\\test.ns"));
	FileInputStream CompFile = new FileInputStream(
		new File("c:\\\\test.ns"));
	Write w = new Write(outFile);
	int a[] = { 1, 2, 3, 4, 5, 60, 70, 80, 90, 100 };
	// for(int i=0;i<a.length;i++)
	// System.out.print(a[i]+" ");
	int b[] = { 60, 70, 80, 90, 100 };
	w.writeArrayData(a, a.length);
	w.writeArrayData(b, b.length);
	outFile.close();
	Read r = new Read(CompFile);
	int[] rd = new int[a.length];
	rd = r.readArrayData(rd);
	for (int i = 0; i < rd.length; i++)
	    System.out.print(rd[i] + " ");
	rd = new int[b.length];
	rd = r.readArrayData(rd);
	System.out.print("\n");
	for (int i = 0; i < rd.length; i++)
	    System.out.print(rd[i] + " ");

    }
}