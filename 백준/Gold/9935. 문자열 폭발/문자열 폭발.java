// https://github.com/KimYongJ/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;

class Stack{
	char c;
	int size=0;
	Stack child;
	public Stack() {}
	public Stack(char c, Stack child) {
		this.c=c; this.child=child;
		if(child != null)
			this.size=child.size + 1;
	}
	public boolean contains(char[] key) {
		if(key.length == 1)
			return key[0] == c;
		if(key[0] != c) return false;
		
		int i=1;
		for(Stack now=child; now!=null && i<key.length; now=now.child, i++) {
			if(key[i] != now.c)
				return false;
		}
		
		return true;
	}
	public boolean isEmpty() {return size == 0;}
	public Stack delete() {return child;}
	public char pop() {return c;}
}
class Main{
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Stack stack = new Stack();
		String base = br.readLine();
		char[] key = new StringBuilder(br.readLine()).reverse().toString().toCharArray();
		int keySize = key.length;
		for(char c : base.toCharArray()) 
		{
			stack = new Stack(c, stack);
			if(stack.size >= keySize) 
				if(stack.contains(key))
					for(int i=0; i<keySize; i++)
						stack = stack.delete();
		}
		
		StringBuilder sb = new StringBuilder();
		while(!stack.isEmpty()) {
			sb.append(stack.pop());
			stack = stack.delete();
		}
		String result = sb.reverse().toString();
		if("".equals(result))
			result = "FRULA";
		System.out.print(result);
	}
}