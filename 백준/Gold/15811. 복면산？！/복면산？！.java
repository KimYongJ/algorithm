// https://github.com/kimyongj/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;

class Main{
	
	static String	str[];
	static boolean	visit[];
	static int		arr[];
	static int		alphabet[];
	
	public static long getNumber(String s) {
		long num = 0;
		for(int i=0; i<s.length(); i++)
			num = num*10 + alphabet[s.charAt(i) - 'A'];
		return num;
	}
	public static boolean backtracking(int depth) {
		if(depth < 0)
			return getNumber(str[0]) + getNumber(str[1]) == getNumber(str[2]);
		
		for(int i=0;i<=9; i++) 
		{
			if(!visit[i]) 
			{
				visit[i] = true;
				alphabet[arr[depth]] = i;
				if(backtracking(depth - 1))
					return true;
				visit[i] = false;
			}
		}
		return false;
	}
	public static void main(String[] args)throws Exception{
		BufferedReader br		= new BufferedReader(new InputStreamReader(System.in));
		HashSet<Integer> set	= new HashSet<>();
		str						= br.readLine().split(" ");
		alphabet				= new int[26];
		visit					= new boolean[10];
		for(String s : str) 
			for(int i=0; i<s.length(); i++)
				set.add(s.charAt(i)-'A');				// 문자열에 대해 차례로 돌며 사용된 문자를 넣는다.
		
		arr	= new int[set.size()];
		
		int len = 0;
		for(int num : set)
			arr[len++] = num;							// arr에 차례로 사용된 문자열을 담음 arr을 통해 index로 접근할 경우 어떤 알파벳인지 담겨있음
		
		System.out.print(backtracking(len-1) ? "YES" : "NO");
	}
}