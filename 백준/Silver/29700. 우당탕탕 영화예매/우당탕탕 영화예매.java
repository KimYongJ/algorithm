//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/29700
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int Y		= Integer.parseInt(st.nextToken());	// 세로1<=천
		int X		= Integer.parseInt(st.nextToken());	// 가로1<=오천
		int K		= Integer.parseInt(st.nextToken());	// 인원수 1<=10
		int res		= 0;
		for(int y=0; y<Y; y++)
		{
			String str = br.readLine();
			int cnt = 0;
			for(int x=0; x<X; x++)
			{
				if(str.charAt(x) == '0')
				{
					if(K<= ++cnt)
						++res;
				}
				else cnt = 0;
			}
		}
		
		System.out.print(res);
	}
}