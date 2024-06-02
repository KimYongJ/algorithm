// https://github.com/kimyongj/algorithm

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

class Main{
	
	static String	str;
	static int 		len;
	static boolean 	visit[];
	static ArrayList<Integer> list = new ArrayList<>();
	public static boolean check() {
		int cnt = 0;
		int flag = 1;
		for(int i=1; i<51; i++) {
			if(i >= 10) flag = 2;
			if(visit[i])
				cnt += flag;
			else break;
		}
		return cnt == len;
	}
	public static boolean backtracking(int idx) {
		if(idx == len) 
			return check();
		
		for(int t = 1; t<=2 && idx+t <= len; t++) 
		{
			int num  = Integer.parseInt(str.substring(idx,idx+t));
			if(num <= 50 && !visit[num]) 
			{
				visit[num] = true;
				list.add(num);
				if(backtracking(idx+t))
					return true;
				list.remove((Integer)num);
				visit[num] = false;
			}
		}
		return false;
	}
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		str 	= br.readLine();
		len 	= str.length();
		visit 	= new boolean[51];
		
		backtracking(0);
		
		StringBuilder sb = new StringBuilder();
		
		for(int i=0; i<list.size(); i++)
			sb.append(list.get(i)).append(' ');
		
		System.out.print(sb);
	}
}