//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/14921
//1초 / 512MB
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N		= Integer.parseInt(br.readLine());	// 2<=십만
		int arr[]	= new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++)
			arr[i] = Integer.parseInt(st.nextToken());	// 절대값|억|
		
		int res = 1<<30;
		int s	= 0;
		int e	= N - 1;
		while(s < e)
		{
			int abs = arr[e]+arr[s];
			if(Math.abs(abs) < Math.abs(res))
				res = abs;
			
			if(abs < 0)
				++s;
			else
				--e;
		}
		System.out.print(res);
	}
}