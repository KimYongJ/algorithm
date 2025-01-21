//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/24508
//1초 / 512MB
//요약 : 배열에서 두수를 더해서 모두다 K를 만들 수 있는지 and K를 만들 때 T번 이하로 숫자를 옮기며 할 수 있는지 체크
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N		= Integer.parseInt(st.nextToken());// 바구니(2<=십만)
		int K		= Integer.parseInt(st.nextToken());// 모이면 터지는 수 (0<=십억)
		int T		= Integer.parseInt(st.nextToken());// 반복가능한 횟수
		int arr[]	= new int[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++)
			arr[i] = Integer.parseInt(st.nextToken());

		
		Arrays.sort(arr);
		
		int s = 0;
		int e = N-1;
		while(s < e)
		{
			int need = K - arr[e];
			if(T < need)
				break;
			if(arr[s] < need)		// 필요한 양보다 s가 작은경우
			{
				T -= arr[s];
				arr[e] += arr[s];
				arr[s++] = 0;
			}
			else					// e를 충분히 없앨 수 있는 경우
			{
				T -= need;			// 횟수에서 차감
				arr[e--] = 0;
				if(0 >= arr[s] - need)// s가 0이하면 s를 추가
					arr[s++] = 0;
				else
					arr[s] -= need;

			}
		}
		for(int a : arr)
			if(a != 0)
			{
				System.out.print("NO");
				return;
			}

		System.out.print("YES");
	}
}

