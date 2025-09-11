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
	
	static final int len = 52;
	static final int start = 1;// A를 의미
	static final int end = 26;// Z를 의미
	static int[][] ad = new int[len + 1][len + 1];
	static int[] prev = new int[len + 1];
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		while(N-->0)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = atoi(st.nextToken().charAt(0));
			int b = atoi(st.nextToken().charAt(0));
			int u = Integer.parseInt(st.nextToken());
			ad[a][b] += u;
			ad[b][a] += u;
		}
		
		int sum = 0;
		while(true)
		{
			Arrays.fill(prev, -1); // 이전 탐색 노드를 담을 배열 초기화
			
			BFS();// A 에서 Z로 가는 방향을 마킹
			
			if(prev[end] < 0)
				break;
			
			sum += flow();
		}
		
		System.out.print(sum);
	}
	static int flow() {
		// end에서 start로 가며 최소 유량을 구한다.
		int now = end;
		int water = 1<<30;
		while(now != start)
		{
			water = Math.min(water, ad[prev[now]][now]);
			now = prev[now];
		}
		now = end;
		while(now != start)
		{
			ad[prev[now]][now] -= water;
			ad[now][prev[now]] += water;
			now = prev[now];
		}
		return water;
	}
	static void BFS() {
		Queue<Integer> q = new ArrayDeque<>();
		q.add(start);
		prev[start] = start;// 시작의 이전노드는 자기 자신
		
		while(!q.isEmpty())
		{
			int now = q.poll();
			
			if(now == end)
				return;
			
			for(int next=1; next<=len; next++)
			{
				if(ad[now][next] > 0 && prev[next] == -1)
				{
					q.add(next);
					prev[next] = now;
				}
			}
			
		}
	}
	
	static int atoi(char c) {
		int res = c <= 'Z' ? c - 'A' : c - 'a' + 26;
		return res + 1;// 1을 더해서 첫 시작이 1이되게함
	}
}