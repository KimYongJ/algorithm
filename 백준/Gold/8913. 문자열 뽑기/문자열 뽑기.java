//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/8913
//2초, 128MB
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		while(T-->0)
				sb.append(bruteforce(br.readLine()) ? 1 : 0).append('\n');

		System.out.print(sb.toString());
	}
	public static boolean bruteforce(String str) {
		if(str.length() == 0)
			return true;
		if(str.length() == 1)
			return false;
		
		ArrayList<Integer> list = new ArrayList<>();
		char flag	= str.charAt(0);
		int len		= str.length();
		for(int i=1; i<len; i++)
			if(str.charAt(i) != flag)
			{
				list.add(i);
				flag = str.charAt(i);
			}
		
		if(list.size() == 0)
			return true;
		
		for(int i=0, s=0, e=0; i<list.size(); i++)
		{
			e = list.get(i);
			if(1 < e-s)
			{
				String front = str.substring(0, s);
				String back = str.substring(e, len);
				if(bruteforce(front + back))
					return true;
			}
			s = e;
		}
		if(1 < len - list.get(list.size()-1))
		{
			if(bruteforce(str.substring(0,list.get(list.size()-1))))
				return true;
		}
		
		return false;
	}
}