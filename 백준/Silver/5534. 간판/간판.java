//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/5534
import java.io.BufferedReader;
import java.io.InputStreamReader;
class Main{
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N		= Integer.parseInt(br.readLine());
		char[] base	= br.readLine().toCharArray();
		int baselen = base.length;
		int ans		= 0;
		
		LOOP:
		for(int i=0; i<N; i++)
		{
			char[] compare	= br.readLine().toCharArray();
			int clen		= compare.length;
			int diff		= clen - baselen;
			if(clen < baselen)
				continue;
			
			int maxInterval = diff + 1;
			int interval	= 0;
			while(++interval <= maxInterval)
			{
				for(int start=0; start<=diff; start++)
				{
					
					int s	= start;
					int idx = -1;
					int cnt = 0;
					while(s<clen && ++idx < baselen && base[idx] == compare[s])
					{
						++cnt;
						s+=interval;
					}
					if(cnt == baselen)
					{
						++ans;
						continue LOOP;
					}
				}
			}
			
		}
		
		System.out.print(ans);
	}
}