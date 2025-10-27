//https://www.acmicpc.net/problem/24051
//1초 512MB
//5 7 // 배열의 크기(5<=10,000), 저장 횟수(1<=N^2)
//4 5 1 3 2 // 서로 다른 수 1<=10^9
//저장횟수가 K보다 작으면 -1 출력 : 5
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());// 배열의 크기(5<=10,000)
		int K = Integer.parseInt(st.nextToken());// 저장 횟수(1<=N^2)
		int A[] = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++)
			A[i] = Integer.parseInt(st.nextToken());
		
		for(int i=1; i<N; i++)
		{
			int loc = i - 1;
			int newItem = A[i];
			
			while(0<=loc && newItem < A[loc])
			{
				if(--K == 0)
				{
					System.out.print(A[loc]);
					return;
				}
				A[loc + 1] = A[loc];
				loc--;
			}
			
			if(loc + 1 != i)
			{
				A[loc + 1] = newItem;
				if(--K == 0)
				{
					System.out.print(newItem);
					return;
				}
			}
			
		}
		
		System.out.print(-1);
	}
}