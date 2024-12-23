//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/19597
import java.io.BufferedReader;
import java.io.InputStreamReader;
class Str{
	String str[] = new String[2];
	Str(String str1, String str2)
	{
		str[0] = str1;
		str[1] = str2;
	}
}
class Main{
	
	static StringBuilder r = new StringBuilder();
	static int N;
	static String result;
	static Str arr[];
	static int order[];
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		while(T-->0)
		{
			N		= Integer.parseInt(br.readLine());
			arr		= new Str[N];
			order	= new int[N];
			result	= "zzzzzzzzzz";
			
			for(int i=0; i<N; i++)
			{
				String str1 = br.readLine();
				String str2 = reverse(str1);
				arr[i] = new Str(str1, str2);
			}
			
			back(0);
			
			sb.append(result).append('\n');
		}
		System.out.print(sb);
	}
	public static boolean back(int depth) {
		if(depth == N)
		{
			result = make();
			return true;
		}
		
		if(depth == 0)
		{
			order[0] = 0;
			if(back(depth + 1))return true;
			order[0] = 1;
			if(back(depth + 1))return true;
		}
		else
		{
			String before = arr[depth - 1].str[order[depth - 1]];
			if(before.compareTo(arr[depth].str[0]) < 0) {
				order[depth] = 0;
				if(back(depth + 1))return true;
			}
			if(before.compareTo(arr[depth].str[1]) < 0) {
				order[depth] = 1;
				if(back(depth + 1))return true;
			}
		}
		return false;
	}
	public static String make() {
		r.setLength(0);
		for(int o : order)r.append(o);
		return r.toString();
	}
	public static String reverse(String s) {
		r.setLength(0);
		return r.append(s).reverse().toString();
	}
}