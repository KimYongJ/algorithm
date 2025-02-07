//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/20040
//1초 / 512MB
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N		= Integer.parseInt(st.nextToken());// 점의개수 n(3<=오십만) / 노드는 0부터 n-1까지  
		int M		= Integer.parseInt(st.nextToken());// 진행된 차례(3<=백만)
		int parent[]= new int[N];
		int result	= 0;
		
		for(int i=1; i<N; i++)
			parent[i] = i;
		
		for(int i=0; i<M; i++)
		{
			st = new StringTokenizer(br.readLine());
			int parent1 = getParent(parent, Integer.parseInt(st.nextToken()));
			int parent2 = getParent(parent, Integer.parseInt(st.nextToken()));
			if(parent1 == parent2) {
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
}
