//https://www.acmicpc.net/problem/30803
//2초 1024MB
//3 // 수도꼭지 수 (1<=200,000)
//6 1 4 // 처음 수도꼭지에서 나오는 물의 양(1<=10^9)
//4	// 조작의 수 (1<=200,000)
//2 1	// 2 i 는 i번째 수도꼭지 토글 버튼을 누른걸 의미
//2 3
//1 2 3// // 1 i x 는 i번째 수도꼭지 나사를 돌려 1분에 x리터의 물을 내보내도록 함
//2 3
//조작횟수 + 1개 줄에 물의양 출력
//11
//5
//1
//3
//7
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		long sum = 0;
		long arr[] = new long[N];
		boolean isClosed[] = new boolean[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++)
			sum += arr[i] = Integer.parseInt(st.nextToken());
		
		sb.append( sum ).append('\n');
		
		int Q = Integer.parseInt(br.readLine());
		
		while(Q-->0)
		{
			st = new StringTokenizer(br.readLine());
			int cmd = Integer.parseInt(st.nextToken());
			int idx = Integer.parseInt(st.nextToken()) - 1;
			
			if(cmd == 1)
			{
				int x = Integer.parseInt(st.nextToken());
				
				if(!isClosed[idx])
					sum += x - arr[idx];
				
				arr[idx] = x;
			}
			else
			{
				isClosed[idx] = !isClosed[idx];
				
				if(isClosed[idx]) sum -= arr[idx];
				else sum += arr[idx];
			}
			
			sb.append( sum ).append('\n');
		}
		System.out.print(sb);
	}
}