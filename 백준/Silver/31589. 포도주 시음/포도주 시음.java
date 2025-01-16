//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/31589
//1초 / 512MB
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());	
		int N		= Integer.parseInt(st.nextToken());	// 포도주 수N (1≤300,000)
		int K		= Integer.parseInt(st.nextToken());	// 최대 선택 가능 수(1≤N)
		long arr[]	= new long[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++)
			arr[i] = Integer.parseInt(st.nextToken());
		
		Arrays.sort(arr);
		
		long res = arr[N-1];
		int s = 0;
		int e = N - 2;
		--K;
		while(K>1)
		{
			res += arr[e--] - arr[s++];
			K-=2;
		}
		System.out.print(res);
	}
}
