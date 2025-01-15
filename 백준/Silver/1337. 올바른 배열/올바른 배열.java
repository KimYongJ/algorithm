//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/1337
//2초 / 128MB
import java.util.Arrays;
class Main{
	public static void main(String[] args)throws Exception{
		int N		= read();//(1<=50)
		int arr[]	= new int[N];
		int	diff	= 0;
		for(int i=0; i<N; i++)
			arr[i] = read();//(0<=10억)
		
		Arrays.sort(arr);
		
		int s = 0;
		int e = 0;
		while(e<N)
		{
		    // 두 인덱스 사이 값의 차이가 5 미만이면, 그 구간은 1씩 차이나는 연속 구간으로 만들 수 있다.
		    // 이 구간에서 실제로 연속된 5개의 숫자를 만들기 위해 필요한 숫자의 개수를 확인하려면,
		    // 현재 구간의 길이(e - s)를 계산한 후 4 - (구간 길이)를 구한다.
		    // 예를 들어, 구간 길이가 2라면 4 - 2 = 2개의 숫자가 필요함을 알 수 있다.
			// 두 인덱스 차이에 +1을 하면 그 구간 사이 들어있는 숫자의 개수가 나오는 것이다.
			if(arr[e] - arr[s] < 5)
			{
				diff = Math.max(diff, e - s);
				++e;
			}
			else
				++s;
		}
		
		System.out.print(4 - diff);
	}
	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
}
