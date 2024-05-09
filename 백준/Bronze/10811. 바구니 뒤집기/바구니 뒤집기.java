// https://github.com/KimYongJ/algorithm

class Main{
	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	public static void main(String[] args)throws Exception{
		int s,	e,	t, i, j, m,
			N 		= read(),
			M 		= read(),
			arr[] 	= new int[N+1];
		
		for(i=1; i<=N; i++)
			arr[i] = i;
		
		for(m=0; m<M; m++) 
		{
			s = read();
			e = read();
			for(i=s, j=e; i<j; i++,j--)
			{
				t = arr[i];
				arr[i] = arr[j];
				arr[j] = t; 
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for(i=1; i<=N; i++)
			sb.append(arr[i]).append(' ');
		System.out.println(sb);
	}
	
}