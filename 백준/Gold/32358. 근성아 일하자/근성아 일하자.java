//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/32358
//1초 / 1024MB

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N		= Integer.parseInt(br.readLine());
		int now		= 0;
		long res	= 0;
		ArrayList<Integer> list = new ArrayList<>();
		
		while(N-->0)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			int type = Integer.parseInt(st.nextToken());
			if(type == 1)
			{
				int value = Integer.parseInt(st.nextToken());
				
				list.add(value);
			}
			else
			{
				Collections.sort(list);
				while(!list.isEmpty())
				{
					int idx = binarySearch(list, now);
					
					if(idx + 1 != list.size())
					{
						int diff1 = Math.abs(now - list.get(idx));
						int diff2 = Math.abs(now - list.get(idx+1));
						if(diff2 < diff1)
							idx++;
					}
					res += Math.abs(now - list.get(idx));
					now = list.get(idx);
					
					list.remove(idx);
				}
			}
		}
		System.out.print(res);
	}
	public static int binarySearch(ArrayList<Integer> list, int target) {
		int s	= 0;
		int e	= list.size()-1;
		int idx = -1;
		while(s <= e)
		{
			int mid = (s + e) >> 1;
			if(list.get(mid) <= target)
			{
				
				idx = mid;
				s = mid + 1;
			}
			else
				e = mid - 1;
		}
		
		return idx < 0 ? 0 : idx;
	}
}