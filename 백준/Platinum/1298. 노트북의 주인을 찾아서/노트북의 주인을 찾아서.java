//https://github.com/KimyongJ/algorithm
//https://www.acmicpc.net/problem/1298
//2초 128MB
//5 13	// 사람 수 1<=100, 노트북 예상 수(0<=5,000)
//1 2	// 1번 사람이 2번 노트북이 자신의 것이라 주장
//1 3
//2 2
//2 3
//3 1
//3 2
//4 1
//4 2
//5 1
//5 2
//5 3
//5 4
//5 5
//답 : 4
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	
	static int N, M;
	static int[] match;						// idx : 노트북 번호, value : 매칭된 사람 번호
	static boolean[] visit;					// idx : 노트북 번호, value : 해당 노트북을 방문했었는지 체크
	static Node[] adNode;
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N		= Integer.parseInt(st.nextToken());	// 사람 수 1<=100
		M		= Integer.parseInt(st.nextToken());	// 노트북 예상 수(0<=5,000)
		match	= new int[N + 1];
		adNode	= new Node[N + 1];
		
		for(int i=0; i<M; i++)
		{
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			adNode[a] = new Node(b, adNode[a]);
		}
		
		int cnt = 0;
		
		for(int i=1; i<=N; i++)
		{
			visit = new boolean[N + 1];
			if(dfs(i))
				++cnt;
		}
		
		System.out.print(cnt);
	}
	static boolean dfs(int person) {
		for(Node next = adNode[person]; next != null; next=next.next)
		{
			int noteBook = next.noteBook;
			
			if(visit[noteBook])
				continue;
			
			visit[noteBook] = true;
			
			if(match[noteBook] == 0 || dfs(match[noteBook]))
			{
				match[noteBook] = person;
				return true;
			}
			
		}
		return false;
	}
	static class Node{
		int noteBook;
		Node next;
		Node(int noteBook, Node next){
			this.noteBook = noteBook;
			this.next = next;
		}
	}
}
