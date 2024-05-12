//https://github.com/KimYongJ/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
class Main{
	
	static int N;
	static List<List<Integer>> list = new ArrayList<>();
	static boolean visit[];
	static int result;
	public static void main(String[] args)throws Exception{
		BufferedReader 	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N 		= Integer.parseInt(br.readLine());
		visit 	= new boolean[N+1];
		for(int i=0; i<=N; i++)
			list.add(new ArrayList<>());
		
		int a,b;
		for(int i=1; i<N; i++) 
		{
			st 			= new StringTokenizer(br.readLine());
			a 			= Integer.parseInt(st.nextToken());
			b 			= Integer.parseInt(st.nextToken());
			list.get(a).add(b);
			list.get(b).add(a);
		}
		dfs(0,1);
		if(result%2 != 0) System.out.println("Yes");
		else System.out.println("No");
	}
    static void dfs(int depth, int v) {
        for (int i = 0; i < list.get(v).size(); i++) {
            int next = list.get(v).get(i);
            if (!visit[next]) {
                visit[next] = true;
                dfs(depth + 1, next);
            }
        }

        if (list.get(v).size() == 1) result += depth;
    }
}