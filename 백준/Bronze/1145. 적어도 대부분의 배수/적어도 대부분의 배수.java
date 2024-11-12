//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/1145
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int num[] = new int[5];
		
		for(int i=0; i<5; i++)
			num[i] = Integer.parseInt(st.nextToken());
		
		int res = 0;
		int cnt = 0;
		while(cnt < 3)
		{
			++res;
			cnt = 0;
			for(int n : num)
				if(res % n == 0)
					++cnt;
		}
		System.out.print(res);
	}
}