//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/1059
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main{

	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());// 배열의 크기 L(1<=50)
		int arr[] = new int[N+1];				// 배열S(정수(1<=1000), 중복수 없음)
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++)
			arr[i] = Integer.parseInt(st.nextToken());
		int T = Integer.parseInt(br.readLine());
		
		Arrays.sort(arr);
		
		
		int S = -1;
		int E = -1;
		for(int i=1; i<=N; i++)
		{
			int s = arr[i-1] + 1;
			int e = arr[i] - 1;
			if(s<=T && T<=e)
			{
				S = s; E = e;
				break;
			}
		}
		
		int cnt = 0;
		for(int s=S; s<=T; s++)
			for(int e=T; e<=E; e++)
				if(s!=e)
					++cnt;

		
		System.out.print(cnt);
	}
}