//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/16562
//2초 / 512MB
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	
	static int N, M, TOTAL;
	static int[] parent, money;
	static boolean[] visit;
	
	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N		= Integer.parseInt(st.nextToken());	// 학생수(1<=만)
		M		= Integer.parseInt(st.nextToken());	// 친구 관계수(0<=만)
		TOTAL	= Integer.parseInt(st.nextToken());	// 가지고있는돈(1<=천만)
		parent	= new int[N+1];
		money	= new int[N+1];
		visit	= new boolean[N+1];
		
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++)
		{
			parent[i]	= i;
			money[i]	= Integer.parseInt(st.nextToken());
		}
		
		for(int i=0; i<M; i++)
		{
			st = new StringTokenizer(br.readLine());
			int parent1 = getParent(Integer.parseInt(st.nextToken()));
			int parent2 = getParent(Integer.parseInt(st.nextToken()));
			if(parent1 != parent2)
			{
				if(parent2 < parent1)
				{
					int tmp = parent2;
					parent2 = parent1;
					parent1 = tmp;
				}
				
				parent[parent2] = parent1;
				money[parent1] = Math.min(money[parent1], money[parent2]);
			}
		}
		
		int total = 0;
		for(int i=1; i<=N; i++)
		{
			int parentNum = getParent(i);
			if(!visit[parentNum])
			{
				visit[parentNum] = true;
				total += money[parentNum];
			}
		}
		
		System.out.print(total <= TOTAL ? total : "Oh no");
	}
	public static int getParent(int node) {
		if(parent[node] == node) return node;
		return parent[node] = getParent(parent[node]);
	}
}