//https://www.acmicpc.net/problem/10040
// 1초 128MB
//4 3 // 경기수(1<=1000), 위원수(1<=1000)
//5 // 경기 수만큼 필요 비용이 입력됨(1<=1000)
//3
//1
//4
//4 // 심사 기준 값이 주어짐(1<=1000)
//3
//2
//가장 많은 표를 받은 경기 : 2
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());// 경기수(1<=1000)
		int M = Integer.parseInt(st.nextToken());// 위원수(1<=1000)
		int arr[] = new int[N];
		int cnt[] = new int[N];
		
		for(int i=0; i<N; i++)
			arr[i] = Integer.parseInt(br.readLine());
		
		for(int i=0; i<M; i++)
		{
			int cost = Integer.parseInt(br.readLine());
			
			for(int j=0; j<N; j++)
			{
				if(arr[j] <= cost)
				{
					cnt[j]++;
					break;
				}
			}
		}
		
		int ans = 0;
		
		for(int i=1; i<N; i++)
			if(cnt[i] > cnt[ans]) ans = i;
		
		System.out.print(ans + 1);
	}
}