// https://github.com/KimYongJ/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Main{
	
	static StringBuilder sb = new StringBuilder();
	static char[][] arr = new char[91][2];
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
		int N = Integer.parseInt(br.readLine());
		
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			char parent = st.nextToken().charAt(0);
			char left 	= st.nextToken().charAt(0);
			char right 	= st.nextToken().charAt(0);
			arr[parent][0] = left;
			arr[parent][1] = right;
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
		// 노드가 전달되면 부모노드를 먼저 넣은 후  
		// 왼쪽노드가 있다면 왼쪽 노드를 전달한다.
		// 그후 오른쪽 노드가 있다면 오른쪽 노드를 전달한다.
		char left = arr[node][0];
		char right = arr[node][1];
		
		sb.append(node);
		if(left != '.')preorder(left);
		if(right !='.') preorder(right);
	}
	// 중위순회 왼쪽 - 루트 - 오른쪽
	public static void inorder(char node) {
		// 노드가 전달되면 그 노드의 왼쪽 노드가 있다면 다시 왼쪽노드를 전달해 재귀 호출
		// 왼쪽노드가 없다면 부모노드를 넣고,
		char left = arr[node][0];
		char right = arr[node][1];
		
		if(left != '.')inorder(left);
		sb.append(node);
		if(right !='.') inorder(right);
	}
	// 후위순회 왼쪽 - 오른쪽 - 루트
	public static void postorder(char node) {
		// 노드가 전달되면 그 노드의 왼쪽자식 노드가 있다면 재귀
		// 없다면 부모노드를 넣은 후 오른쪽있다면 재귀 없다면 부모노드를 넣는다.
		char left = arr[node][0];
		char right = arr[node][1];
		
		if(left != '.')postorder(left);
		if(right !='.') postorder(right);
		sb.append(node);
	}
}