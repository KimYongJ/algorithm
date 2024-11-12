//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/1057

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Main{
	
	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N	= Integer.parseInt(st.nextToken());
		int A	= Integer.parseInt(st.nextToken())-1;
		int B	= Integer.parseInt(st.nextToken())-1;
		int res	= 0;
		ArrayList<Boolean> list = new ArrayList<>();
		
		for(int i=0; i<N; i++)
			list.add(i==A || i==B);
		
		while(true)
		{
			++res;
			ArrayList<Boolean> next = new ArrayList<>();
			for(int i=0; i<list.size(); i+=2) {
				if(i+1 == list.size()) {
					next.add(list.get(i));
					break;
				}
				else {
					boolean a = list.get(i);
					boolean b = list.get(i+1);
					next.add(a || b);
					if(a&&b)
					{
						System.out.print(res);
						return;
					}
				}
			}
			list = next;
		}
	}
}