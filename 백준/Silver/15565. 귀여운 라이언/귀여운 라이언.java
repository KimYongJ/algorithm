//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/15565
//1초 / 256MB
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N		= Integer.parseInt(st.nextToken());	//숫자의 개수(1<=10의6승)
		int K		= Integer.parseInt(st.nextToken());	//1의최소개수(1<=N)
		int arr[]	= new int[N];	// 슬라이딩 윈도우 활용, arr에는 1의 인덱스만 담는다.
		int len		= 0;
		int res		= 1<<30;
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++)
			if(Integer.parseInt(st.nextToken()) == 1)
				arr[len++] = i;
		
		// k만큼 옮겨가며 길이를 계산
		for(int i=K-1; i<len; i++)
			res = Math.min(res, arr[i] - arr[i-K+1] + 1);
		
		System.out.print(res == (1<<30) ? -1 : res);
	}
}