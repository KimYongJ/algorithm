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

		while(isContinue())	// sum배열에 같은 값이 모두 들어가있는지 체크
		{
			for(int i=0; i<len; i++)
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
			if(base != s || !isPrime[s])
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
			len		= Integer.parseInt(br.readLine());
			eIdx	= new int[len];
			sIdx	= new int[len];
			sum		= new int[len];
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0; j<len; j++)
			{
				eIdx[j] = Integer.parseInt(st.nextToken());
				for(int z=0; z<eIdx[j]; z++)
					sum[j] += prime[z];		// sum[j] 에 그 숫자만큼 소수의 합을 담는다.
			}
			
			int max = 0;
			for(int s : sum)
				max = Math.max(max, s);
			
			sb.append("Scenario ").append(i).append(":\n")
				.append(cal(max)).append("\n\n");
		}
		System.out.print(sb);
	}
}