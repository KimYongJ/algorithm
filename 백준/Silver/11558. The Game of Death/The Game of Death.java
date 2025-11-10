//https://www.acmicpc.net/problem/11558
//1초 256MB
//1 // 테스트 케이스 수
//7 // 인원수(1<=10,000)
//2 // 지명한 숫자(1<=N)
//3
//4
//5
//6
//7
//1
//답 : 6
import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		while(T-->0)
		{
			int N = Integer.parseInt(br.readLine());
			int R = 0;
			int arr[] = new int[N + 1];
			boolean visit[] = new boolean[N + 1];
			
			for(int i=1; i<=N; i++)
				arr[i] = Integer.parseInt(br.readLine());
			
			int now = 1;
			
			while(now != N && !visit[now])
			{
				visit[now] = true;
				now = arr[now];
				++R;
			}
			
			sb.append(now == N ? R : 0).append('\n');
		}
		
		System.out.print(sb);
	}
}