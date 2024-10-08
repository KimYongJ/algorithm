//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/8983
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int M		= Integer.parseInt(st.nextToken());	// 사대의 수 (1<=십만)
		int N		= Integer.parseInt(st.nextToken());	// 동물의 수 (1<=십만)
		int L		= Integer.parseInt(st.nextToken());	// 사정거리 (1<=10억)
		int arr[]	= new int[M];						// 사대의 위치를 나타내는 x좌표 값

		st = new StringTokenizer(br.readLine());
		for(int i=0; i<M; i++)
			arr[i] = Integer.parseInt(st.nextToken());
		
		Arrays.sort(arr);
		
		int cnt = 0;
		for(int i=0; i<N; i++)
		{
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());// 동물의 좌표x, y 순서( 0<=10억)
			int y = Integer.parseInt(st.nextToken());// 동물의 좌표x, y 순서( 0<=10억)
			
			int range = L - y;
			if(range < 0) 
				continue; // y가 사정거리보다 더 길경우 스킵
			
			// pos의 특정 인덱스 값 - x의 절대 값이 range보다 크거나 같아야 한다.
			int s = 0;
			int e = M - 1;
			while(s <= e)
			{
				int mid = (s + e) >> 1;
				int diff = arr[mid] - x;	// 사대위치 - 동물위치
				if(Math.abs(diff) <= range)	// 절대 값이 유효범위 내라면 사냥 가능
				{
					cnt++;
					break;
				}
				
				if(x < arr[mid]) {
					e = mid - 1;
				}
				else
				{
					s = mid + 1;
				}
			}
		}
		System.out.print(cnt);
	}
}