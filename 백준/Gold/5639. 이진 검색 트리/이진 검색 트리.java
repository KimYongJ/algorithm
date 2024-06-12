import java.io.BufferedReader;
import java.io.InputStreamReader;


class Node{
	int root;
	Node left, right;
	Node(int root){
		this.root = root;
	}
	void insert(int node) 
	{
		if(this.root > node) 
		{
			if(left == null)	
				left = new Node(node);
			else				
				left.insert(node);
		}else 
		{
			if(right == null)	
				right = new Node(node);
			else				
				right.insert(node);
		}
	}
}
class Main{
	
	static StringBuilder sb = new StringBuilder();
	static Node root;
	public static void postOrder(Node node) {
		if(node == null) return;
		postOrder(node.left);
		postOrder(node.right);
		sb.append(node.root).append('\n');
	}
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		root = new Node(Integer.parseInt(br.readLine()));
		
		String in;
		while((in = br.readLine())!= null) 
				root.insert(Integer.parseInt(in));

		postOrder(root);
		
		System.out.print(sb);
	}
}
