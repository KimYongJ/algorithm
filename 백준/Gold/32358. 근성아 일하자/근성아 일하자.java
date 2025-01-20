//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/32358
//1초 / 1024MB

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeSet;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		TreeSet<Integer> set = new TreeSet<>();
		int N		= Integer.parseInt(br.readLine());
		int now		= 0;
		long res	= 0;

		while(N-->0)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			int type = Integer.parseInt(st.nextToken());
			if(type == 1)
			{
				int value = Integer.parseInt(st.nextToken());
				if(value != now)
					set.add(value);
			}
			else
			{
				while(!set.isEmpty())
				{
					Integer l = set.lower(now);	// floor는 now값 포함, heigher는 미포함
					Integer r = set.higher(now);// ceiling은 now 값 포함, lower는 미포함
					int value = 0;
					if(l == null)
						value = r;
					else if(r == null)
						value = l;
					else
					{
						int diff1 = Math.abs(l - now);
						int diff2 = Math.abs(r - now);
						value = diff1 <= diff2 ? l : r;
					}
					
					res += Math.abs(now - value);
					now = value;
					set.remove(now);
				}
			}
		}
		System.out.print(res);
	}

}