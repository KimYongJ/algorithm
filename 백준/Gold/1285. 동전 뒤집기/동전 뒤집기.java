//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/1285
import java.io.BufferedReader;
import java.io.InputStreamReader;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N		= Integer.parseInt(br.readLine());// 범위 0<=20
		int row[]	= new int[N];
		for(int i=0; i<N; i++)
		{
			String str = br.readLine();
			for(int j=0; j<N; j++)
				row[i] |= str.charAt(j) == 'T' ? (1<<j) : 0;
		}
		
		int ans = Integer.MAX_VALUE;
		
		for(int s=0; s<(1<<N); s++) // 특정 열에 대해 뒤집을지 말지 결정 ex)N=3 -> 000, 001, 002 ... 111까지
		{
			int sum = 0;
			for(int x=0; x<N; x++) // 각 행마다 위에서 정한 s를 다 대입함
			{
				int Tcnt = Integer.bitCount(row[x] ^ s);// 가로행에다 열에 대한 값(s)를 도입해 뒤집음
				sum += Math.min(Tcnt,  N-Tcnt);
			}
			ans = Math.min(ans, sum);
		}
		System.out.print(ans);
	}
}