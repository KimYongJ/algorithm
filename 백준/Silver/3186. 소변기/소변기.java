//https://www.acmicpc.net/problem/3186
//1초 128MB
//1 1 3 // 사용중 기록할 K초 / 사람이 없어야 하는 초(플러시동작까지의시간), 수열길이(1<=10,000)
//101 // 0은 서있는사람이 없음 ,1은 있음을 의미
//답(플러시 할때마다 시작 시간을 출력) 플러시가 없다면 NIKAD 출력
//2
//4
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int K = Integer.parseInt(st.nextToken()); // 사용중 기록할 K초
		int L = Integer.parseInt(st.nextToken()); // 사람이 없어야 하는 초(플러시동작까지의시간)
		int N = Integer.parseInt(st.nextToken()); // 수열길이(1<=10,000)
		int idx = 0;
		boolean exists[] = new boolean[N + L + 1];
		
		for(char c : br.readLine().toCharArray())
			exists[idx++] = c == '1';
		
		boolean use = false;
		int useTime = 0;
		int empTime = 0;
		
		for(int i = 0; i < exists.length; i++)
		{
			if(exists[i]) // 사람이서 있을 때
			{
				empTime = 0;
				
				if(++useTime >= K)
					use = true;
				
				continue;
			}
			
			useTime = 0;
			
			if(use && ++empTime == L)
			{
				use = false;
				sb.append(i + 1).append('\n');
			}
			
		}
		
		System.out.print(sb.length() == 0 ? "NIKAD" : sb);
	}
}