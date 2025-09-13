//https://www.acmicpc.net/problem/1592
//2초 128MB
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());//사람수
		int M = Integer.parseInt(st.nextToken());//목표횟수
		int L = Integer.parseInt(st.nextToken());//다음턴 사람 칸수
		int cnt[] = new int[N];
		int time = 0;
		int ballIdx = 0;
		
		while(++cnt[ballIdx] != M)
		{
			++time;
			
			int l = L;
			
			if(cnt[ballIdx] % 2 == 0)
				l = -L;
			
			ballIdx = (ballIdx + l + N) % N;
		}
		
		System.out.print(time);
	}
}