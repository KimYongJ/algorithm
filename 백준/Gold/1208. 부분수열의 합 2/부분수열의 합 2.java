//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/1208
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Main{
	static final int plus	= 2_000_000;
	static final int MAX	= 4_000_001;
	static long res	= 0;
	static int N, S, arr[], count[];
	public static void getSum(int s, int e, int sum) {
		if(s == e)
		{
			count[sum + plus]++;	// 원소하나가 십만이고, 최대 40개인데 반을 나누므로 최대 20개만가능 10만*20개해서 plus가 이십만임
			return;
		}
		getSum(s+1, e, sum);
		getSum(s+1, e, sum+arr[s]);
	}
	public static void getRes(int s, int sum) {
		if(s == N)
		{
			int target = S - sum + plus;	// 목표값을 산출
			if(0<= target && target < MAX)	// 목표값이 유효범위 내일 때 만 결과 플러스, MAX는 최대 나올 수 있는 수임 
				res += count[target];
			return;
		}
		getRes(s+1, sum);
		getRes(s+1, sum+arr[s]);
	}
	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N		= Integer.parseInt(st.nextToken());	// 정수개수(1<=40)
		S		= Integer.parseInt(st.nextToken());	// 목표값(-백만<=백만)
		arr		= new int[N];						// 원소들(-십만<=십만)
		count	= new int[MAX];						// 원소들의 합 카운팅 정렬
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++)
			arr[i] = Integer.parseInt(st.nextToken());
		
		getSum(0, N/2, 0);	// 배열을 반으로 나눠 그 배열의 모든 부분함을 count에 정렬한다.
		getRes(N/2, 0);		// 남은 반에 대해 모든 부분합을 구해서 위에서 구한 값하고 더했을 때 S가 되는것을 찾음
		
		if(S == 0)			// 부분합을 구할 때 0이 자동으로 들어가기 때문에 S가 0일 때는 하나를 빼준다.
			--res;
		System.out.print(res);
	}
}
