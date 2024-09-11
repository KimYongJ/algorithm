//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/1294
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
class Main{
	public static void main(String[] args)throws Exception{
		PriorityQueue<String> pq = new PriorityQueue<String>((a,b)->{
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
			pq.add(br.readLine());
		
		while(!pq.isEmpty())
		{
			String now = pq.poll();
			String next = now.substring(1);
			sb.append(now.charAt(0));
			if(next.length() >= 1)
				pq.add(next);
		}
		System.out.print(sb.toString());
	}
}
