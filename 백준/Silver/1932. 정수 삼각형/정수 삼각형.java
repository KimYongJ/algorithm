// https://github.com/KimYongJ/algorithm
class Main{
	public static void main(String[] args)throws Exception{		
		int N = read();
		
		int[][]arr = new int[N][N];
		
		for(int i=0; i<N; i++)
			for(int j=0; j<=i; j++)
				arr[i][j] = read();
		
		
		for(int i=N-2; i>=0; i--)
			for(int j=0; j<=i; j++) 
				arr[i][j] += Math.max(arr[i+1][j],arr[i+1][j+1]);
	
		System.out.println(arr[0][0]);
	}
    private static int read() throws Exception
    {
	    int c, n = System.in.read() & 15;
	    while ((c = System.in.read()) > 32)
	    	n = (n << 3) + (n << 1) + (c & 15);
	    return n;
	}
}