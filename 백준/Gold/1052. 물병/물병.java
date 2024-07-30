// https://github.com/kimyongj/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 초기 물병개수
		int K = Integer.parseInt(st.nextToken()); // 목표 물병 개수
		if(K >= N) // 특정 조건 조기종료
		{
			System.out.print(0);
			return;
		}
		int res = 0;
		while(Integer.bitCount(N) > K) {
			int lowerBit = Integer.lowestOneBit(N);
			N += lowerBit;
			res += lowerBit;
		}
		System.out.print(res);
	}
}