//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/14476
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N			= Integer.parseInt(br.readLine());
		int arr[]		= new int[N];
		int forward[]	= new int[N];
		int backward[]	= new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++)
			arr[i] = Integer.parseInt(st.nextToken());
		forward[0] = arr[0];
		backward[N-1] = arr[N-1];
		
		for(int i=1; i<N; i++)
			forward[i] = gcd(forward[i-1], arr[i]);
		for(int i=N-2; i>=0; i--)
			backward[i] = gcd(backward[i+1], arr[i]);
		
		int idx = -1, maxGcd = 0;
		
		for(int i=0; i<N; i++) {
			int curGcd = 0;
			if(i == 0)
			{
				curGcd = backward[i+1];
			}
			else if(i == N -1)
			{
				curGcd = forward[N-2];
			}
			else
			{
				curGcd = gcd(forward[i-1], backward[i+1]);
			}
			if(maxGcd < curGcd) {
				maxGcd = curGcd;
				idx = i;
			}
		}
		if(arr[idx] % maxGcd == 0)
			System.out.print(-1);
		else
			System.out.print(new StringBuilder().append(maxGcd).append(' ').append(arr[idx]));
	}
	public static int gcd(int a, int b) {
		int tmp;
		while(b!=0)
		{
			tmp = b;
			b = a%b;
			a = tmp;
		}
		return a;
	}
}