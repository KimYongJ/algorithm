//https://www.acmicpc.net/problem/18116
//4초 1024MB
//4// 지시 횟수 1<=1,000,000
//I 1 2// I명령어는 두 노드가 같은 부품임을 의미
//I 3 2
//Q 1// Q명령어는 해당 노드가 포함된 로봇에 몇개의 부품이 있는지 출력
//Q 4
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	
	static int MAX = 1_000_000;
	static int N;
	static int parent[];
	static int cnt[];
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		parent = new int[MAX + 1];
		cnt = new int[MAX + 1];
		for(int i=1; i<=MAX; i++)
		{
			parent[i] = i;
			cnt[i] = 1;
		}
		
		StringBuilder sb = new StringBuilder();
		while(N-->0)
		{
			st = new StringTokenizer(br.readLine());
			char cmd = st.nextToken().charAt(0);
			if(cmd == 'Q')
			{
				sb.append(cnt[find(Integer.parseInt(st.nextToken()))])
					.append('\n');
				continue;
			}
			
			int parent1 = find(Integer.parseInt(st.nextToken()));
			int parent2 = find(Integer.parseInt(st.nextToken()));
			
			if(parent1 == parent2)
			{
				continue;
			}
			
			if(parent[parent1] < parent[parent2])
			{
				cnt[parent1] += cnt[parent2];
				parent[parent2] = parent1;
				continue;
			}
			
			cnt[parent2] += cnt[parent1];
			parent[parent1] = parent2;
		}
		System.out.print(sb);
	}
	static int find(int node) {
		if(parent[node] == node) return node;
		return parent[node] = find(parent[node]);
	}
}