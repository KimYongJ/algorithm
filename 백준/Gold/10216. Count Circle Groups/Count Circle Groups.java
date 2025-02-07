//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/10216
//8초 / 256MB
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		while(T-->0)
		{
			int N = Integer.parseInt(br.readLine());	// 진영 숫자 1<=3000
			int pos[][] = new int[N][3];	// x,y좌표 및 지름(0<=5000)
			int parent[] = new int[N];
			
			for(int i=0; i<N; i++)
			{
				StringTokenizer st = new StringTokenizer(br.readLine());
				pos[i][0] = Integer.parseInt(st.nextToken());
				pos[i][1] = Integer.parseInt(st.nextToken());
				pos[i][2] = Integer.parseInt(st.nextToken());
				parent[i] = i;
			}
			
			for(int i=0; i<N-1; i++)
			{
				for(int j=i+1; j<N; j++)
				{
					int x = pos[i][0] - pos[j][0];
					int y = pos[i][1] - pos[j][1];
					int r = pos[i][2] + pos[j][2];
					if(x*x + y*y <= r*r)
					{
						int parent1 = getParent(i,parent);
						int parent2 = getParent(j,parent);
						if(parent2 < parent1)
						{
							int tmp = parent2;
							parent2 = parent1;
							parent1 = tmp;
						}
						parent[parent2] = parent1;
					}
				}
			}
			
			boolean visit[] = new boolean[N+1];
			int group = 0;
			for(int i=0; i<N; i++)
			{
				int parentNode = getParent(i,parent);
				if(!visit[parentNode])
				{
					visit[parentNode] = true;
					++group;
				}
			}
			
			sb.append(group).append('\n');
		}
		System.out.print(sb);
	}
	public static int getParent(int node, int[] parent) {
		if(parent[node] == node) return node;
		return parent[node] = getParent(parent[node], parent);
	}
}
