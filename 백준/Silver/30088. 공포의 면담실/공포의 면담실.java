//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/30088
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N		= Integer.parseInt(br.readLine());
		int psum[]	= new int[N];
		
		for(int i=0; i<N; i++)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			int m	= Integer.parseInt(st.nextToken());
			while(m-->0)
				psum[i] += Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(psum);
		
		int sum = psum[0];
		for(int i=1; i<N; i++)
			sum += psum[i] += psum[i-1];
		
		System.out.print(sum);
	}
}