//https://github.com/KimYongJ/algorithm
//https://www.acmicpc.net/problem/14426
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Node{
	boolean now;
	Node[] next = new Node[26];
	Node(boolean now) {this.now = now;}
}
class Trie{
	Node[] string;
	Trie() {string = new Node[26];}
	
	public void add(String str)
	{
		int c = str.charAt(0) - 'a';
		if(string[c] == null) {
			string[c] = new Node(true);
		}
		Node now[] = string[c].next;
		for(int i=1; i<str.length(); i++) {
			c = str.charAt(i) - 'a';
			if(now[c] == null) {
				now[c] = new Node(true);
			}
			now = now[c].next;
		}
	}
	public boolean contains(String str) {
		int c = str.charAt(0) - 'a';
		if(string[c] == null) return false;
		Node now[] = string[c].next;
		for(int i=1; i<str.length(); i++) {
			c = str.charAt(i) - 'a';
			if(now[c] == null) return false;
			now = now[c].next;
		}
		return true;
	}
}
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N		= Integer.parseInt(st.nextToken());//기준 문자열 N개	(1<=만)
		int M		= Integer.parseInt(st.nextToken());//검사할 문자열 M개	(1<=만)
		int res		= 0;
		Trie trie	= new Trie();
		
		while(N-->0)
			trie.add(br.readLine());
		
		while(M-->0)
			if(trie.contains(br.readLine()))
				res++;

		System.out.print(res);
	}
}