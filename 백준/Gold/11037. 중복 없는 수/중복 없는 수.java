//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/11037

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

class Main{
	
	static int arr[];
	static boolean visit[];
	static ArrayList<Integer> list;
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		String str;
		arr		= new int[9];
		visit	= new boolean[10];
		list	= new ArrayList<>();
		
		back(8);
		
		Collections.sort(list);

		while((str = br.readLine()) != null && str.length() != 0)
			sb.append( binarySearch(Integer.parseInt(str)) ).append('\n');

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
	public static void back(int depth) {
		make();
		if(depth < 0)
			return;
		
		for(int i=1; i<=9; i++)
			if(!visit[i])
			{
				visit[i] = true;
				arr[depth] = i;
				back(depth - 1);
				arr[depth] = 0;
				visit[i] = false;
			}
	}
	public static void make() {
		int num = 0;
		int i = 8;
		int mul = 1;
		while(0<=i) {
			if(arr[i] == 0)
				break;
			num += mul*arr[i];
			i--;
			mul *= 10;
		}
		list.add(num);
	}
}