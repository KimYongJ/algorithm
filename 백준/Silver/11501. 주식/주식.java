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
			int day			= Integer.parseInt(br.readLine());
			long start		= Long.MIN_VALUE;
			long res		= 0;
			long sum		= 0;
			int cnt			= 0;
			long price[]	= new long[day];
			boolean flag[]	= new boolean[day];
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<day; i++) 
			{
				price[i] = Integer.parseInt(st.nextToken());
			}
			for(int i=day-1; i>=0; i--) 
			{
				if(price[i] > start) 
					start = price[i];
				else flag[i] = true;
			}
			for(int i=0; i<day; i++) {
				if(flag[i]) {
					cnt++;
					sum += price[i];
				}else {
					res += (price[i] * cnt) - sum;
					cnt = 0;
					sum = 0;
				}
			}
			
			sb.append(res).append('\n');
		}
		System.out.print(sb.toString());
	}
}