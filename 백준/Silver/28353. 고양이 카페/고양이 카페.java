//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/28353
//2초 / 1024MB
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());	// 고양이수(1≤N≤오천)
		int K = Integer.parseInt(st.nextToken());	// 최대무게K(1≤K≤10의9승)
		int C = 0;
		int s = 0;
		int e = N-1;
		int arr[] = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++)
			arr[i] = Integer.parseInt(st.nextToken());
		
		Arrays.sort(arr);
		
		while(s < e)
		{
			if(arr[e] + arr[s] <= K)
			{
				--e;
				++s;
				++C;
			}
			else
				--e;
		}
		System.out.print(C);
	}
}