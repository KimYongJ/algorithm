//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/1214
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{

	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int D		= Integer.parseInt(st.nextToken());
		int big		= Integer.parseInt(st.nextToken());
		int small	= Integer.parseInt(st.nextToken());
		
		if(big < small)
		{
			int tmp = big;
			big = small;
			small = tmp;
		}
		
		if(D % big == 0 || D % small == 0)
		{
			System.out.print(D);
			return;
		}
		
		int ans = (D / big) * big + big;
		int tmp = ans;
		int len = tmp / big;
		for(int i=1; i<= len; i++)
		{
			int blankSpace = tmp - big * i;
			if((D-blankSpace) % small == 0)
			{
				ans = D;
				break;
			}
			
			blankSpace += ((D-blankSpace) / small) * small + small;
			
			if(ans == blankSpace)
				break;
			
			ans = Math.min(ans, blankSpace);
		}
		System.out.print(ans);
	}
}