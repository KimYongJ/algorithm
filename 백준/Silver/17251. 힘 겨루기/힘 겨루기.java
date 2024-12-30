//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/17251
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N		= Integer.parseInt(br.readLine());	// 2<=백만
		int left[] 	= new int[N];
		int right[]	= new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++)
			left[i] = right[i] = Integer.parseInt(st.nextToken());
		
		for(int l=1,r=N-2;l<N; l++,r--)
		{
			left[l] = Math.max(left[l - 1], left[l]);
			right[r] = Math.max(right[r+1], right[r]);
		}
		int lcnt = 0;
		int rcnt = 0;
		for(int i=0; i<N-1; i++) {
			if(left[i] < right[i+1])
				rcnt++;
			else if(right[i+1] < left[i])
				lcnt++;
		}
		if(lcnt == rcnt)
			System.out.print('X');
		else
			System.out.print(lcnt < rcnt ? 'B' : 'R');
	}
}