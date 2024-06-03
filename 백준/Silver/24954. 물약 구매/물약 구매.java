// https://github.com/kimyongj/algorithm
import java.util.ArrayList;

class Main{
	
	static int 		result = Integer.MAX_VALUE;
	static int 		N;
	static int 		price[];
	static boolean 	visit[];
	static ArrayList<int[]>[] saleList;
	
	static int read() throws Exception {// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	
	public static void backtracking(int depth,int sum) {
		if(sum > result) return;
		if(depth == N) 
		{
			result = sum;
			return;
		}
		
		for(int i=0; i<N; i++) 
		{
			if(!visit[i]) 
			{
				visit[i] = true;
				// price에서 값을 뺀다.
				for(int[] p : saleList[i])
					price[p[0]] -= p[1];
				
				backtracking(depth + 1,sum + (price[i] < 1 ? 1 : price[i]));
				// price에서 값을 더한다 ( 백트레킹 )
				for(int[] p : saleList[i])
					price[p[0]] += p[1];
				
				visit[i] = false;
			}
		}
	}
	public static void main(String[] args)throws Exception{
		N			= read();
		price 		= new int[N];
		visit		= new boolean[N];
		saleList 	= new ArrayList[N];
		
		for(int i=0; i<N; i++) 
		{
			price[i]	= read();
			saleList[i] = new ArrayList<>();
		}
		
		int a,b,n;
		for(int i=0; i<N; i++) 
		{
			n = read();
			for(int j=0; j<n; j++) 
			{
				a	= read()-1;
				b 	= read();
				saleList[i].add(new int[] {a,b});	// 특정 노드의 할인 정보를 담는다.
			}
		}
		
		backtracking(0,0);
		
		System.out.print(result);
	}
}