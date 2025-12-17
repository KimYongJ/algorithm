//https://www.acmicpc.net/problem/24052
//1초 512MB
//5 7 // 배열 크기 5<=10,000, 변경 횟수 1<=N ^ 2
//4 5 1 3 2 // 원소 값 1<=10^9
//답 : 1 3 4 5 5
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int arr[] = new int[N + 1];
		
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++) arr[i] = Integer.parseInt(st.nextToken());
		
		for(int i=2; i<=N && K != 0; i++)
		{
			int loc = i - 1;
			int newItem = arr[i];
			
			while(1<=loc && newItem < arr[loc] && K > 0)
			{
				arr[loc + 1] = arr[loc];
				loc--;
				K--;
			}
			
			if(K != 0 && loc + 1 != i)
			{
				K--;
				arr[loc + 1] = newItem;
			}
		}
		
		if(K != 0)
		{
			System.out.print(-1);
			return;
		}
		
		StringBuilder sb = new StringBuilder();
		
		for(int i=1; i<=N; i++)
			sb.append(arr[i]).append(' ');
		
		System.out.print(sb);
	}
}