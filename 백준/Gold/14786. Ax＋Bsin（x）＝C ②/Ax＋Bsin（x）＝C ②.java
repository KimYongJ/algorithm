//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/14786
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int A = Integer.parseInt(st.nextToken());	// 0<=십만
		int B = Integer.parseInt(st.nextToken());	// 0<=십만
		int C = Integer.parseInt(st.nextToken());	// 0<=십만
		// Ax + Bsin(x) = C를 만족하는 X찾기
		double diff = 0.0000000009;
		double s = 0;
		double e = 100_001;
		double x = 0;
		while(e - s > diff)
		{
			double mid = (s + e) / 2;
			double cal = A * mid + B * Math.sin(mid);
			
			if(cal <= C)
			{
				x = mid;
				s = mid;
			}
			else
				e = mid;
		}
		System.out.print(x);
	}
}
