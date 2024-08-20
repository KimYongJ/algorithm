// https://github.com/kimyongj/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());	// 최대길이
		int M = Integer.parseInt(st.nextToken());	// 놓을 심판 개수
		int K = Integer.parseInt(st.nextToken());	// 심판이 자리잡을 수 있는 곳
		int arr[]		= new int[K];				// 심판이 들어갈 수 있는 위치
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<K; i++)
			arr[i] = Integer.parseInt(st.nextToken());
		
		int start = 0;
		int end = N;
		int mid;
		int res = 0;
		while(start <= end)
		{
			mid = (start + end) / 2;
			int m = M-1;
			int idx = 0;
			for(int i=1; i<K; i++)
			{
				if(arr[i] - arr[idx] >= mid) 
				{
					idx = i;
					if(--m==0) 
					{
						res = mid;
						start = mid+1;
						break;
					}
				}
			}
			if(m != 0)
				end = mid-1;
		}
		
		StringBuilder sb = new StringBuilder("1");
		int idx = 0;
		M--;
		for(int i=1; i<K; i++) {
			if(M > 0 && arr[i] - arr[idx] >= res) {
				idx = i;
				sb.append('1');
				M--;
			}else sb.append('0');
		}
		System.out.print(sb.toString());
	}
}