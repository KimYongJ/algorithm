//https://www.acmicpc.net/problem/16567
//2초 512MB
//5 9 // 칸의 길이(1<=백만), 명령어 횟수(1<=백만)
//0 0 0 0 0 // 초기 값
//0
//1 4
//0
//1 4
//0
//1 2
//0
//1 3
//0
//답
//0
//1
//1
//2
//1
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(st.nextToken());// 칸의 길이(1<=백만)
		int M = Integer.parseInt(st.nextToken());// 명령어 횟수(1<=백만)
		int cnt = 0;
		int arr[] = new int[N + 2];
		
		int prev = -1;
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++)
		{
			arr[i] = Integer.parseInt(st.nextToken());
			if(prev != 1 && arr[i] == 1)
				++cnt;
			
			prev = arr[i];
		}
		
		while(M-->0)
		{
			st = new StringTokenizer(br.readLine());
			int cmd = Integer.parseInt(st.nextToken());
			
			if(cmd == 0)
			{
				sb.append(cnt).append('\n');
				continue;
			}
			
			int idx = Integer.parseInt(st.nextToken());
			
			if(arr[idx] == 1)
				continue;
			
			arr[idx] = 1;
			
			if(arr[idx - 1] == arr[idx + 1])
			{
				// 양쪽이 0이면 cnt 1 증가
				// 양쪽이 1이면 cnt -1 처리
				if(arr[idx + 1] == 1) cnt--;
				else cnt++;
			}
			// 한쪽만 1이면 cnt 변동 없음
			
		}
		System.out.print(sb);
	}
}