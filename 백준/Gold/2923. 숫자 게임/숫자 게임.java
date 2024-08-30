// https://github.com/kimyongj/algorithm
// https://www.acmicpc.net/problem/2923
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		int A[] = new int[101];
		int B[] = new int[101];
		while(T-->0)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			A[Integer.parseInt(st.nextToken())]++;
			B[Integer.parseInt(st.nextToken())]++;
			int AA[] = new int[101];
			int BB[] = new int[101];
			for(int i=1; i<=100; i++) {
				AA[i] = A[i];
				BB[i] = B[i];
			}
			int left = 0;
			int right = 100;
			int max = 0;
			while(true) 
			{
				while(left<=100 && AA[left]==0)left++;
				while(right>=0 && BB[right]==0)right--;
				if(left > 100 || right < 0) break;
				max = Math.max(max, left + right);
				
				int min = Math.min(AA[left], BB[right]);
				AA[left] -= min;
				BB[right] -= min;
			}
			sb.append(max).append('\n');
		}
		System.out.print(sb.toString());
	}
}
