//https://www.acmicpc.net/problem/11578
//2초 256MB
//5 4 // 문제수 N(1<=10), 학생들의 수 M(1<=10)
//2 3 4 // 학생 수만큼 풀 수 있는 문제가 주어짐
//2 1 2
//4 1 2 3 4
//1 5
//답 : 2 // 모든 문제를 풀 팀을 못만들면 -1출력
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Main{
	
	static int res = Integer.MAX_VALUE;
	static int G, M;
	static int []student;
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		G = (1 << (Integer.parseInt(st.nextToken())+1)) - 1;
		M = Integer.parseInt(st.nextToken());
		student = new int[M];
		
		for(int i=0; i<M; i++)
		{
			student[i] = 1;
			st = new StringTokenizer(br.readLine());
			int j = Integer.parseInt(st.nextToken());
			while(j-->0)
				student[i] |= (1<<Integer.parseInt(st.nextToken()));
		}
		
		dfs(0, 0);
		
		if(res == Integer.MAX_VALUE)
			res = -1;
		
		System.out.print(res);
	}
	static void dfs(int depth, int bitMask) {
		if(depth >= res) return;
		if(bitMask == G)
		{
			res = depth;
			return;
		}
		if(depth == M) return;
		
		for(int i=0; i<M; i++)
		{
			if((bitMask & student[i]) == student[i])
				continue;
			
			dfs(depth + 1, bitMask | student[i]);
		}
	}
}