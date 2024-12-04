//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/5534
import java.io.BufferedReader;
import java.io.InputStreamReader;
class Main{
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N		= Integer.parseInt(br.readLine());
		String base	= br.readLine();
		int cnt		= 0;
		
		LOOP:
		for(int i=0; i<N; i++)
		{
			String compare = br.readLine();
			
			if(compare.length() < base.length())
				continue;
			
			int maxInterval = compare.length() - base.length() + 1;
			int interval = 0;
			
			while(++interval <= maxInterval)
			{
				for(int start=0; start<compare.length(); start++)
				{
					sb.setLength(0);
					
					int s = start;
					while(s<compare.length() && sb.length() != base.length())
					{
						sb.append(compare.charAt(s));
						s+=interval;
					}
					if(base.equals(sb.toString()))
					{
						++cnt;
						continue LOOP;
					}
				}
			}
			
		}
		
		System.out.print(cnt);
	}
}