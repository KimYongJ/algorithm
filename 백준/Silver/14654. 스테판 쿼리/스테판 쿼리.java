//https://www.acmicpc.net/problem/14654
//2초 256MB
//5	// 라운드 수(1<=300)
//2 3 1 3 1 // 가위(1), 바위(2) 보(3)
//1 1 2 3 2
//가장긴 연승 횟수 출력 : 2
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int res = 0;// 가장 긴 연승 횟수
		int now = 0;// 지금까지 연승 횟수
		int who = -1;// 현재 이기고있는 팀 번호
		int first = 0;// 새로 출전한 선수의 팀
		int arr[] = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++)
			arr[i] = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++)
		{
			int a = arr[i];
			int b = Integer.parseInt(st.nextToken());
			
			int win = getWin(a,b);// 0은 비긴거, 1은 a가 이긴거, 2는 b가이긴거
			
			if(win == 1) // a가 이긴경우
			{
				if(who == 1) ++now;
				else now = 1;
				
				who = 1;
				first = 2;
			}
			else if(win == 2)// b가 이긴경우
			{
				if(who == 2) ++ now;
				else now = 1;
				who = 2;
				first = 1;
			}
			else
			{
				now = 1;
				
				if(first == 1)
				{
					who = 1;
					first = 2;
				}
				else
				{
					who = 2;
					first = 1;
				}
			}
				
			res = Math.max(res, now);
		}
		System.out.print(res);
	}
	static int getWin(int a, int b) {
		if(a == b) return 0;
		
		if((a == 1 && b == 3) || (a==2 && b==1) || (a==3 && b==2))
			return 1;
		
		return 2;
	}
}