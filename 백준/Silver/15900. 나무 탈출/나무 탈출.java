//https://github.com/KimYongJ/algorithm
import java.util.ArrayList;
class Main{
	
	static int N, sum;
	static boolean visit[];
	static ArrayList<Integer>[] list;
    private static int read() throws Exception {
        int c, N = System.in.read() - 48;
        while ((c = System.in.read()) > 32) N = 10 * N + c - 48;
        return N;
    }
	public static void main(String[] args)throws Exception{
		N 		= read();
		list	= new ArrayList[N+1];
		visit 	= new boolean[N+1];
		for(int i=0; i<=N; i++)
			list[i] = new ArrayList<>();
		
		int a,b;
		for(int i=1; i<N; i++) 
		{
			a 			= read();
			b 			= read();
			list[a].add(b);
			list[b].add(a);
		}
		
		dfs(1, 0);
		
		System.out.println(sum%2==0 ? "No" : "Yes");
	}
	public static void dfs(int n, int d) {
		for(int i=0; i<list[n].size(); i++) {
			int next = list[n].get(i);
			if(!visit[next]) 
			{
				visit[next] = true;
				dfs(next,d+1);
			}
		}
			
		if(list[n].size() == 1)
			sum += d;
	}
}