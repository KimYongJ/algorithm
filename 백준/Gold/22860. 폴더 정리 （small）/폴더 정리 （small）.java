//https://github.com/KimYongJ/algorithm
//https://www.acmicpc.net/problem/22860
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;
class Node{
	int node;
	boolean isFile;
	Node next;
	Node(int n, boolean i, Node nt){node=n; isFile=i; next=nt;}
}
class Main{
	
	static int fileCategoryCnt, fileTotalCnt;
	static Node adNode[];
	static boolean visit[];
	static HashMap<String, Integer> idxData;
	public static void DFS(int node, boolean isFile) {
		if(isFile)
		{
			++fileTotalCnt;
			if(!visit[node])
			{
				visit[node] = true;
				++fileCategoryCnt;
			}
		}
		for(Node next=adNode[node]; next!=null; next=next.next)
			DFS(next.node, next.isFile);
	}
	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder	sb = new StringBuilder();
		
		idxData = new HashMap<>();
		// 폴더 + 파일의 총 개수
		int T	= Integer.parseInt(st.nextToken()) + Integer.parseInt(st.nextToken());
		int idx = 1;
		adNode	= new Node[1999];
		while(T-->0)
		{
			st = new StringTokenizer(br.readLine());
			String p		= st.nextToken();
			String c		= st.nextToken();
			boolean isFile	= Integer.parseInt(st.nextToken()) == 0;
			int parent, child;
			
			if(idxData.containsKey(p))
				parent = idxData.get(p);
			else
			{
				parent	= idx;
				idxData.put(p, idx++);				
			}
			if(idxData.containsKey(c))
				child = idxData.get(c);
			else
			{
				child	= idx;
				idxData.put(c, idx++);				
			}
			
			adNode[parent] = new Node(child, isFile, adNode[parent]);
		}
		
		T = Integer.parseInt(br.readLine());
		
		while(T-->0)
		{
			fileCategoryCnt	= 0;
			fileTotalCnt	= 0;
			visit			= new boolean[1999];
			String[] cmd	= br.readLine().split("/");
			
			int node = idxData.get(cmd[cmd.length-1]);
			
			DFS(node, false);
			
			sb.append(fileCategoryCnt).append(' ')
				.append(fileTotalCnt).append('\n');
		}
		System.out.print(sb.toString());
	}
}