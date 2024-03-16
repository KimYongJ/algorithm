class Main{
    static int read() throws Exception 
    {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
        return n;
    }
	public static void main(String[] args)throws Exception{
		StringBuilder sb = new StringBuilder();
		int N 		= read();
		int M 		= read();
		int arr[] 	= new int[N+1];
		
		int a,b,c;
		for(int i=0; i<M; i++) 
		{
			a = read();
			b = read();
			c = read();
			for(; a<=b; a++)
				arr[a] = c;
		}
		
		for(int i=1; i<=N; i++)
			sb.append(arr[i]).append(' ');
		System.out.println(sb);
	}
}