//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/10216
//8초 / 256MB
class Main{
	public static void main(String[] args)throws Exception{
		StringBuilder sb = new StringBuilder();
		int T = read();
		while(T-->0)
		{
			int N			= read();		// 진영 숫자 1<=3000
			int pos[][]		= new int[N][3];// x,y좌표 및 지름(0<=5000)
			int parent[]	= new int[N];
			
			for(int i=0; i<N; i++)
			{
				pos[i][0] = read();
				pos[i][1] = read();
				pos[i][2] = read();
				parent[i] = i;
			}
			
			for(int i=0; i<N; i++)
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
	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
}