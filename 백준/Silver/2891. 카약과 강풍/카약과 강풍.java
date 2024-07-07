// https://github.com/kimyongj/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());	// 팀수
		int S = Integer.parseInt(st.nextToken());	// 손상된 팀수
		int R = Integer.parseInt(st.nextToken());	// 더 가져온 팀수
		int arr[] = new int[N+2];					// 카약의 수

		st = new StringTokenizer(br.readLine());
		while(S-->0) 
		{
			arr[Integer.parseInt(st.nextToken())]--;
		}
		
		st = new StringTokenizer(br.readLine());
		while(R-->0) 
		{
			arr[Integer.parseInt(st.nextToken())]++;
		}
		
		int result = 0;
		for(int i=1; i<=N; i++) 
		{
			if(arr[i] < 0) 
			{
				if(arr[i-1]>0) 
				{
					arr[i-1]--;
				}
				else if(arr[i+1]>0) 
				{
					arr[i+1]--;
				}
				else 
				{
					result++;
				}
			}
		}
		System.out.print(result);
	}
}