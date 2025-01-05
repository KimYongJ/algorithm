//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/17305
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int W = Integer.parseInt(st.nextToken());
		
		ArrayList<Integer> g3 = new ArrayList<>();
		ArrayList<Integer> g5 = new ArrayList<>();
		
		for(int i=0; i<N; i++)
		{
			st = new StringTokenizer(br.readLine());
			int g = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			if(g == 3)
				g3.add(s);
			else
				g5.add(s);
		}
		
		Collections.sort(g3, (a,b)->b-a);
		Collections.sort(g5, (a,b)->b-a);

		long sum3[] = new long[g3.size() + 1];
		long sum5[] = new long[g5.size() + 1];
		
		for(int i=1; i<=g3.size(); i++)
			sum3[i] = sum3[i-1] + g3.get(i-1);
		for(int i=1; i<=g5.size(); i++)
			sum5[i] = sum5[i-1] + g5.get(i-1);
		
		
		long max = 0;
		for(int i=0; i<=g5.size(); i++)
		{
			int weight5 = i * 5;
			if(W < weight5)
				break;
			
			int remainWeight = W - weight5;
			
			max = Math.max(max, sum5[i] + sum3[Math.min(remainWeight / 3, g3.size())]);
		}
		System.out.print(max);
	}
}