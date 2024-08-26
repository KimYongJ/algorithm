// https://github.com/kimyongj/algorithm
// https://www.acmicpc.net/problem/17420
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;
class Node{
	long useDay,remain;
	Node(long useDay,long remain){
		this.useDay=useDay;
		this.remain=remain;
	}
}
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		ArrayList<Node> list = new ArrayList<>();
		int N = Integer.parseInt(br.readLine());
		int dummy[] = new int[N];
		long result = 0;
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++)
			dummy[i] = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) 
		{
			int a = Integer.parseInt(st.nextToken());
			list.add(new Node(a,dummy[i]));
		}
		
		Collections.sort(list,(a,b)->a.useDay != b.useDay ? Long.compare(a.useDay,b.useDay) : Long.compare(a.remain,b.remain));
		
		long prev_max = list.get(0).useDay;
		long cur_max = -1;
		for(int i=0; i<N; i++) 
		{
			Node now = list.get(i);
			
			if(prev_max > now.remain) 
			{
				prev_max = Math.max(prev_max, now.useDay);
				
				int count = (int)Math.ceil((prev_max - now.remain) / (double)30);
				
				now.remain += count*30;
				
				result += count;
			}
			
			cur_max = Math.max(cur_max, now.remain);
			
			if(i+1<N && now.useDay != list.get(i+1).useDay) 
			{
				prev_max = cur_max;
			}
		}
		
		System.out.print(result);
	}
}
