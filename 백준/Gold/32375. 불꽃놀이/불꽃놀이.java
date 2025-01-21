//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/32375
//1초 / 1024MB
//요약 : K점수를 시작으로 점점 큰수를 만든다, 수는 1~2개 더해서 K이상의 수를 계속 만들어나가며 만든 횟수를 최대로 출력, 없으면 -1출력
//팁 : K가 증가해야하는 것은 고려할 필요 없음, 배열에서 1~2개를 사용해 K보다 큰것만 만들어내면 됨 
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N		= Integer.parseInt(st.nextToken());	// 폭죽개수(1<=이십만)
		int K		= Integer.parseInt(st.nextToken());	// 시작 화려한 점수(1<=십억)
		int arr[]	= new int[N];
		int idx		= 0;
		int ans		= 0;
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++)
		{
			int n = Integer.parseInt(st.nextToken());	// 화려한 정도(1<=십억)
			if(n < K)
				arr[idx++] = n;
			else ++ans;
		}
		
		Arrays.sort(arr);
		
		int s = N - idx;
		int e = N - 1;
		while(s < e)
		{
			if(K <= arr[s] + arr[e])
			{
				++ans;
				++s;
				--e;
			}
			else
				++s;
		}
		
		System.out.print(ans == 0 ? -1 : ans);
	}
}