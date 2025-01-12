//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/7512
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
class Main{
	
	static final int MAX = 10_000_000;
	static boolean isPrime[];
	static int len;
	static int[] prime, eIdx, sIdx, sum;
	
	public static void initPrime(){
		int size = 0;
		isPrime = new boolean[MAX];

		Arrays.fill(isPrime, true);
		
		for(int i=2; i<MAX; i++)
			if(isPrime[i])
			{
				++size;
				for(int j=i+i; j<MAX; j+=i)
					isPrime[j] = false;
			}
		
		prime = new int[size];
		for(int i=2,j=0; i<MAX; i++)
			if(isPrime[i])
				prime[j++] = i;
	}
	public static int cal(int max) {

		while(isContinue())	// sum배열에 같은 값이 모두 들어가있는지 체크 및 무한반복
		{
			for(int i=0; i<len; i++)		// sum배열을 돌면서 max보다 작거나, 소수가 아니면 계속 증가시킨다
			{
				if(sum[i] < max || !isPrime[sum[i]])
				{
					while(sum[i] < max || !isPrime[sum[i]])
					{
						sum[i] = sum[i] - prime[sIdx[i]] + prime[eIdx[i]];
						sIdx[i]++;
						eIdx[i]++;
					}
				}
				max = Math.max(sum[i], max);
			}
		}
		
		return max;
	}
	public static boolean isContinue(){
		int base = sum[0];
		for(int s: sum)
			if(base != s || !isPrime[s])	// sum배열 값이 다 같지 않거나 하나라도 소수가 아니면 계속반복
				return true;
		return false;
	}
	
	public static void main(String args[])throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder	sb = new StringBuilder();
		
		initPrime();
		
		int T = Integer.parseInt(br.readLine());
		
		
		for(int i=1; i<=T; i++)
		{
			int max = 0;
			
			len		= Integer.parseInt(br.readLine());
			eIdx	= new int[len];	// 각 배열의 종료인덱스
			sIdx	= new int[len];	// 각 배열의 시작인덱스
			sum		= new int[len];	// 각 배열마다의 총 합
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0; j<len; j++)
			{
				eIdx[j] = Integer.parseInt(st.nextToken());	// 종료인덱스를 입력받음, ex)3입력시 0~2까지 범위가되는것
				for(int z=0; z<eIdx[j]; z++)
					sum[j] += prime[z];	// sum[j] 에 종료인덱스 만큼 더한다. ex)3입력시 0~2번째 소수를 더함
				
				max = Math.max(max, sum[j]);
			}
			
			sb.append("Scenario ").append(i).append(":\n")
				.append(cal(max)).append("\n\n");
		}
		System.out.print(sb);
	}
}