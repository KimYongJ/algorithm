//https://www.acmicpc.net/problem/21315
//1초 1024MB
//5 // 3<=1000
//1 3 5 4 2 // 위부터 순서대로 나열
//답 : 2 1 // 1≤ K, 2^K < N
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	
	static int N;
	static int[] origin, copy;
	static int[] goal, backup;
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N		= Integer.parseInt(br.readLine());
		goal	= new int[N + 1];
		origin	= new int[N + 1];
		copy	= new int[N + 1];
		backup = new int[N + 1];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++) goal[i] = Integer.parseInt(st.nextToken());
		
		// N 은 3부터 시작, K는 1부터 시작
		int max = 1;
		
		while((1<<max) < N)max++;
		
		max -= 1;
		
		for(int k1=1; k1<=max; k1++)
		{
			for(int i=0; i<origin.length; i++)
				origin[i] = i;
			
			cal(k1);
			
			 System.arraycopy(origin, 0, backup, 0, N + 1); // 만든 결과를 backup에 백업해 놓는다.
			
			for(int k2=1; k2<=max; k2++)
			{
				System.arraycopy(backup, 0, origin, 0, N + 1);// 두번째 셔플마다 origin을 첫번째 섞을 때와 같이 만들어 준다.
				
				cal(k2);

				if(isSame(goal, origin))
				{
					System.out.printf("%d %d", k1, k2);
					return;
				}
			}
		}
		
	}
	static void cal(int k) {
		int pow = 1 << k;
		int s = N - pow + 1;
		int idx = dfs(pow, s, N);
		
		for(int j=1; j<s; j++)
			copy[idx++] = origin[j]; 
		
		int c[] = origin;
		origin = copy;
		copy = c;
	}
	static int dfs(int k, int s, int e) {
		if(k == 1)
		{
			copy[1] = origin[s];
			return 2;
		}
		int nextK = k / 2;
		
		int idx = dfs(nextK, s + nextK, e);
		
		int limit = s + nextK;
		
		while(s < limit)
			copy[idx++] = origin[s++]; 
		
		return idx;
	}
	static boolean isSame(int[] a, int[] b) {
		for(int i=1; i<a.length; i++)
			if(a[i] != b[i]) return false;
		return true;
	}
}