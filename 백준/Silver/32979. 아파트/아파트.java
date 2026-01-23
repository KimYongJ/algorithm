//https://www.acmicpc.net/problem/32979
//1초 1024MB
//4 // 참가자수(1<=40)
//2 // 게임횟수(1<=5000)
//2 4 4 3 3 1 2 1 // 손의 위치가 2*N 주어짐, 
//3 12 // 게임 횟수만큼 숫자를 부름(1<=1000)
//답 4 1
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int T = Integer.parseInt(br.readLine());
		ArrayDeque<Integer> q = new ArrayDeque<>();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int len = N * 2;
		
		for(int i=0; i<len; i++)
			q.addLast(Integer.parseInt(st.nextToken()));
		
		StringBuilder sb = new StringBuilder();
		st = new StringTokenizer(br.readLine());
		while(T-->0)
		{
			int target = (Integer.parseInt(st.nextToken()) - 1) % len;   // <-- 수정 포인트
			while(target-->0)
				q.addLast(q.pollFirst());
			
			sb.append(q.peekFirst()).append(' ');
		}
		System.out.print(sb);
	}
}