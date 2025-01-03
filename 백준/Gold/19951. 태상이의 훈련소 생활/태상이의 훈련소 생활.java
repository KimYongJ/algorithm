//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/19951
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N		= Integer.parseInt(st.nextToken());	//구덩이개수(1≤100,000)
		int M		= Integer.parseInt(st.nextToken());	//조교수(1≤100,000)
		int arr[]	= new int[N+2];
		int psum[]	= new int[N+2];
		
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++)
			arr[i] = Integer.parseInt(st.nextToken());
		
		while(M-->0)
		{
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());// k가 양수인경우 a,b범위내 흙 추가, 음수는 흙 감소
			psum[a] += k;
			psum[b+1] += -k;
		}
		StringBuilder sb = new StringBuilder();
		for(int i=1; i<=N; i++)
			sb.append(arr[i] + (psum[i] += psum[i-1])).append(' ');

		System.out.print(sb);
	}
}