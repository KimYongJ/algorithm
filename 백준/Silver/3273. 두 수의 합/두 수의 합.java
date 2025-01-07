//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/3273
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N			= Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		int arr[]		= new int[N];
		boolean visit[] = new boolean[1_000_001];
		
		for(int i=0; i<N; i++)
			visit[arr[i] = Integer.parseInt(st.nextToken())] = true;
		
		int x = Integer.parseInt(br.readLine());
		int cnt = 0;
		for(int num : arr)
		{
			int target = x - num;
			if(0 <= target && target <= 1_000_000 && visit[target])
				++cnt;
		}
		System.out.print(cnt / 2);
	}
}