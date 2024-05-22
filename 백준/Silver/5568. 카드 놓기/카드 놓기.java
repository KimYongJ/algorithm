// https://github.com/kimyongj/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

class Main{
	
	static int N, K;
	static boolean visit[];
	static String base[], make[];
	static Set<String> set;
	public static void add() {
		StringBuilder sb = new StringBuilder();
		for(String s : make)sb.append(s);
		set.add(sb.toString());
	}
	public static void Backtracking(int depth) {
		if(depth == K) 
		{
			add();
			return;
		}
		for(int i=0; i<N; i++)
			if(!visit[i]) 
			{
				visit[i] = true;
				make[depth] = base[i];
				Backtracking(depth+1);
				visit[i] = false;
			}
		
	}
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N 		= Integer.parseInt(br.readLine());
		K 		= Integer.parseInt(br.readLine());
		base 	= new String[N];
		make 	= new String[K];
		visit 	= new boolean[N];
		set 	= new HashSet<>();
		
		for(int i=0; i<N; i++)
			base[i] = br.readLine();
		
		Backtracking(0);
		
		System.out.print(set.size());
	}
}