//https://www.acmicpc.net/problem/12873
//2초 512MB

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		long N = Integer.parseInt(br.readLine());// 참가자 수 1<=5,000
		List<Integer> list = new ArrayList<>();
		for(int i=1; i<=N; i++)
			list.add(i);
		
		int idx = 0;
		long level = 1;
		while(list.size() != 1)
		{
			idx = (int)((idx - 1 + level * level * level) % list.size());

			list.remove(idx);
			
			++level;
		}
		System.out.print(list.get(0));
	}
}