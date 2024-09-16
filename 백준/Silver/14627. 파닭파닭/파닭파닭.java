//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/14627
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int S = Integer.parseInt(st.nextToken());	// 시장에서 사온 파의 개수(1<=백만)
		int C = Integer.parseInt(st.nextToken());	// 파닭의 수
		int arr[] = new int[S];						// 파의길이 (1<=십억)
		long sum = 0;
		for(int i=0; i<S; i++)
		{
			arr[i] = Integer.parseInt(br.readLine());
			sum += arr[i];
		}
		
		int maxlen = 0;
		int s = 1;
		int e = 1_000_000_001;
		while(s <= e)
		{
			int mid = (s + e) >> 1;
			int cnt = 0;
			for(int a : arr)
				cnt += a / mid;

			if(cnt < C)
				e = mid - 1;
			else
			{
				s = mid + 1;
				maxlen = mid;
			}
		}

		System.out.print(sum - ((long)maxlen * C));
	}
}