// https://github.com/kimyongj/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		int s,e, min = 9999999, max = 0;
		for(int i=0; i<N; i++) 
		{
			st = new StringTokenizer(br.readLine());
			s = Integer.parseInt(st.nextToken());
			e = Integer.parseInt(st.nextToken());
			if(min > e) {
				min = e;
			}
			if(max < s) {
				max = s;
			}
		}
		int result = max - min;
		System.out.print(result > 0 ? result : 0);
	}
}
