//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/2866
import java.util.HashSet;
class Main{
	static int read() throws Exception {// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	public static void main(String[] args)throws Exception{
		int Y				= read();	// 2<=천
		int X				= read();	// 2<=천
		char base[][]		= new char[Y][X];
		StringBuilder arr[]	= new StringBuilder[X];
		HashSet<String> set = new HashSet<>();
		
		for(int y=0; y<Y; y++)
		{
			for(int x=0; x<X; x++)
				base[y][x] = (char)System.in.read();
			System.in.read();
		}
		
		for(int x=0; x<X; x++)
		{
			arr[x] = new StringBuilder();
			for(int y=0; y<Y; y++)
				arr[x].append(base[y][x]);
		}
		
		int s	= 1;
		int e	= Y - 1;
		int res = 0; // 지울 수 있는 가장 밑에 행
		
		while(s <= e)
		{
			int mid			= (s + e) >> 1;
			boolean flag	= true;
			
			for(int x=0; x<X; x++)
			{
				String str = arr[x].substring(mid);
				if(set.contains(str))
					flag = false;
				set.add(str);
			}
		
			if(flag)
			{
				res = mid;
				s = mid + 1;
			}
			else
				e = mid - 1;
		}
		
		 System.out.print(res);
	}
}