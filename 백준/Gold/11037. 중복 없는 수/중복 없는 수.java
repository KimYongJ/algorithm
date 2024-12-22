//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/11037

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

class Main{
	
	static boolean visit[]			= new boolean[10];
	static ArrayList<Integer> list	= new ArrayList<>();
	
	public static void main(String[] args)throws Exception{
		BufferedReader br	= new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb	= new StringBuilder();
		
		for(int i=1; i<10; i++)
			back(i, 0);
		
		String str;
		while((str = br.readLine()) != null && str.length() != 0)
		{
			int num = Integer.parseInt(str);
			sb.append(num >= 987654321 ? 0 :  binarySearch(num) ).append('\n');
		}

		System.out.print(sb);
	}
	public static int binarySearch(int target) {
		int s	= 0;
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
		if(0 == depth)
		{
			list.add(num);
			return;
		}
		
		for(int i=1; i<=9; i++)
			if(!visit[i])
			{
				visit[i] = true;
				back(depth - 1, num*10 + i);
				visit[i] = false;
			}
	}
}