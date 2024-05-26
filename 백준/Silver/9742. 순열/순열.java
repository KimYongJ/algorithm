// https://github.com/kimyongj/algorithm

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	
	static char base[], make[];
	static int	len, CNT;
	static boolean visit[];
	
	public static boolean DFS(int depth) {
		if(depth == len) 
		{
			if(--CNT == 0) 
				return true;
			return false;
		}
		
		for(int i=0; i<len; i++) 
		{
			if(visit[i]) continue;
			visit[i] 	= true;
			
			make[depth] = base[i];
			
			if(DFS(depth + 1)) 
				return true;
			
			visit[i] 	= false;
		}
		
		return false;
	}
	
	public static void main(String[] args)throws Exception{
		BufferedReader 	br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder	sb = new StringBuilder();
		StringTokenizer st;
		String str;
		
		while((str = br.readLine()) != null)
		{
			st 		= new StringTokenizer(str);
			base 	= st.nextToken().toCharArray();
			len	 	= base.length;
			make 	= new char[len];
			visit 	= new boolean[len];
			CNT 	= Integer.parseInt(st.nextToken());
			
			sb.append(str).append(" = ");
			
			if(DFS(0))	sb.append(String.valueOf(make));
			else		sb.append("No permutation");
			
			sb.append('\n');
		}
		System.out.print(sb);
	}
}