//https://github.com/KimYongJ/algorithm
//https://www.acmicpc.net/problem/1166
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());//1<=십억
		int L = Integer.parseInt(st.nextToken());//1<=십억
		int W = Integer.parseInt(st.nextToken());//1<=십억
		int H = Integer.parseInt(st.nextToken());//1<=십억

		double s = 0;
		double e = Math.min(Math.min(L,W),H);
		double res = 0;
		while(s < e) {
			double mid = (s + e) / 2;
			if((long)(L / mid) * (long)(W / mid) * (long)(H / mid) < N) {
				if(e == mid) break;
				e = mid;
			}else {
				if(s == mid) break;
				s = mid;
				res = mid;
			}
		}
		System.out.print(res);
	}
}