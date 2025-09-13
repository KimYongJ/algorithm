//https://www.acmicpc.net/problem/1173
//2초 16MB
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());//목표 운동 분
		int m = Integer.parseInt(st.nextToken());//초기,최소 맥박
		int M = Integer.parseInt(st.nextToken());//최대 맥박
		int T = Integer.parseInt(st.nextToken());//분당 맥박증가 수
		int R = Integer.parseInt(st.nextToken());//쉴 때 맥박 감소
		int res = 0;
		int now = m;
		
		if(m + T > M)
		{
			System.out.print(-1);
			return;
		}
		
		while(N > 0)
		{
			int next = now + T;// 운동했을 경우 맥박
			
			res++;
			
			if(next > M)
			{
				now = Math.max(m, now - R);
				continue;
			}
			now = next;
			
			N--;
		}
		
		System.out.print(res);
	}
}