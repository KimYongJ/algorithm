//https://www.acmicpc.net/problem/8922
//1초 128MB
//4 // 테스트 케이스 수
//4 // 배열 크기 (3<=15)
//8 11 2 7 // 0<=1000, 루프까지 단계는 1000을 넘지 않음
//5
//4 2 0 2 0
//7
//0 0 0 0 0 0 0
//6
//1 2 3 1 2 3
//답 : 
//ZERO // 0이 가능한 경우
//LOOP // 루프에 빠지는 경우
//ZERO
//LOOP
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		while(T-->0)
		{
			int N = Integer.parseInt(br.readLine());// 배열 크기 (3<=15)
			int arr[] = new int[N];
			int brr[] = new int[N];
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<N; i++)
				arr[i] = Integer.parseInt(st.nextToken());
			
			boolean loop = true;
			
			for(int i=0; i<=1000; i++)
			{
				boolean zero = true;
				
				brr[N-1] = Math.abs(arr[N-1] - arr[0]);
				if(brr[N - 1] != 0)
					zero = false;
				for(int j=0; j<N - 1; j++)
				{
					brr[j] = Math.abs(arr[j] - arr[j + 1]);
					if(brr[j] != 0) zero = false;
				}
				
				int tmp[] = arr;
				arr = brr;
				brr = tmp;
				
				if(zero)
				{
					loop = false;
					break;
				}
			}
			
			sb.append(loop ? "LOOP" : "ZERO").append('\n');
		}
		System.out.print(sb);
	}
}