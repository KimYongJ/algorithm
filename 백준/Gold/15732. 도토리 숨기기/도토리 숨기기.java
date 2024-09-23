//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/15732
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N			= Integer.parseInt(st.nextToken());	// 상자개수(1<=백만)
		int K			= Integer.parseInt(st.nextToken());	// 규칙개수(1<=만)
		int D			= Integer.parseInt(st.nextToken());	// 도토리개수(1<=십억)
		int counting[]	= new int[N+1];
		int prevSum[]	= new int[N+2];
		
		for(int i=0; i<K; i++)
		{
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());	// 시작상자
			int B = Integer.parseInt(st.nextToken());	// 종료상자
			int C = Integer.parseInt(st.nextToken());	// 간격
			if(C == 1) {
				prevSum[A] += 1;
				prevSum[B + 1] += -1;
			}
			else
				while(A <= B)
				{
					counting[A]++;
					A+=C;
				}
		}
		
		for(int i=1; i<=N; i++)
			prevSum[i] += prevSum[i - 1];
		
		int Dcnt	= 0;
		int idx		= 0;
		while(idx <= N)
		{
			Dcnt += counting[idx] + prevSum[idx];
			if(Dcnt >= D)
				break;
			++idx;
		}
		System.out.print(idx);
	}
}