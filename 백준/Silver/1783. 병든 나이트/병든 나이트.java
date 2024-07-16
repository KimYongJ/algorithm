// https://github.com/kimyongj/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Main{
	
	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int Y	= Integer.parseInt(st.nextToken());
		int X	= Integer.parseInt(st.nextToken());
		int res = 1;
		if(Y == 2) {
			res = Math.min((X+1)/2, 4);
		}else if(Y>=3) {
			if(X < 7) {
				res = Math.min(X, 4);
			}else {
				res = X-2;
			}
		}
		System.out.print(res);
	}
}