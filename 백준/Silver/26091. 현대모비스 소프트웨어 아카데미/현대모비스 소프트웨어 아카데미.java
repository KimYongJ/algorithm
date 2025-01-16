//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/26091
//1초 / 1024MB
import java.util.Arrays;
class Main{
	public static void main(String[] args)throws Exception{
		int N		= read();	//총인원(1<=100,000)
		int M		= read();	//팀의 최소 능력치(1<=10의9승)
		int cnt		= 0;
		int arr[]	= new int[N];

		for(int i=0; i<N; i++)
			arr[i] = read();
		
		Arrays.sort(arr);
		
		int s = 0;
		int e = N - 1;
		while(s<e)
		{
			if(arr[e] + arr[s] >= M)
			{
				e--;
				s++;
				cnt++;
			}
			else
				s++;
		}
		System.out.print(cnt);
	}
	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
}