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
	public char get(int idx) {
		if(idx==0)return c;
		int idx1 = 0;
		for(Stack now=child; now!=null; now= now.child)
			if(++idx1 == idx)
				return now.c;
		return ' ';
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
		char[] key = br.readLine().toCharArray();
		int keySize = key.length;
		for(char c : base.toCharArray()) 
		{
			stack = new Stack(c, stack);
			if(stack.size >= keySize) 
			{
				boolean flag = true;
				
				for(int i=0; i<keySize; i++) 
					if(key[i] != stack.get(keySize-i-1)) 
					{
						flag = false;
						break;
					}
				
				
				if(flag)
					for(int i=0; i<keySize; i++)
						stack = stack.delete();
			}
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