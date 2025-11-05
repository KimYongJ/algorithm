//https://www.acmicpc.net/problem/4998
// 1초 128MB
//200.00 6.5 300
//500 4 1000.00
//답
//7
//18
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		while(true)
		{
			String str = br.readLine();
			
			if(str == null || str.length() == 0) break;
			
			st = new StringTokenizer(str);
			
			double N = Double.parseDouble(st.nextToken());
			double B = Double.parseDouble(st.nextToken());
			double M = Double.parseDouble(st.nextToken());
			
			int year = 0;
			
			while(N < M)
			{
				N = N + N / 100 * B;
				year++;
			}
			sb.append(year).append('\n');
		}
		System.out.print(sb);
	}
}