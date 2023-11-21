// https://github.com/KimYongJ/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
class Main{
	
	static StringBuilder sb = new StringBuilder();
	static ArrayList<ArrayList<Character>> tree = new ArrayList<>();
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
		int N = Integer.parseInt(br.readLine());
		
		for(int i=0; i<N; i++)
			tree.add(new ArrayList<Character>());
		
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			char a = st.nextToken().charAt(0);
			char b = st.nextToken().charAt(0);
			char c = st.nextToken().charAt(0);
			ArrayList<Character> nodeList = tree.get(a-'A');
			nodeList.add(a); // 부모노드
			nodeList.add(b); // 왼쪽 자식노드
			nodeList.add(c); // 오른쪽자식노드
		}
		preorder('A'); // 전위순회 루트 - 왼쪽 - 오른쪽
		
		sb.append('\n');
		
		inorder('A');  // 중위순회 왼쪽 - 루트 - 오른쪽
		
		sb.append('\n');
		
		postorder('A');// 후위순회 왼쪽 - 오른쪽 - 루트
		System.out.println(sb);

	}	
	// 전위순회 루트 - 왼쪽 - 오른쪽
	public static void preorder(char node) {
		// 노드가 전달되면 그 노드를 먼저 스트링에 넣은 후 
		// 왼쪽노드가 있다면 왼쪽 노드를 전달한다.
		// 그후 오른쪽 노드가 있다면 오른쪽 노드를 전달한다.
		ArrayList<Character> list = tree.get(node-'A');
		char pnode = list.get(0); // 부모노드
		char lnode = list.get(1); // 왼쪽노드
		char rnode = list.get(2); // 오른쪽노드
		sb.append(pnode);
		if(lnode!='.')preorder(lnode);
		if(rnode!='.') preorder(rnode);
	}
	// 중위순회 왼쪽 - 루트 - 오른쪽
	public static void inorder(char node) {
		// 노드가 전달되면 그 노드의 왼쪽 노드가 있다면 다시 왼쪽노드를 전달해 재귀 호출
		// 왼쪽노드가 없다면 부모노드를 넣고,
		ArrayList<Character> list = tree.get(node-'A');
		char pnode = list.get(0); // 부모노드
		char lnode = list.get(1); // 왼쪽노드
		char rnode = list.get(2); // 오른쪽노드
		if(lnode!='.')inorder(lnode);
		sb.append(pnode);
		if(rnode!='.')inorder(rnode);
	}
	// 후위순회 왼쪽 - 오른쪽 - 루트
	public static void postorder(char node) {
		// 노드가 전달되면 그 노드의 왼쪽자식 노드가 있다면 재귀
		// 없다면 부모노드를 넣은 후 오른쪽있다면 재귀 없다면 부모노드를 넣는다.
		ArrayList<Character> list = tree.get(node-'A');
		char pnode = list.get(0); // 부모노드
		char lnode = list.get(1); // 왼쪽노드
		char rnode = list.get(2); // 오른쪽노드
		if(lnode!='.') postorder(lnode);
		if(rnode!='.') postorder(rnode);
		sb.append(pnode);
	}
}
//컴퓨터가 인지 가능한 것으로 놓는다.
// 노드의 갯수가 7개가 오면 list를 초기화 한다. 
			
//			StringTokenizer st = new StringTokenizer(br.readLine());
//			int r = Integer.parseInt(st.nextToken());
//			int g = Integer.parseInt(st.nextToken());
//			int b = Integer.parseInt(st.nextToken());
//			arr[i][0] = Math.min(arr[i-1][1],arr[i-1][2])+r;
//			arr[i][1] = Math.min(arr[i-1][0],arr[i-1][2])+g;
//			arr[i][2] = Math.min(arr[i-1][1],arr[i-1][0])+b;
