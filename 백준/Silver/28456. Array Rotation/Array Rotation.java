//https://www.acmicpc.net/problem/28456
//1초 1024MB
//3 // 배열 크기(1<=100)
//1 2 3 // 원소 (1<=1000)
//4 5 6
//7 8 9
//3 // 질의 수 (1<=100)
//1 1 // i번째 행을 택하여 마지막 원소 삭제 후 첫번째로 추가
//1 2
//2 // 원래 있던 배열의 모든 원소에 대해 i번째 행 j번째 열의 원소는 A의 j번째 행 N-i+1번째 열의 원소가 됨
//답
//7 6 3
//8 4 1
//9 5 2
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		int arr[][] = new int[N][N];
		int brr[][] = new int[N][N];
		
		for(int y=0; y<N; y++)
		{
			st = new StringTokenizer(br.readLine());
			for(int x=0; x<N; x++)
				arr[y][x] = Integer.parseInt(st.nextToken());
		}
		
		int Q = Integer.parseInt(br.readLine());
		while(Q-->0)
		{
			st = new StringTokenizer(br.readLine());
			int cmd = Integer.parseInt(st.nextToken());
			
			if(cmd == 1)
			{
				int idx = Integer.parseInt(st.nextToken()) - 1;
				int last = arr[idx][N - 1];
				for(int y=N - 1; y>0; y--)
					arr[idx][y] = arr[idx][y - 1];
				arr[idx][0] = last;
				continue;
			}
			
			for(int y=0; y<N; y++)
				for(int x=0; x<N; x++)
					brr[x][N-y-1] = arr[y][x];
			
			int tmp[][] = arr;
			arr = brr;
			brr = tmp;
		}
		
		StringBuilder sb = new StringBuilder();
		for(int y=0; y<N; y++)
		{
			for(int x=0; x<N; x++)
				sb.append(arr[y][x]).append(' ');
			sb.append('\n');
		}
		System.out.print(sb);
	}
}