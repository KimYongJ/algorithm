//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/12234
//5초 / 512MB
//요약 : M크기 안에 최대 2개 이하로 원소들을 채워 넣을 때 필요한 최소 박스 개수 
import java.util.Arrays;
class Main{
	public static void main(String[] args)throws Exception{
		StringBuilder	sb = new StringBuilder();
		int T = read();	// 테스트케이스 수 1<=100
		for(int i=1; i<=T; i++)
		{
			int N		= read();
			int M		= read();
			int arr[]	= new int[N];
			
			for(int j=0; j<N; j++)
				arr[j] = read();
			
			Arrays.sort(arr);
			
			int c = 0;
			int s = 0;
			int e = N-1;
			
			while(s<=e)
			{
				if(arr[s] + arr[e] <= M)
					++s;
				
				--e;
				++c;
			}
			
			sb.append("Case #").append(i).append(": ").append(c).append('\n');
		}
		System.out.print(sb);
	}
	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
}