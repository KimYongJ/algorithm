//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/2635
import java.io.BufferedReader;
import java.io.InputStreamReader;
class Main{
	
	static int N, maxLen, arr[];
	static StringBuilder sb;
	public static void DFS(int depth) {
		int num = arr[depth-2] - arr[depth-1];
		if(num < 0)
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
		arr[depth] = num;
		DFS(depth + 1);
	}
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N		= Integer.parseInt(br.readLine());	// 0<=30,000;
		arr		= new int[30_000];
		arr[0]	= N;
		
		for(int i=0; i<=N; i++)
		{
			arr[1] = i;
			DFS(2);
		}
		
		System.out.println(maxLen);
		System.out.print(sb);
	}
}