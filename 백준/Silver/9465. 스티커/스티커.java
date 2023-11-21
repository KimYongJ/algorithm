// https://github.com/KimYongJ/algorithm
class Main{
	public static void main(String[] args)throws Exception{
		StringBuilder sb = new StringBuilder();
		int T = read();
		while(T-->0) {
			int N = read();
			
			int[][] DP  = new int[2][N+2];
			
			for(int i=2; i<N+2;i++) DP[0][i] = read();

			for(int i=2; i<N+2; i++) DP[1][i] = read();
			
			for(int i=2; i<N+2; i++) {
				// DP 시작
				DP[0][i] += Math.max(DP[1][i-1], DP[1][i-2]);
				DP[1][i] += Math.max(DP[0][i-1], DP[0][i-2]);
			}
			sb.append(Math.max(DP[0][N+1], DP[1][N+1])).append('\n');
		}
		System.out.println(sb);
	}	
    static int read() throws Exception {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32)
            n = (n << 3) + (n << 1) + (c & 15);
        return n;
    }
}