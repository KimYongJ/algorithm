//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/11037

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

class Main{
	
	static boolean visit[]			= new boolean[10];
	static ArrayList<Integer> list	= new ArrayList<>();
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		back(0, 0);
		
		Collections.sort(list);
		
		String str;
		while((str = br.readLine()) != null && str.length() != 0)
			sb.append( binarySearch(Integer.parseInt(str)) ).append('\n');

		System.out.print(sb);
	}
	public static int binarySearch(int target) {
		int s	= 1;
		int e	= list.size() - 1;
		int res	= 0;
		
		while(s<=e)
		{
			int mid = (s + e) >> 1;
			int num = list.get(mid);
			if(target < num)
			{
				res = num;
				e = mid - 1;
			}
			else s = mid + 1;
		}
		
		
		return res;
	}
	public static void back(int depth, int num) {
		list.add(num);
		if(9 <= depth)
			return;
		
		for(int i=1; i<=9; i++)
			if(!visit[i])
			{
				visit[i] = true;
				back(depth + 1, num + i * (int)Math.pow(10, depth));
				visit[i] = false;
			}
	}
}