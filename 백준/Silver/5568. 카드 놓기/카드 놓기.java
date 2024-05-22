// https://github.com/kimyongj/algorithm
import java.util.HashSet;
import java.util.Set;

class Main{
	
	static int 		N, K, base[];
	static boolean 	visit[];
	static Set<Long> set;

	static int read() throws Exception {// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	
	public static void Backtracking(int depth, long total) {
		if(depth == K) 
		{
			set.add(total);
			return;
		}
		for(int i=0; i<N; i++)
			if(!visit[i]) 
			{
				visit[i] = true;
				if(base[i] < 10)Backtracking(depth+1, total*10 + base[i]);
				else Backtracking(depth+1, total*100 + base[i]);
				visit[i] = false;
			}
		
	}
	public static void main(String[] args)throws Exception{
		N 		= read();
		K 		= read();
		base 	= new int[N];
		visit 	= new boolean[N];
		set 	= new HashSet<>();
		
		for(int i=0; i<N; i++)
			base[i] = read();
		
		Backtracking(0,0);
		
		System.out.print(set.size());
	}
}