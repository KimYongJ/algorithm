//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/12234
//5초 / 512MB
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder	sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());	// 테스트케이스 수 1<=100
		for(int i=1; i<=T; i++)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N		= Integer.parseInt(st.nextToken());
			int M		= Integer.parseInt(st.nextToken());
			int arr[]	= new int[N];
			st = new StringTokenizer(br.readLine());
			
			for(int j=0; j<N; j++)
				arr[j] = Integer.parseInt(st.nextToken());
			
			Arrays.sort(arr);
			
			int c = 0;
			int s = 0;
			int e = N-1;
			
			while(s<=e)
			{
				if(arr[s] + arr[e] <= M) {
					++s;
					--e;
				}
				else --e;
				++c;
			}
			
			sb.append("Case #").append(i).append(": ").append(c).append('\n');
		}
		System.out.print(sb);
	}
}