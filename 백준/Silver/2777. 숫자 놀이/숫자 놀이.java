// https://github.com/kimyongj/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		while(T-->0) 
		{
			int n	= Integer.parseInt(br.readLine());
			int cnt = 0;
			if(n >= 10) {
				int bef = 0;
				while(bef != n) 
				{
					bef = n;
					for(int i=9; i>=2; i--) 
					{
						if(n % i == 0) 
						{
							cnt++;
							n /= i;
							break;
						}
					}
				}
				if(n>=10) {
					cnt = -1;
				}
			}else {
				cnt = 1;
			}
			sb.append(cnt).append('\n');
		}
		System.out.print(sb.toString());
	}
}