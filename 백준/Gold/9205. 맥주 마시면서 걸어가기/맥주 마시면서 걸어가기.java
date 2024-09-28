//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/9205
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;
class Node{
	int y, x; Node(int y, int x){this.y=y; this.x=x;}
}
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());				// 1<=50
		while(T-->0)
		{
			int N			= Integer.parseInt(br.readLine());	// 편의점개수 (0<=100)
			Node cs[]		= new Node[N];						// 편의점 위치를 담을 배열
			boolean visit[] = new boolean[N];
			boolean flag	= false;
			StringTokenizer st = new StringTokenizer(br.readLine());
			Node start = new Node(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
			
			for(int i=0; i<N; i++)
			{
				st = new StringTokenizer(br.readLine());
				cs[i] = new Node(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
			}
			st = new StringTokenizer(br.readLine());
			Node last = new Node(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
			
			ArrayDeque<Node> q = new ArrayDeque<>();
			q.add(start);
			while(!q.isEmpty())
			{
				Node now = q.poll();
				
				if(Math.abs(now.x-last.x) + Math.abs(now.y-last.y) <= 1000)
				{
					flag = true;
					break;
				}
				for(int i=0; i<N; i++)
					if(!visit[i])
					{
						int dist = Math.abs(now.x-cs[i].x) + Math.abs(now.y-cs[i].y);
						if(dist <= 1000)
						{
							visit[i] = true;
							q.add(cs[i]);
						}
					}
			}
			
			if(flag)
				sb.append("happy");
			else
				sb.append("sad");
			sb.append('\n');
		}
		System.out.print(sb.toString());
	}
}