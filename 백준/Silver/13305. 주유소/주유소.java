// https://github.com/kimyongj/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		long dist[] = new long[N];
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<N; i++) {
			dist[i] = Long.parseLong(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		long nowCost = Integer.parseInt(st.nextToken());
		long res = 0;
		long distSum = 0;
		for(int i=1; i<N; i++) {
			distSum += dist[i];
			int nextCost = Integer.parseInt(st.nextToken());
			if(i == N-1) {
				res += nowCost * distSum;
			}
			else if(nowCost > nextCost) 
			{
				res += nowCost * distSum;
				nowCost = nextCost;
				distSum = 0;
			}
		}
		System.out.print(res);
	}
}