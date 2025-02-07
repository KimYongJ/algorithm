//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/20040
//1초 / 512MB
class Main{
	public static void main(String[] args)throws Exception{

		int N		= read();// 점의개수 n(3<=오십만) / 노드는 0부터 n-1까지  
		int M		= read();// 진행된 차례(3<=백만)
		int parent[]= new int[N];
		int result	= 0;
		
		for(int i=1; i<N; i++)
			parent[i] = i;
		
		for(int i=0; i<M; i++)
		{
			int parent1 = getParent(parent, read());
			int parent2 = getParent(parent, read());
			if(parent1 == parent2)
			{
				result = i + 1;
				break;
			}
			if(parent2 < parent1)
			{
				int tmp = parent2;
				parent2 = parent1;
				parent1 = tmp;
			}
			parent[parent2] = parent1;
		}
		System.out.print(result);
	}
	public static int getParent(int[] parent, int node) {
		if(parent[node] == node) return node;
		return parent[node] = getParent(parent, parent[node]);
	}
	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
}