//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/17245
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int s = 1;
		int e = 0;
		long total = 0;
		ArrayList<Integer> list = new ArrayList<>();
		for(int i=0; i<N; i++)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++)
			{
				int n = Integer.parseInt(st.nextToken());
				if(n > 0)
				{
					e = Math.max(e, n);
					list.add(n);
					total += n;
				}
			}
		}
		total = (long)Math.ceil(total / 2.0);
		int res = 0;
		
		while(s <= e)
		{
			int mid		= (s + e) >> 1;
			long sum	= 0;
			for(int l : list)
				sum += Math.min(mid, l);
			
			if(total <= sum)
			{
				res = mid;
				e = mid - 1;
			}else
				s = mid + 1;
		}
		System.out.print(res);
	}
}