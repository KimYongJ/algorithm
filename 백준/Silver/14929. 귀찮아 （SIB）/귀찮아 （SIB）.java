//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/14929
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N		= Integer.parseInt(br.readLine());
		int arr[]	= new int[N+1];
		int psum[]	= new int[N+1];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++)
			psum[i] = (arr[i] = Integer.parseInt(st.nextToken())) + psum[i-1];
		
		long res = 0;
		for(int i=1; i<=N; i++)
			res += arr[i] *(psum[N]-psum[i]);
		
		System.out.print(res);
	}
}