//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/6209
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int D		= Integer.parseInt(st.nextToken());		// 총거리(1<=십억)
		int N		= Integer.parseInt(st.nextToken())+2;	// 돌섬수(0<=오만)
		int M		= Integer.parseInt(st.nextToken());		// 제거 할 돌섬수(0<=N)
		int arr[]	= new int[N];							// 시작으로부터 떨어진 섬의 위치
		arr[N-1]	= D;// 마지막거리 삽입
		
		for(int i=1; i<N - 1; i++)
			arr[i] = Integer.parseInt(br.readLine());
		
		Arrays.sort(arr);
		
		int res	= 0;
		int s	= 0;
		int e	= D;
		
		while(s <= e)
		{
			int mid			= (s + e) >> 1; // 돌과 돌섬사이 최소 거리
			int deleteCnt	= 0;
			int startIdx	= 0;
			
			for(int i=1; i<N; i++)
			{
				if(arr[i] - arr[startIdx] < mid)
					deleteCnt++;
				else
					startIdx = i;
			}
			
			if(deleteCnt <= M) // 해당 최소거리로 갈 수 있다면
			{
				s = mid + 1;
				res = mid;
			}else
				e = mid - 1;
		}
		
		System.out.print(res);
	}
}
