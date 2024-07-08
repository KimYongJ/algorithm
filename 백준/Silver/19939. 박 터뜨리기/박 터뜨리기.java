// https://github.com/kimyongj/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 공개수
		int K = Integer.parseInt(st.nextToken()); // 팀개수
		int sum = K*(K+1)/2;
		if(sum > N) 
		{
			System.out.print(-1);
		}
		else 
		{
			N -= sum;

			if(N != 0 && (N < K || N%K != 0)) // 공개수가 팀수보다 작거나, 공개수를 팀수로 나눈 나머지가 0이 아닐 때
			{
				System.out.print(K);
			}else {
				System.out.print(K-1);
			}
		}
	}
}