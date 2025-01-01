//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/20438
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N			= Integer.parseInt(st.nextToken());	// 학생수
		int K			= Integer.parseInt(st.nextToken());	// 졸고있는 학생수
		int Q			= Integer.parseInt(st.nextToken());	// 출석 코드를 보낼 학생 수
		int M			= Integer.parseInt(st.nextToken());	// 질의 수
		int psum[]		= new int[N+3];
		boolean sleep[] = new boolean[N+3];
		
		Arrays.fill(psum, 1);
		psum[0] = psum[1] = psum[2] = 0;
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<K; i++)
			sleep[Integer.parseInt(st.nextToken())] = true;
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<Q; i++)
		{
			int num = Integer.parseInt(st.nextToken());
            if(sleep[num])
                continue;
            
			for(int j=num; j<=N+2; j+=num)
				if(!sleep[j])
					psum[j] = 0;
		}
		
		for(int i=3; i<=N+2; i++)
			psum[i] += psum[i-1];
		
		StringBuilder sb = new StringBuilder();
		while(M-->0)
		{
			st = new StringTokenizer(br.readLine());
			int l = Integer.parseInt(st.nextToken());
			int r = Integer.parseInt(st.nextToken());
			sb.append(psum[r] - psum[l-1]).append('\n');
		}
		System.out.print(sb);
	}
}