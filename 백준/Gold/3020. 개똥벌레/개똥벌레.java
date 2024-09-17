//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/3020
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N		= Integer.parseInt(st.nextToken());	// 길이 N(2<=이십만) 항상짝수
		int M		= Integer.parseInt(st.nextToken());	// 높이 H(2<=오십만)
		int arr[]	= new int[M+1];						// 종유석을 담을 배열
		
		for(int i=0; i<N; i++)
		{
			if(i % 2 == 0)
			{
				arr[0] += 1;
				arr[Integer.parseInt(br.readLine())] += -1;
			}else
				arr[M - Integer.parseInt(br.readLine())] += 1;
		}
		
		int cnt[]	= new int[N+1];
		int min		= arr[0];
		
		cnt[arr[0]]++;
		
		for(int i=1; i<M; i++)
		{
			arr[i] += arr[i-1];
			min = Math.min(min, arr[i]);
			cnt[arr[i]]++;
		}
		
		
		System.out.print(new StringBuilder().append(min).append(' ').append(cnt[min]));
	}
}