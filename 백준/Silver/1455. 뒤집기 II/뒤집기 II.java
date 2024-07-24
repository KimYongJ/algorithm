// https://github.com/kimyongj/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int Y		= Integer.parseInt(st.nextToken());
		int X		= Integer.parseInt(st.nextToken());
		int res		= 0;
		int cnt[]	= new int[X];
		int arr[][]	= new int[Y][X];
		for(int y=0; y<Y; y++) 
		{
			String str = br.readLine();
			for(int x=0; x<X; x++)
				arr[y][x] = str.charAt(x)-'0';
		}
		
		for(int y=Y-1; y>=0; y--)
			for(int x=X-1; x>=0; x--)
				if((cnt[x] + arr[y][x])%2 == 1) // 해당 칸이 뒷면(1)이라면 
				{
					res++;
					for(int x1=0; x1<=x; x1++)cnt[x1]++;
				}

		System.out.print(res);
	}
}