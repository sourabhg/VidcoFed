

class Thread_Test{
    public static void main(String[] args)
    {
	for(int i=0;i<25;i++)
	{
	    Runnable r= new print_no(i);
	    Thread t=new Thread(r);
	    t.start();
	}
    }
    
}

class print_no  implements Runnable
{
    public print_no(int i)
    {
	x=i;
    }

    public void run() {
	
	while(M!=x);
	System.out.println(x);
	M++;
	
	
	// TODO Auto-generated method stub
	
    }
    public static  int M=0;
    public int x;
}