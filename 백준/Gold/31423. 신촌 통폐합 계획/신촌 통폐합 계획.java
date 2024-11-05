//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/31423
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{

	public static void  main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N			= Integer.parseInt(br.readLine());	// 대학교 개수(2<=오십만)
		String[] str	= new String[N+1];
		int next[]		= new int[N+1];
		int tail[]		= new int[N+1];
		
		for(int i=1; i<=N; i++)
		{
			str[i]	= br.readLine();
			tail[i] = i;
		}
		int a=0,b;
		for(int i=1; i<N; i++)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			next[tail[a]] = b;
			tail[a] = tail[b];
		}
		
		while(a != 0) {
			sb.append(str[a]);
			a = next[a];
		}
		
		
		System.out.print(sb.toString());
	}
}