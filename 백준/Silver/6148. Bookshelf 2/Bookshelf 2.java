//https://github.com/KimYongJ/algorithm
//https://www.acmicpc.net/problem/6148
class Main{
	
	static int N, H, min, arr[];
	
	static int read() throws Exception {// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	
	public static void DFS(int depth, int sum) {
		if(H <= sum)
		{
			min = Math.min(min, sum-H);
			return;
		}
		if(depth == N)
			return;
		DFS(depth+1, sum + arr[depth]);
		DFS(depth+1, sum);
	}
	
	public static void main(String[] args)throws Exception{
		N		= read();
		H		= read();
		arr		= new int[N];
		min		= 1<<30;
		
		for(int i=0; i<N; i++)
			arr[i] = read();
		
		DFS(0, 0);
		
		System.out.print(min);
	}
}