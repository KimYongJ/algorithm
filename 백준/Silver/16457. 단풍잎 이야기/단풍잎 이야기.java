// https://github.com/kimyongj/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	
	static int N, N2, M, K, map[];
	static int MAX;

	public static int check(int bit) {
		int cnt = 0;
		
		for(int m : map)
			if((m & bit) == m)
				cnt++;
		
		return cnt;
	}
	public static void comb(int depth,int idx, int bit) {
		if(depth == 0) 
		{
			MAX = Math.max(MAX, check(bit));
			return;
		}
		
		for(int i=idx; i<=N2; i++)
			comb(depth - 1, i + 1, bit | (1<<i));
	}
	public static void main(String[] args)throws Exception{
		BufferedReader 	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N 		= Integer.parseInt(st.nextToken());
		N2 		= N*2;
		M 		= Integer.parseInt(st.nextToken());
		K 		= Integer.parseInt(st.nextToken());
		map 	= new int[M];
		
		for(int i=0; i<M; i++) 
		{
			st = new StringTokenizer(br.readLine());
			for(int k=0; k<K; k++)
				map[i] |=  (1<<Integer.parseInt(st.nextToken()));
		}
		// 2n개중 n개를 조합한다.
		comb(N, 1, 0);
		
		System.out.print(MAX);
	}
}