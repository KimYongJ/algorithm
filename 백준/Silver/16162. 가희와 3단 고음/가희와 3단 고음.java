// https://github.com/kimyongj/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 참가자들의 음의 개수 
		int A = Integer.parseInt(st.nextToken()); // 고음의 첫항
		int D = Integer.parseInt(st.nextToken()); // 공차
		int X = 0;
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) 
		{
			int num = Integer.parseInt(st.nextToken());
			if(num == A + (X*D))
			{
				X++;
			}
		}
		System.out.print(X);
	}
}