//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/1294
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
class Main{
	public static void main(String[] args)throws Exception{
		PriorityQueue<StringBuilder> pq = new PriorityQueue<StringBuilder>((a,b)->{
			int min = Math.min(a.length(),b.length());
			for(int i=0; i<min; i++) {
				char c1 = a.charAt(i);
				char c2 = b.charAt(i);
				if(c1!=c2)
					return c1-c2;
			}
			return b.length() - a.length();
		});
		StringBuilder	sb = new StringBuilder();
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		while(N-->0)
			pq.add(new StringBuilder(br.readLine()));
		
		while(!pq.isEmpty())
		{
			StringBuilder now	= pq.poll();
			if(now.length() > 0)
			{
				sb.append(now.charAt(0));
				pq.add(now.deleteCharAt(0));
			}
		}
		System.out.print(sb.toString());
	}
}
/*
3
BBBBABBBBBABBBBBAB
BBBBABBB
BBBBAB
ans : BBBBABBBBABBBBABBBBBABBBBBABBBBB
*/