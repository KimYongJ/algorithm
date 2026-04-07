//https://www.acmicpc.net/problem/32980
//1초 1024MB
//3 // 쓰레기 수 1<=10,000
//OOOOO // 쓰레기 문자열(1<=1,000, {P,C,V,S,G,F,O})
//PPPPPPP
//PPPPFFFFF
//2 2 2 2 2 2 // 플라스틱(P), 캔(C), 비닐(V), 종이(S), 유리(G), 스티로폼(F) 단위 크기당 비용(1<=10,000)
//3 // 일반쓰레기 배출시 단위 크기당 비용(1<=10,000)
//답 : 56
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	
	static int N;
	static int[] cost;
	static String[] str;
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N		= Integer.parseInt(br.readLine());// 쓰레기 수 1<=10,000
		cost	= new int[7];
		str		= new String[N];
		
		for(int i=0; i<N; i++)
			str[i] = br.readLine();

		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<6; i++)
			cost[i] = Integer.parseInt(st.nextToken());
		
		cost[6] = Integer.parseInt(br.readLine());
		
		long sum = 0;
		
		for(String s : str)
		{
			boolean isSame = true;
			
			char base = s.charAt(0);
			
			int money = cost[6]; // 비용은 무조건 일반
			
			for(char c : s.toCharArray())
			{
				if(base != c)
				{
					isSame = false;
					break;
				}
			}
			
			if(isSame)
				money = Math.min(cost[getCost(base)], cost[6]);
			
			sum += money * s.length();
		}
		
		System.out.print(sum);
	}
	static int getCost(char c) {
		if(c == 'P')	return 0;
		if(c == 'C')	return 1;
		if(c == 'V')	return 2;
		if(c == 'S')	return 3;
		if(c == 'G')	return 4;
		if(c == 'F')	return 5;
		return 6;
	}
}