//https://www.acmicpc.net/problem/26070
//2초 1024MB
//10 30 20 // 치킨원하는 곰, 피자 곰, 햄버거 곰(0<=10^9)
//6 100 1 // 치킨 식권 수, 피자식권수, 햄버거 식권 수(0<=10^9)
//답 : 57
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	
	static int[] bear, tick, tick_new;
	static long res;
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		bear = new int[3];
		tick = new int[3];
		tick_new = new int[3];
		
		for(int i=0; i<3; i++)
			bear[i] = Integer.parseInt(st.nextToken());// 치킨 원하는 곰(0<=10^9),피자 원하는 곰(0<=10^9),햄버거 원하는 곰(0<=10^9)
		
		st = new StringTokenizer(br.readLine());
		
		for(int i=0; i<3; i++)
			tick[i] = Integer.parseInt(st.nextToken());// 치킨 식권 수(0<=10^9),피자 식권 수(0<=10^9),햄버거 식권 수(0<=10^9)
		
		cal();
		
		for(int r=0; r<3; r++)
		{
			for(int i=0; i<3; i++)
			{
				int j = (i + 1) % 3;
				tick_new[j] = tick[i] / 3;
				tick[i] %= 3;
			}
			
			for(int i=0; i<3; i++)
				tick[i] += tick_new[i];
			
			cal();
		}
		
		System.out.print(res);
	}
	static void cal() {
		for(int i=0; i<3; i++)
		{
			if(bear[i] == 0 || tick[i] == 0)
				continue;
			
			int eat = Math.min(bear[i], tick[i]);
			
			res += eat;
			bear[i] -= eat;
			tick[i] -= eat;
		}
	}
}