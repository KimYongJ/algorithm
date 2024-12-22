//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/10025
import java.util.Arrays;
class Pos{
	int val, idx;
	Pos(int v, int i){val=v; idx=i;}
}
class Main{
	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	public static void main(String[] args)throws Exception{
		int N		= read();		// 양동이개수(1<=십만)
		int K		= read()*2;	// 이동가능거리(1<=이백만)
		Pos arr[]	= new Pos[N];
		
		for(int i=0; i<N; i++)
			arr[i] = new Pos(read(), read());
		
		int max = 0;
		
		Arrays.sort(arr,(a,b)-> a.idx - b.idx);
		max = Math.max(cal(arr, N , K), max);
		
		System.out.print(max);
	}
	public static int cal(Pos arr[], int N, int K) {
		int s	= 0;
		int e	= 0;
		int max = 0;
		int sum = 0;
		
		while(e<N)
		{
			while(e<N && arr[e].idx - arr[s].idx<= K)
			{
				sum += arr[e].val;
				++e;
			}
			
			max = Math.max(sum, max);
			
			sum -= arr[s].val;
			++s;
			
		}
		return max;
	}
}