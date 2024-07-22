// https://github.com/kimyongj/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;
class Node{
	int base, inc;
	Node(int base, int inc){this.base=base; this.inc=inc;}
}
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		ArrayList<Node> list = new ArrayList<>();
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			list.add(new Node(Integer.parseInt(st.nextToken()), 0));
		}
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			list.get(i).inc = Integer.parseInt(st.nextToken());
		}
		Collections.sort(list,(a,b)->{
			if(a.inc == b.inc)return b.base - a.base;
			return a.inc - b.inc;
		});

		long sum = 0;
		for(int i=0; i<N; i++) {
			Node node = list.get(i);
			sum += node.base + (node.inc * (long)(i));
		}
		System.out.print(sum);
	}
}