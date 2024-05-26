// https://github.com/kimyongj/algorithm

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	
	static int N, M, data[][];
	static boolean  bool[];
	
	public static boolean get(int num) {
		int abs = Math.abs(num);
		return num > 0 ? bool[abs] : !bool[abs];
	}
	public static boolean check() {
		boolean flag = get(data[0][0]) || get(data[0][1]);
		
		for(int i=1; i<M; i++) 
			flag = flag && (get(data[i][0]) || get(data[i][1]));
		
		return flag;
	}
	public static boolean DFS(int cnt, int idx) {
		if(cnt == 0)
			return check();
		
		for(int i=idx; i<=N; i++) 
		{
			bool[i] = true;
			if(DFS(cnt - 1, i+1)) return true;
			bool[i] = false;
		}
		
		return false;
	}
	
	public static void main(String[] args)throws Exception{
		BufferedReader 	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N 		= Integer.parseInt(st.nextToken());
		M 		= Integer.parseInt(st.nextToken());
		bool 	= new boolean[N+1];
		data 	= new int[M][2];
		
		for(int i=0; i<M; i++) 
		{
			st = new StringTokenizer(br.readLine());
			data[i][0] = Integer.parseInt(st.nextToken());
			data[i][1] = Integer.parseInt(st.nextToken());
		}
		
		
		// bool 배열에 true와 false에 대해 모든 것을 담아야 함으로 조합으로 구현
		int num = 0;
		
		if(check())		// 모두 false인 경우 
			num = 1;
		
		for(int i=1; i<=N && num == 0; i++)
			if(DFS(i,1))
				num = 1;

		System.out.print(num);
	}
	
}