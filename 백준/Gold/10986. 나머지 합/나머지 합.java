//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/10986
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N		= Integer.parseInt(st.nextToken());	//원소개수(1<=10의6승)
		int M		= Integer.parseInt(st.nextToken());	//나눌값(2<=천)
		long psum[]	= new long[N+1];
		long C[]	= new long[M];
		
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++)
		{
			psum[i] += psum[i-1] + Integer.parseInt(st.nextToken());
			++C[(int)(psum[i] % M)];
		}
		
		long res = C[0];
		for(int i=0; i<M; i++)
			res += C[i] < 2 ? 0 : C[i]*(C[i]-1) / 2 ;
		
		System.out.print(res);
	}
}
