//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/20116
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N		= Integer.parseInt(st.nextToken());	// 상자개수(1<=이십만)
		int L		= Integer.parseInt(st.nextToken());	// 상자 사이즈(1<=십억)
		double psum[] = new double[N+1];
		double arr[][]= new double[N+1][2];
		
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++)
		{
			psum[i]		= Integer.parseInt(st.nextToken());
			arr[i][0]	= psum[i] - L;
			arr[i][1]	= psum[i] + L;
		}
		
		for(int i=N-1; i>0; i--)
			psum[i] += psum[i + 1];

		for(int i=N-1, j=1; i>0; i--,j++)
		{
			double mid = psum[i+1] / j;
			if(mid <= arr[i][0] || arr[i][1] <= mid) {
				System.out.print("unstable");
				return;
			}
		}
		System.out.print("stable");
	}
}