// https://github.com/kimyongj/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken()) * 10;
		int R = 0;
		boolean visit[] = new boolean[10011];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) 
		{
			int num = Integer.parseInt(st.nextToken()) * 10;
			for(int j=num-5; j<num+5; j++) 
			{
				visit[j] = true;
			}
		}
		for(int i=0; i<10011; i++) {
			if(visit[i]) {
				R++;
				int j = i;
				for(;j<i+L && j<10011; j++) {
					visit[j] = false;
				}
				i = j - 1;
			}
		}
		System.out.print(R);
	}
}