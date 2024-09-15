//https://github.com/KimYongJ/algorithm
//https://www.acmicpc.net/problem/14426
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Node{
	Node[] next = new Node[26];
}
class Trie{
	Node root;
	Trie() {root = new Node();}
	
	public void add(String str)
	{
		Node current = root;
		for(int i=0; i<str.length(); i++)
		{
			int c = str.charAt(i) - 'a';
			
			if(current.next[c] == null)
				current.next[c] = new Node();
			
			current = current.next[c];
		}
	}
	public boolean contains(String str) {
		Node current = root;
		for(int i=0; i<str.length(); i++)
		{
			int c = str.charAt(i) - 'a';
			
			if(current.next[c] == null)
				return false;
			
			current = current.next[c];
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