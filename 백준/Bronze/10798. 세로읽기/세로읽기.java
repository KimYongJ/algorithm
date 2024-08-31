//https://github.com/kimyongj/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
class Node{
	String str; int idx, len;
	Node(String str, int idx, int len){this.str=str; this.idx=idx; this.len=len;}
}
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		ArrayDeque<Node> q = new ArrayDeque<>();
		while(true) 
		{
			String str = br.readLine();
			if(str == null || str.length()==0)
				break;
			q.add(new Node(str, 0, str.length()));
		}
		while(!q.isEmpty()) 
		{
			Node now = q.poll();
			if(now.len > now.idx) {
				sb.append(now.str.charAt(now.idx++));
				q.add(now);
			}
		}
		System.out.print(sb.toString());
	}
}