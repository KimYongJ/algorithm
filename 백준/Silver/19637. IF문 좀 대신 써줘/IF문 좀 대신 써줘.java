//https://github.com/KimYongJ/algorithm
//https://www.acmicpc.net/problem/19637
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Main{
	public static void main(String[] args)throws Exception{
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		String name[] = new String[N];
		int level[] = new int[N];
		for(int i=0; i<N; i++)
		{
			st = new StringTokenizer(br.readLine());
			name[i]	= st.nextToken();
			level[i]= Integer.parseInt(st.nextToken());
		}
		while(M-->0)
		{
			int number = Integer.parseInt(br.readLine());
			int s	= 0;
			int e	= N-1;
			int idx	= -1;
			while(s <= e)
			{
				int mid = (s + e) / 2;
				if(number <= level[mid])
				{
					idx = mid;
					e = mid -1;
				}
				else
				{
					s = mid + 1;
				}
			}
			if(idx != -1) {
				sb.append(name[idx]).append('\n');
			}
		}
		System.out.print(sb.toString());
	}
}