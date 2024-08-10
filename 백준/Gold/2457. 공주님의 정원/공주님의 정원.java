//https://github.com/kimyongj/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;
class Node{
	int s,e;
	Node(int s,int e){this.s=s;this.e=e;}
}
class Main{
	public static void main(String args[])throws Exception{
		ArrayList<Node> list = new ArrayList<>();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		
		for(int i=0; i<N; i++) 
		{
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()) * 100 + Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken()) * 100 + Integer.parseInt(st.nextToken());
			list.add(new Node(a,b));
		}
		
		Collections.sort(list,(a,b)->{
			if(a.e==b.e) return a.s-b.s;
			return b.e-a.e;
		});
		
		int cnt = 0;
		int start = 301;
		int flag = 0;
		while(start < 1201 && flag != start)
		{
			flag = start;
			for(int j=0; j<N; j++)
			{
				Node now = list.get(j);
				if(now.s <= start) {
					start = now.e;
					cnt++;
					break;
				}
			}
		}
		
		if(start < 1201)
			cnt = 0;
		System.out.print(cnt);
	}
}