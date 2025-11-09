//https://www.acmicpc.net/problem/22858
//1초 1024MB

class Main{
	
	static int[] arr[], base;
	static int N, K;
	
	public static void main(String[] args)throws Exception{
		N = read();
		K = read();
		arr = new int[2][N + 1];
		base = new int[N + 1];

		for(int i=1; i<=N; i++) arr[0][i] = read();
		for(int i=1; i<=N; i++) base[i] = read();
		
		int idx = 0;
		
		while(K-->0)
		{
			idx ^= 1;
			for(int i=1; i<=N; i++)
				arr[idx][base[i]] = arr[idx ^ 1][i];
		}

		StringBuilder sb = new StringBuilder();
		
		for(int i=1; i<=N; i++)
			sb.append(arr[idx][i]).append(' ');
		
		System.out.print(sb);
	}
    static int read() throws Exception {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) >= 48)
            n = (n << 3) + (n << 1) + (c & 15);
        return n;
    }
}