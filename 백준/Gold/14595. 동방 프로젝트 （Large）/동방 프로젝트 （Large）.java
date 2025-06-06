//https://www.acmicpc.net/problem/14595
//1초 512MB
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

class Main{
	
	static int N, M;
	static List<Edge> edge;
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		edge = new ArrayList<>();
		
		for(int i=0; i<M; i++)
		{
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			edge.add(new Edge(s, e));
		}
		
		Collections.sort(edge);
		
		int prevE = 0;
		int remove = 0;
		
		for(Edge now : edge)
		{
			if(prevE < now.s)
			{
				prevE = now.e;
				remove += now.e - now.s;
			}
			else if(now.e > prevE)
			{
				remove += now.e - prevE;
				prevE = now.e;
			}
		}
		System.out.print(N - remove);
	}
	static class Edge implements Comparable<Edge>{
		int s,e;
		Edge(int s, int e){
			this.s=s;
			this.e=e;
		}
		@Override
		public int compareTo(Edge o) {
			return s == o.s ? e - o.e : s - o.s;
		}
	}
}