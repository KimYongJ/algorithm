//https://www.acmicpc.net/problem/14594
//1초 512MB
//8// 동아리방의 개수(2<=100)
//6// 부시는 횟수(0<=100)
//5 8// 방의 번호 1<=x < y <= N
//6 7
//4 7
//5 6
//1 3
//1 5
//답 : 1
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());// 동아리방의 개수(2<=100)
		int M = Integer.parseInt(br.readLine());// 부시는 횟수(0<=100)
		boolean room[] = new boolean[N];
		while(M-->0)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken())-1;
			int y = Integer.parseInt(st.nextToken())-1;
			
			while(x<y)
				room[x++] = true;
		}
		
		int cnt = 0;
		
		for(boolean r : room) if(!r)++cnt;
		
		System.out.print(cnt);
	}
}