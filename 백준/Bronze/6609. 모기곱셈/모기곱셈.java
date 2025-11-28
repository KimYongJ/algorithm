//https://www.acmicpc.net/problem/6609
//1초 128MB
//10 20 40 4 2 2 10// 모기수(0<=100000), 번데기수(0<=100000), 유충수(0<=100000), 한모기가낳는 알의수(0<=100), 살아남는 유충(1<=10), 번데기 비율(1<=10), 구하려는 주(1<=1000)
//144 55 8 0 1 9 4
//10 10 10 2 3 2 6
//10 20 40 86 9 9 999
//답
//10
//0
//1
//10

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		while(true)
		{
			String str = br.readLine();
			if(str == null || str.length() == 0)
				break;
			
			StringTokenizer st = new StringTokenizer(str);
			int M = Integer.parseInt(st.nextToken());// 모기수(0<=100000)
			int P = Integer.parseInt(st.nextToken());// 번데기수(0<=100000)
			int L = Integer.parseInt(st.nextToken());// 유충수(0<=100000)
			int E = Integer.parseInt(st.nextToken());// 한모기가낳는 알의수(0<=100)
			int R = Integer.parseInt(st.nextToken());// 살아남는 유충(1<=10)
			int S = Integer.parseInt(st.nextToken());// 번데기 비율(1<=10)
			int N = Integer.parseInt(st.nextToken());// 구하려는 주(1<=1000)

			while(N-->0)
			{
				int L1 = M * E;
				int P1 = L / R;
				int M1 = P / S;
				L = L1;
				P = P1;
				M = M1;
			}
			sb.append(M).append('\n');
		}
		System.out.print(sb);
	}
}