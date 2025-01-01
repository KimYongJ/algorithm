//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/20438
import java.util.Arrays;
class Main{
	public static void main(String[] args)throws Exception{
		int N			= read();	// 학생수
		int K			= read();	// 졸고있는 학생수
		int Q			= read();	// 출석 코드를 보낼 학생 수
		int M			= read();	// 질의 수
		int psum[]		= new int[N+3];
		boolean sleep[] = new boolean[N+3];
		
		Arrays.fill(psum, 1);			// 모든학생을 일단 출석 처리를 한다.
		psum[0] = psum[1] = psum[2] = 0;
		
		for(int i=0; i<K; i++)
			sleep[read()] = true;
		
		for(int i=0; i<Q; i++)
		{
			int num = read();
            if(!sleep[num])
				for(int j=num; j<=N+2; j+=num)
					if(!sleep[j])
						psum[j] = 0;
		}
		
		for(int i=3; i<=N+2; i++)
			psum[i] += psum[i-1];
		
		StringBuilder sb = new StringBuilder();
		while(M-->0)
		{
			int l = read();
			int r = read();
			sb.append(psum[r] - psum[l-1]).append('\n');
		}
		System.out.print(sb);
	}
	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
}