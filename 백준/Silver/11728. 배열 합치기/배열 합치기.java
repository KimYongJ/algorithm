//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/11728
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(st.nextToken());	// 1<=백만
		int M = Integer.parseInt(st.nextToken());	// 1<=백만
		int arr1[] = new int[N];
		int arr2[] = new int[M];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++)
			arr1[i] = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<M; i++)
			arr2[i] = Integer.parseInt(st.nextToken());
		
		int u = 0, d = 0;
		
		while(u < N || d < M)
		{
			if(u < N && d < M)
				sb.append(arr1[u] < arr2[d] ? arr1[u++] : arr2[d++]).append(' ');
			else if(u < N)
				while(u < N)
					sb.append(arr1[u++]).append(' ');
			else if(d < M)
				while(d < M)
					sb.append(arr2[d++]).append(' ');
		}
		System.out.print(sb);
	}
}