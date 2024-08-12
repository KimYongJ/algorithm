// https://github.com/kimyongj/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(st.nextToken());
		int result[] = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++) 
		{
			int num = Integer.parseInt(st.nextToken())+1;
			int idx = 0;
			while(idx < N) {
				if(result[idx] == 0) {
					if(--num == 0) {
						result[idx] = i;
						break;
					}
				}
				idx++;
			}
		}
		
		for(int r : result)
			sb.append(r).append(' ');
		System.out.print(sb.toString());
	}
}