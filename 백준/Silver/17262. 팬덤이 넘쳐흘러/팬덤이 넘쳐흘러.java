// https://github.com/kimyongj/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;
class Node{
	int s, e;
	Node(int s, int e) {this.s=s; this.e=e;}
}
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		ArrayList<Node> list = new ArrayList<>();
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		int s,e;
		for(int i=0; i<N; i++) 
		{
			st = new StringTokenizer(br.readLine());
			s = Integer.parseInt(st.nextToken());
			e = Integer.parseInt(st.nextToken());
			list.add(new Node(s,e));
		}
		
		Collections.sort(list, (a,b)->{
			return a.e - b.e; // 종료가 가장 빠른 수를 앞으로
		});
		s = list.get(0).e;
		int cnt = 0;
		for(int i=0; i<N; i++) {
			Node now = list.get(i);
			if(now.s <= s && s<= now.e) {
				cnt++;
			}
		}
		if(cnt != N) {
			Collections.sort(list,(a,b)->{
				return b.s-a.s; // 시작이 제일 큰수를 앞으로 
			});
			e = list.get(0).s;
			System.out.print(e - s);
		}
		else {
			System.out.print(0);
		}
	}
}