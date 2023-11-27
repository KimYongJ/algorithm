// https://github.com/KimYongJ/algorithm

class Main{

	static StringBuilder sb = new StringBuilder();
	static int N,M;
	public static void main(String[] args)throws Exception{
		//1부터 N까지 자연수 중에서 M개를 고른 수열
		N = read();
		M = read();

		combination(0,0,new int[M]);
		
		System.out.println(sb);
	}
	public static void combination(int r, int start, int[] arr) {
		if(r==M) {
			for(int i=0; i<M; i++) {
				sb.append(arr[i]).append(' ');
			}sb.append('\n');
			return;
		}
		for(int i=start; i<N; i++) {
			arr[r] = i+1;
			combination(r+1,i,arr);
		}
	}
	// 빠른 입력을 위해 만듦
    private static int read() throws Exception {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
        return n;
    }
}