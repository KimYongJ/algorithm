//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/2571
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N			= Integer.parseInt(br.readLine());// 색종이 수(0<=100)
		int max			= 0;
		boolean map[][]	= new boolean[100][100];
		
		while(N-->0)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			int X = Integer.parseInt(st.nextToken());// 도화지와 색종이의 왼쪽변 사이거리 
			int Y = 99 - Integer.parseInt(st.nextToken()) - 9;// 도화지와 색종이의 아래쪽 변 사이의 거리
			
			for(int y=Y; y<Y+10; y++)
				for(int x=X; x<X+10; x++)
					map[y][x] = true;
		}
		// 히스토그램으로 변환시켜 넓이를 구하기 위해 일차원 배열 생성
		int height[] = new int[100];	// 행마다 높이 누적합
		
		for(int y=0; y<100; y++)
		{
			for(int x=0; x<100; x++)
			{
				if(map[y][x])			// 해당 위치에 색종이가 있다면 + 1
					height[x]++;
				else					// 생종이가 없다면 거기부터는 높이가 0
					height[x] = 0;
			}
			max = Math.max(max, getArea(height));
		}
		System.out.print(max);
	}
	public static int getArea(int height[]) {
		int[] stack = new int[102];	// 빠른 연산을 위해 스택을 직접 구현, 스택에 담는것은 가로 인덱스임!
		int stackIdx = -1;
		int max = 0;
		
		for(int i=0; i<=100; i++)	// height배열은 99까지 가능하지만, 스택에 남은 데이터를 끝내기 위해 100까지
		{
			int h = i==100 ? 0 : height[i];	// 끝애 도달하면 h는 0이된다.
			// 스택이 비어있지 않고, 스택의 최상단이 현재보다 크다면, 즉 현재 값이 이전 보다 작다면
			while(0<=stackIdx && h < height[stack[stackIdx]])
			{
				int H = height[stack[stackIdx--]];	// i(현재)인덱스보다 큰 바로 전값(스택에있는값)을 높이르 가져옴
				int W = i;						// 넓이는 기본 현재인덱스로 하지만,
				if(0<=stackIdx)			// 스택이 비어있지 않다면
					W = i - stack[stackIdx] - 1;	// 바로 위에서 pop한 후 남아있는 스택의 최상단 값을 빼고 1을 더빼서 넓이를 구함
				// 위 if문은 알고리즘의 핵심으로 가장 큰 높이의 넓이를 구하는 것으로 이해하면 된다.
				// 스택에는 가로 인덱스만 담기기 때문에 스택에 있는 값(가로인덱스값)과 i(가로인덱스) 만으로 현재 H의 넓이를 구할 수 있다.
				// 스택에 데이터가 쌓이는 기준 자체가 h < height[stack.peek()] 조건에 의해 오름차순으로만 들어간다

				max = Math.max(H*W, max);
			}
			stack[++stackIdx] = i;			// 스택에 담는 것은 가로인덱스이다
		}
		return max;
	}
}