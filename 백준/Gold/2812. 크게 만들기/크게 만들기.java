// https://github.com/kimyongj/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Main{
	public static void main(String args[])throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 자리수
		int K = Integer.parseInt(st.nextToken()); // 지울 수
		int limit = N-K; // 최종 만들어야하는 길이
		String str = br.readLine();

		
		for(int i=0; i<N; i++) {
			char c = str.charAt(i);
			while(K != 0 && sb.length()>0 && sb.charAt(sb.length()-1) < c) 
			{
				sb.deleteCharAt(sb.length()-1);
				if(--K==0)break;
			}
			sb.append(c);
		}
		
		
		System.out.print(sb.toString().substring(0,limit));
	}
} 