// https://github.com/kimyongj/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder	sb = new StringBuilder();
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		while(T-->0) 
		{
			int day		= Integer.parseInt(br.readLine());
			int price[]	= new int[day];
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<day; i++) 
			{
				price[i] = Integer.parseInt(st.nextToken());
			}
			long res = 0;
			int start = price[day-1];
			for(int i=day-1; i>=0; i--) 
			{
				if(price[i] > start)
					start = price[i];
				else
					res += start - price[i];
			}
			sb.append(res).append('\n');
		}
		System.out.print(sb.toString());
	}
}