//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/2635
class Main{
	
	static int N, maxLen, arr[];
	static StringBuilder sb;
	
	public static void main(String[] args)throws Exception{
		N		= read();	// 0<=30,000;
		arr		= new int[50];
		arr[0]	= N;
		
		for(int i=0; i<=N; i++)
		{
			arr[1] = i;
			DFS(2);
		}
		
		System.out.println(maxLen);
		System.out.print(sb);
	}
	
	static void DFS(int depth) {
		arr[depth] = arr[depth-2] - arr[depth-1];
		if(arr[depth] < 0)
		{
			if(maxLen < depth)
			{
				maxLen	= depth;
				sb		= new StringBuilder();
				for(int i=0; i<depth; i++)
					sb.append(arr[i]).append(' ');
			}
			return;
		}
		DFS(depth + 1);
	}
	
	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
}
