// https://github.com/kimyongj/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	
	static int 		N, K, num[];
	static String 	str;
	
	public static boolean exit() {
		StringBuilder sb = new StringBuilder();
		for(int n : num) {
			if(n == 0)break;
			sb.append(n).append('+');
		}
		str = sb.deleteCharAt(sb.length()-1).toString();
		return true;
	}
	public static boolean DFS(int depth, int sum) {
		if(sum == 0 && 0 == --K)	return exit();
		if(sum <= 0 || depth == N) 	return false;	
		
		for(int i=1; i<=3; i++) 
		{
			num[depth] = i;
			if(DFS(depth + 1, sum - i)) 
				return true;
			num[depth] = 0;
		}
		return false;
	}
	public static void main(String[] args)throws Exception{
		BufferedReader 	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N 	= Integer.parseInt(st.nextToken());
		K 	= Integer.parseInt(st.nextToken());
		num = new int[N];
		str	= "-1";
		
		DFS(0,N);
		
		System.out.print(str);
	}
}