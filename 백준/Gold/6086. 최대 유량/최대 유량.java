//https://www.acmicpc.net/problem/6086
//1초 128MB
//5 // 파이프 개수1<=700
//A B 3// 연결 노드 2개와 유량(1<=1000)이 주어집니다. 
//B C 3// 대소문자가 다르면 다른 파이프 입니다.
//C D 5
//D Z 4
//B Z 6
//A-Z까지 최대 유량 : 3
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;
class Main{
	
	static final int MAX = 52;
	static final int start = atoi('A');
	static final int end = atoi('Z');
	static int [][] pipe = new int[MAX + 1][MAX + 1];// 각
	static int []prev = new int[MAX + 1];// A->Z 경로 탐색 중 이전 노드를 담을 배열
	static int N;
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		while(N-->0)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = atoi(st.nextToken().charAt(0));
			int b = atoi(st.nextToken().charAt(0));
			int u = Integer.parseInt(st.nextToken());
			// “무방향 파이프”를 양방향 잔여용량으로 초기화, 중복 간선은 합쳐짐
			pipe[a][b] += u;
			pipe[b][a] += u;
		}
		
		int res = 0;
		
		while(true)
		{
			// 이전 노드 위치를 담을 배열 초기화
			Arrays.fill(prev, -1);
			// A->Z 까지의 경로를 prev 배열에 마킹
			bfs();
			// 종료 지점에 더이상 못 가면 종료
			if(prev[end] < 0)
				break;
			// 최소 유량 확인 및 결과 갱신
			res += flow();
		}
		
		System.out.print(res);
	}
	static int flow() {
		int now = end;
		int water = Integer.MAX_VALUE;// 최소 유량
		
		while(now != start)
		{
			// end -> start로 가면서(역방향) 정방향 방향(pipe[prev[now]][now])에 있는 가장 최소 유량을 구함
			water = Math.min(water, pipe[prev[now]][now]);
			now = prev[now];
		}
		
		now = end;
		
		while(now != start)
		{
			// 최소 유량(water)을 정방향에는 마이너스 처리
			pipe[prev[now]][now] -= water;
			// 역방향에는 최소 유량(water)를 플러스 해줌으로 추후 되돌림을 할 수 있게 한다.
			pipe[now][prev[now]] += water;
			now = prev[now];
		}
		
		return water;
	}
	static void bfs() {
		Queue<Integer> q = new ArrayDeque<>();
		prev[start] = start;
		q.add(start);
		
		while(!q.isEmpty())
		{
			int now = q.poll();
			if(now == end)
				return;
			// 인접 노드 모두 탐색
			for(int next = 1; next <= MAX; next++)
			{
				// 잔여 유량이 있고, 방문한적이 없다면 탐색
				if(pipe[now][next] > 0 && prev[next] < 0)
				{
					prev[next] = now;// 이전노드마킹
					q.add(next);
				}
			}
		}
	}
	static int atoi(char c){
		int res = c <= 'Z' ? c - 'A' : c - 'a' + 26;
		return res + 1;
	}
}