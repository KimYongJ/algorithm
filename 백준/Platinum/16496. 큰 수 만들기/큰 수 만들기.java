//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/16496
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
class Main{
	public static void main(String[] args)throws Exception{
		PriorityQueue<String> pq = new PriorityQueue<String>(
				(a,b)->
			new StringBuilder().append(b).append(a).toString()
			.compareTo(
					new StringBuilder().append(a).append(b).toString()
			)
		);
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		while(N-->0) 
			pq.add(st.nextToken());
		
		StringBuilder sb = new StringBuilder();
		
		if("0".equals(pq.peek()))
			sb.append(0);
		else
			while(!pq.isEmpty())sb.append(pq.poll());
		
		System.out.print(sb.toString());
	}
}