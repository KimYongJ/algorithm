//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/14939
import java.util.Arrays;
class Main{
	static int dxy[][] = {{0,0},{1,0},{0,1},{-1,0},{0,-1}};
	
	public static void click(int row, int col, int arr[]) {
		for(int xy[] : dxy) 
		{
			int y = xy[0] + row;
			int x = xy[1] + col;
			if(y >= 0 && x >=0 && y<10 && x<10)
				arr[y] ^= 1<<x;
		}
	}
	public static void main(String[] args)throws Exception{		
		int origin[] = new int[10];
		for(int i=0; i<10; i++)
		{
			for(int j=0,b=9; j<10; j++,b--)
				if((char)System.in.read()== 'O')
					origin[i] |= 1<<b;// 켜진것을 1로 표현
			System.in.read();
		}
		int ans = Integer.MAX_VALUE;
		
		for(int i=0; i<1024; i++)// 첫번째 열을 어떻게 만들지 완전탐색 2의10제곱-1개까지 ..
		{
			int cnt		= 0;
			int arr[]	= Arrays.copyOf(origin, 10);

			// i를 비트로 표현했을 때 1인 것이 스위치를 누르는 것이다. arr의 첫행만 진행
			for(int x=0; x<10; x++)
				if((i & (1<<x)) != 0)
				{
					cnt++;
					click(0, x, arr);				// 해당 위치 입력시 그 위치 포함 상하좌우 모두 반전
				}
			
			for(int y=1; y<10; y++)					// 첫행은 정해졌으니 두번째 행부터 실행
				for(int x=0; x<10; x++)				// 가로 전체 탐색
					if((arr[y-1] & (1<<x)) != 0)	// 바로 위의 행이 꺼져있지 않으면 밑에 행에서 끈다.
					{
						cnt++;
						click(y, x, arr);			// 해당 위치 입력시 그 위치 포함 상하좌우 모두 반전
					}
			
			if(arr[9] == 0)
				ans = Math.min(ans,  cnt);
		}
		if(Integer.MAX_VALUE == ans)
			System.out.print(-1);
		else 
			System.out.print(ans);
	}
}
