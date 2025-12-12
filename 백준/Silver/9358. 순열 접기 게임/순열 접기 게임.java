//https://www.acmicpc.net/problem/9358
//1초 128MB
//2 // 테스트 케이스 (1<=100)
//5 // 수열 길이 (2<=100)
//2 5 10 3 -4
//3
//5 4 -3
//답
//Case #1: Alice
//Case #2: Bob

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for(int i=1; i<=T; i++)
		{
			int N = Integer.parseInt(br.readLine());
			int arr[] = new int[N];
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			for(int j=0; j<N; j++)
				arr[j] = Integer.parseInt(st.nextToken());
			
			int max = (N+1) / 2;
			
			while(max >= 2)
			{
				for(int j=0, z = N-1; j<max; j++, z--)
					arr[j] += arr[z];
				
				N = max;
				max = (N+1) / 2;
			}
			
			sb.append("Case #").append(i).append(": ")
				.append(arr[0] > arr[1] ? "Alice" : "Bob").append('\n');
		}
		
		System.out.print(sb);
	}
}