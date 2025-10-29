//https://www.acmicpc.net/problem/11504
//1초 256MB
//3 // 테스트 케이스
//8 3 // 몇 등분인지(1<=100), X Y의 길이 (1<=9 &&  <=N)
//2 0 0// X 자리수
//3 1 1// Y 자리수
//3 7 8 3 1 9 2 7// 돌림판 상태
//5 2
//8 8
//9 9
//1 3 2 5 4
//6 3
//0 0 0
//9 9 9
//1 2 3 4 5 6
//답
//1
//0
//6
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder result = new StringBuilder();
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		while(T-->0)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());// 몇 등분인지(1<=100)
			int M = Integer.parseInt(st.nextToken());// X Y의 길이 (1<=9 &&  <=N)
			int X = 0;
			int Y = 0;
			int arr[] = new int[N];
			
			sb.setLength(0);
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<M; i++) sb.append(st.nextToken());
			X = Integer.parseInt(sb.toString());
			
			sb.setLength(0);
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<M; i++) sb.append(st.nextToken());
			Y = Integer.parseInt(sb.toString());
			
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<N; i++) arr[i] = Integer.parseInt(st.nextToken());
			
			int cnt = 0;
			
			for(int i=0; i<N; i++)
			{
				int idx = i;
				int repeat = M;
				sb.setLength(0);
				while(repeat-->0)
				{
					sb.append(arr[idx]);
					idx = (idx + 1) % N;
				}
				
				int num = Integer.parseInt(sb.toString());
				
				if(X <= num && num <= Y)
					++cnt;
			}
			
			result.append(cnt).append('\n');
		}
		System.out.print(result);
	}
}