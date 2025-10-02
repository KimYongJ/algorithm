//https://www.acmicpc.net/problem/1362
//1초 128MB
//100 100// 적정 체중(10<=1000), 실제 체중(10<=1000)
//F 10//펫의 체중이 N증가
//F 10
//E 20//체중이 N감소
//# 0// 시나리오 종료 신호
//50 30
//F 5
//E 20
//# 0
//0 0// 입력 종료시 0 0 입력
//답
//1 :-)
//2 :-(

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	
	static final String HAPPY = ":-)";
	static final String SAD = ":-(";
	static final String END = "RIP";
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int idx = 0;
		while(true)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int B = Integer.parseInt(st.nextToken());// 적정 체중(10<=1000)
			int W = Integer.parseInt(st.nextToken());// 실제 체중(10<=1000)
			
			if(B == 0 && W == 0)
				break;
			
			boolean isEnd = false;
			
			while(true)
			{
				st = new StringTokenizer(br.readLine());
				char c = st.nextToken().charAt(0);
				int N = Integer.parseInt(st.nextToken());
				
				if(c == '#') break;

				W += c == 'E' ? -N : N;
				
				if(W <= 0)
					isEnd = true;
			}
			
			sb.append(++idx).append(' ')
			.append(isEnd ? END : (B/2 < W && W < B*2 ? HAPPY : SAD))
			.append('\n');
		}
		System.out.print(sb);
	}
}