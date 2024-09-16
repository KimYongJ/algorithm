//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/2428
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader 	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N		= Integer.parseInt(br.readLine());	// 솔루션개수 (1<=십만)
		int arr[]	= new int[N];						// 각 솔루션 파일크기(1<=억)
		long cnt	= 0;
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++)
			arr[i] = Integer.parseInt(st.nextToken());
		
		Arrays.sort(arr);
		
		for(int i=0; i<N; i++)
		{
			long target = (long)((arr[i] * 10) / 9);
			
			int s = i + 1;
			int e = N - 1;
			int r = 0;
			while(s <= e)
			{
				int mid = (s + e) >> 1;
				if(arr[mid] <= target) {
					r = mid;
					s = mid + 1;
				}else{
					e = mid - 1;					
				}
			}
			if(r != 0) {
				cnt += r - i;
			}
		}
		
		System.out.print(cnt);
	}
}