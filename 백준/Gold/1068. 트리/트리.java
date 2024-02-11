// https://github.com/KimYongJ/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Main{
	
	static int N, del, node, root, leaf;
	static ArrayList<Integer>[] list;
	static BufferedReader br;
	static StringTokenizer st;
	public static void main(String[] args)throws Exception
	{
		br 					= new BufferedReader(new InputStreamReader(System.in));
		st 					= new StringTokenizer(br.readLine());
		N 					= Integer.parseInt(st.nextToken());
		list 				= new ArrayList[N];
		st 					= new StringTokenizer(br.readLine());
		
		for(int i=0; i<N; i++)
			list[i] = new ArrayList<>(); // 자식 노드 정보를 담을 리스트 초기화  
		
		del = Integer.parseInt( new StringTokenizer(br.readLine()).nextToken()); // 없앨 노드를 입력받음
		
		for(int i=0; i<N; i++) 
		{
			node = Integer.parseInt(st.nextToken());
			if(node == -1) 					root = i;
			else if(node !=del && i != del)	list[node].add(i);
		}
		
		if(root != del) DFS(root); // 루트노드가 삭제되지 않은 경우만 DFS로 leaf노드를 센다
		
		System.out.println(leaf);

	}
	public static void DFS(int node) 
	{
		if(list[node].size() == 0)  // 리프노드일 때는 자식노드가 없어서 size가 0이다.
		{ 
			leaf++; // 리프노드면 +1
			return;
		}
		for(Integer l : list[node])
			DFS(l);
	}
}