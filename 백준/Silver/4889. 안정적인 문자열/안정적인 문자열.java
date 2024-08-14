// https://github.com/kimyongj/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
class Node{
	Node next;
	char value;
	Node(Node next, char value){
		this.next = next;
		this.value = value;
	}
}
class Stack{
	Node now;
	int size;
	Stack(){size = 0;} // 생성자
	boolean isEmpty() {return size==0;}
	int size() {return size;};
	void add(char value) {
		now = new Node(now, value);
		size++;
	}
	void pop() {
		if(size != 0) {
			now = now.next;
			size--;
		}
	}
}
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		String str = br.readLine();
		int idx = 1;
		while(str.charAt(0) != '-') 
		{
			int len = str.length();
			if(len == 0) 
			{
				sb.append(idx++).append(". ").append(0).append('\n');
				continue;
			}
			Stack rightSt = new Stack();
			Stack leftSt = new Stack();
			for(int i=0; i<len; i++) {
				char c = str.charAt(i);
				if(c == '{') {
					rightSt.add(c);
				}else if(rightSt.isEmpty()) {
					leftSt.add(c);
				}else {
					rightSt.pop();
				}
			}
			int rightSize = rightSt.size();
			int leftSize = leftSt.size();
			int res = rightSize / 2 + leftSize / 2;
			if(rightSize % 2 == 1)
				res+=2;
			sb.append(idx++).append(". ").append(res).append('\n');
			str = br.readLine();
		}
		System.out.print(sb.toString());
	}
}