//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/2866
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br 	= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st	= new StringTokenizer(br.readLine());
		int Y				= Integer.parseInt(st.nextToken());	// 2<=천
		int X				= Integer.parseInt(st.nextToken());	// 2<=천
		char base[][]		= new char[Y][X];
		StringBuilder arr[]	= new StringBuilder[X];
		HashSet<String> set = new HashSet<>();
		
		for(int y=0; y<Y; y++)
			base[y] = br.readLine().toCharArray();
		
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