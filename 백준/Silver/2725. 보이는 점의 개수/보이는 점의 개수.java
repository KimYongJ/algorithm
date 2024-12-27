//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/2725
import java.io.BufferedReader;
import java.io.InputStreamReader;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());	// 1<=1000
		int o[] = new int[T];
		int max = 0;
		for(int i=0; i<T; i++)
		{
			o[i] = Integer.parseInt(br.readLine());	// 1<=1000
			if(max < o[i])
				max = o[i];
		}
		
		int psum[] = new int[max+1];
		psum[1] = 3;
		for(int i=2; i<=max; i++)
		{
			int cnt = 0;
			for(int j=1; j<i; j++)
				if(gcd(i,j) == 1)
					++cnt;
			psum[i] += psum[i-1] + (cnt<<1);
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<T; i++)
			sb.append(psum[o[i]]).append('\n');
		
		System.out.print(sb);
	}
	public static int gcd(int a, int b) {
		if(b == 0) return a;
		return gcd(b, a%b);
	}
}