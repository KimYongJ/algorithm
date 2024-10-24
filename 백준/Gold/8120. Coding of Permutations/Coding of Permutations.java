//https://github.com/KimYongJ
//https://www.acmicpc.net/problem/8120
import java.util.ArrayList;

class Main{
	
	static int read() throws Exception {// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	
	public static void main(String[] args)throws Exception{
		StringBuilder sb = new StringBuilder();
		ArrayList<Integer> list = new ArrayList<>();
		
		int N		= read();
		int arr[]	= new int[N+1];
		int res[]	= new int[N+1];
		
		for(int i=1; i<=N; i++)
		{
			arr[i] = read();
			list.add(i);
		}
		
		for(int i=N; i>=1; i--)
		{
			int idx = list.size() - arr[i] - 1;
			if(0<=idx)
			{
				res[i] = list.get(idx);
				list.remove(idx);
			}
			else
			{
				System.out.print("NIE");
				return;
			}
		}
		
		for(int i=1; i<=N; i++)
			sb.append(res[i]).append('\n');
		
		System.out.print(sb.toString());
	}
}