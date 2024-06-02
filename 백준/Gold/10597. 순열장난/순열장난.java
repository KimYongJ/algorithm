// https://github.com/kimyongj/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main{
	
	static String	str;
	static int 		len;
	static int		arr[];
	static boolean 	visit[];
	
	public static boolean check() {
		int cnt = 0;
		for(int i=1; i<51 && visit[i]; i++) 
			cnt += i < 10 ? 1 : 2;
		return cnt == len;
	}
	public static boolean backtracking(int depth, int idx) {
		if(idx == len) 
			return check();
		
		for(int t = 1; t<=2 && idx+t <= len; t++) 
		{
			int num  = Integer.parseInt(str.substring(idx,idx+t));
			if(num <= 50 && !visit[num]) 
			{
				visit[num] = true;
				arr[depth] = num;
				if(backtracking(depth + 1, idx+t))
					return true;
				visit[num] = false;
			}
		}
		return false;
	}
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		str 	= br.readLine();
		len 	= str.length();
		arr		= new int[51];
		visit 	= new boolean[51];
		
		backtracking(0,0);
		
		StringBuilder sb = new StringBuilder();
		
		for(int i=0; i<51 && arr[i] != 0; i++)
			sb.append(arr[i]).append(' ');
		
		System.out.print(sb);
	}
}