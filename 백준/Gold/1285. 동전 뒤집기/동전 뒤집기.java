// https://github.com/kimyongj/algorithm
// https://www.acmicpc.net/problem/1285
import java.io.BufferedReader;
import java.io.InputStreamReader;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N		= Integer.parseInt(br.readLine());// 범위 0<=20
		int row[]	= new int[N]; // 
		for(int i=0; i<N; i++)
		{
			String str = br.readLine();
			for(int j=0; j<N; j++)
				row[i] |= str.charAt(j) == 'T' ? (1<<N-j-1) : 0;
		}
		
		int ans = Integer.MAX_VALUE;
		
		for(int s=0; s<(1<<N); s++)
		{
			int sum = 0;
			for(int x=0; x<N; x++) 
			{
				int Tcnt = Integer.bitCount(row[x] ^ s);
				sum += Math.min(Tcnt,  N-Tcnt);
			}
			ans = Math.min(ans, sum);
		}
		System.out.print(ans);
	}
}