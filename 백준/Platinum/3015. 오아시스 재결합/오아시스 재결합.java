//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/3015
//1초 256MB
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
class Node{
	long h, cnt;
	Node(int h, int cnt){
		this.h = h;
		this.cnt = cnt; 
	}
}
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());//1<=오십만
		int arr[] = new int[N + 1];

		for(int i=0; i<N; i++)
			arr[i] = Integer.parseInt(br.readLine());//1 <= 2의31승 -1
		
		Stack<Node> stack = new Stack<>();
		long res = 0;
		for(int i=0; i<N; i++)
		{
			Node now = new Node(arr[i], 1);
			
			while(!stack.isEmpty() && stack.peek().h <= arr[i])
			{
				Node prv = stack.pop();
				
				res += prv.cnt;
				
				if(prv.h == arr[i])
					now.cnt+= prv.cnt;
			}
			
			if(!stack.isEmpty())
				++res;
			
			stack.push(now);
		}
		System.out.print(res);
	}
}