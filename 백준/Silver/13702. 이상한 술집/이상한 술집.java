//https://github.com/KimYongJ/algorithm
//https://www.acmicpc.net/problem/13702
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Main{

	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 막걸리 주전자 개수 N( 만이하 )
		int K = Integer.parseInt(st.nextToken()); // 나눌 사람의 수 K( 백만이하 )
		int arr[] = new int[N]; // 막걸리 용량 ( 0 <= int형최대 )
		long s = 1,e = 0;
		for(int i=0; i<N; i++)
		{
			arr[i] = Integer.parseInt(br.readLine());
			e = Math.max(e, arr[i]);
		}
		
		long res = 0;
		while(s <= e)
		{
			long mid = (s + e) / 2;
			int k = K;
			
			for(int a : arr)
				k -= a / mid;
			
			if(k <= 0)
			{
				res = mid;
				s = mid + 1;
			}
			else
				e = mid - 1;
		}
		System.out.print(res);
	}
}