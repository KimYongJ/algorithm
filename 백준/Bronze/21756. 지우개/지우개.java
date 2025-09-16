//https://www.acmicpc.net/problem/21756

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		List<Integer> main = new ArrayList<>();
		List<Integer> dummy = new ArrayList<>();
		
		for(int i=1; i<=N; i++)
			main.add(i);
		
		while(main.size() > 1)
		{
			dummy.clear();
			
			for(int i=1; i<main.size(); i+=2)
				dummy.add(main.get(i));
			
			List<Integer> d = main;
			main = dummy;
			dummy = d;
		}
		
		System.out.print(main.get(0));
	}
}