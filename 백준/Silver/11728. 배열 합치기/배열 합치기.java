//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/11728
class Main{
	public static void main(String[] args)throws Exception{
		StringBuilder sb = new StringBuilder();
		int N		= read();	// 1<=백만
		int M		= read();	// 1<=백만
		int arr1[]	= new int[N];
		int arr2[]	= new int[M];
		
		for(int i=0; i<N; i++)arr1[i] = read();
		for(int i=0; i<M; i++)arr2[i] = read();
		
		int u = 0, d = 0;
		
		while(u < N && d < M)
			sb.append(arr1[u] < arr2[d] ? arr1[u++] : arr2[d++]).append(' ');
		
		if(u < N)
			while(u < N)
				sb.append(arr1[u++]).append(' ');
		
		if(d < M)
			while(d < M)
				sb.append(arr2[d++]).append(' ');
		
		System.out.print(sb);
	}
    static int read() throws Exception {
        int c, n = System.in.read() & 15;
        boolean m = n == 13;
        if (m)n = System.in.read() & 15;
        while ((c = System.in.read()) >= 48) {
        n = (n << 3) + (n << 1) + (c & 15);}
        return m ? ~n + 1 : n;
    }
}