//https://www.acmicpc.net/problem/25187
//1초 1024MB
//5 3 3//물탱크수(1<=100,000) 파이프수(0<=200,000), 방문 횟수(1<=100,000)
//1 0 1 1 0//각 물탱크에 들어있는 물 종류가 주어짐 청정수는 1, 고인물은 0
//1 2// 파이프 수만큼 연결이 주어짐
//3 4
//4 5
//1// 방문할 물탱크
//5
//4
//답
//0
//1
//1
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{

	static int N, M, K;
	static int color[];
	static int parent[];
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());//물탱크수(1<=100,000)
		M = Integer.parseInt(st.nextToken());//파이프수(0<=200,000)
		K = Integer.parseInt(st.nextToken());//방문 횟수(1<=100,000)
		color = new int[N + 1];
		parent = new int[N + 1];
		
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++)
		{
			color[i] = Integer.parseInt(st.nextToken());
			parent[i] = i;
			if(color[i] == 0)
				color[i] = -1;
		}
		
		for(int i = 1; i<=M; i++)
		{
			st = new StringTokenizer(br.readLine());
			int p1 = find(Integer.parseInt(st.nextToken()));
			int p2 = find(Integer.parseInt(st.nextToken()));
			if(p1 == p2)
				continue;
			if(parent[p1] < parent[p2]) {
				parent[p2] = p1;
				color[p1] += color[p2];
			}
			else {
				parent[p1] = p2;
				color[p2] += color[p1];
			}
		}
		StringBuilder sb = new StringBuilder();
		for(int i=1; i<=K; i++) {
			int p = find(Integer.parseInt(br.readLine()));
			sb.append(color[p] >= 1 ? 1 : 0).append('\n');
		}
		System.out.print(sb);
	}
	static int find(int node) {
		if(parent[node] == node) return node;
		return parent[node] = find(parent[node]);
	}
}