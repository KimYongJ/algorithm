// https://github.com/kimyongj/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	
	static final char FIVE = '5';
	static final char ZERO = '0';
	static StringBuilder sb;
	
	public static int count() {
		int cnt = 0;
		for(int i=0; i<sb.length(); i++) 
		{
			if(sb.charAt(i)==FIVE) 
			{
				cnt++;
			}
		}
		return cnt;
	}
	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		long	N 		= Long.parseLong(st.nextToken()) + 1; 
		int		K 		= Integer.parseInt(st.nextToken());
		int 	idx 	= 0;
		boolean rounds 	= false;
		
		sb = new StringBuilder().append(N).reverse();
		
		while(idx < sb.length() && count() < K) 
		{
			if(rounds)							// 반올림을 해야할 때 
			{
				sb.setCharAt(idx - 1, FIVE);
			}
			if(count() >= K) 					// 목적 달성시
			{
				break;
			}
			
			char c = sb.charAt(idx);
			if(c == FIVE) 
			{
				rounds = false;
			}
			else if(c < FIVE){
				sb.setCharAt(idx,FIVE);
				rounds = false;
			}
			else	// 5보다 c가 클경우
			{
				sb.setCharAt(idx,ZERO);
                rounds = true;
                // 올림처리
                sb = new StringBuilder()
				.append(Long.parseLong(sb.reverse().toString()) + (long)Math.pow(10,idx+1))
				.reverse();
			}
			idx++;
		}
		
		// 5를 K만큼 못 채웠는지 확인
		int cnt = count();
		for(int i=cnt; i<K; i++)
		{
			sb.append(FIVE);
		}
		
		System.out.print(sb.reverse().toString());
	}
}