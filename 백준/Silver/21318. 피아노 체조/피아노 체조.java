//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/21318
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N		= Integer.parseInt(br.readLine());	// 악보 개수(1<=십만)
		int psum[]	= new int[N+1];
		int arr[]	= new int[N+1];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++)
		{
			arr[i] = Integer.parseInt(st.nextToken());// 난이도(1<=십억)
			if(arr[i] < arr[i-1])
				++psum[i-1];
			
			psum[i] += psum[i-1];
		}
		
		int Q = Integer.parseInt(br.readLine());// 질문개수(1<=십만)
		while(Q-->0)
		{
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			sb.append(psum[y-1] - psum[x-1]).append('\n');
		}
		System.out.print(sb);
	}
}