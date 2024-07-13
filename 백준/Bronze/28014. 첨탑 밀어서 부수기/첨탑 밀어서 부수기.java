//https://github.com/KimYongJ/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		int cnt = 0, before = 0;
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++)
		{
			int now = Integer.parseInt(st.nextToken());
			if(now >= before) 
			{
				cnt++;
			}
			before = now;
		}
		System.out.print(cnt);
	}
}