//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/25395
//2초 / 1024MB
//요약 : 특정 위치에서 시작해서, 모든 방법을 다써서 최대한 연결할 수 있는 자동차 번호 오름차순 출력
import java.util.ArrayDeque;
class Main{
	public static void main(String[] args)throws Exception{
		ArrayDeque<Integer> q = new ArrayDeque<>();
		int N		= read();			// 자동차수(1<=백만)
		int S		= read();			// 시작노드(1<=N)
		int pos[]	= new int[N+1];		// 현재 위치
		int fuel[]	= new int[N+1];		// 갈 수 있는 거리
		boolean v[]	= new boolean[N+1];	// 해당 위치 방문 유무
		
		for(int i=1; i<=N; i++)pos[i]  = read();
		for(int i=1; i<=N; i++)fuel[i] = read();
		
		v[S] = true;
		q.add(S);
		while(!q.isEmpty())
		{
			int now = q.poll();
			// 왼쪽으로 가며 탐색
			for(int i=now-1, f = fuel[now]; i>=1; i--)
			{
				int needFuel = pos[i+1] - pos[i];	// 다음 위치까지 드는 연료
				if(needFuel <= f && !v[i])
				{
					q.add(i);
					v[i] = true;
				}

				f -= needFuel;
				
				if(fuel[i] >= f)
					break;
			}
			for(int i=now+1, f = fuel[now]; i<=N; i++)
			{
				int needFuel = pos[i] - pos[i-1];
				if(needFuel <= f && !v[i])
				{
					q.add(i);
					v[i] = true;
				}
				
				f -= needFuel;
				
				if(fuel[i] >= f)
					break;
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i=1; i<=N; i++)
			if(v[i])
				sb.append(i).append(' ');
		System.out.print(sb);
	}
	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
}
