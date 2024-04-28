// https://github.com/KimYongJ/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

class Main{
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Stack<Character> stack = new Stack<>();
		String base = br.readLine();
		char[] key = br.readLine().toCharArray();
		int keySize = key.length;
		int stSize =0;
		for(char c : base.toCharArray()) {
			stack.add(c);
			stSize = stack.size();
			if(stack.size() >= keySize) {
				boolean flag = true;
				
				for(int i=0; i<keySize; i++) {
					if(key[i] != stack.get(stSize-keySize+i)) {
						flag = false;
						break;
					}
				}
				
				if(flag) {
					for(int i=0; i<keySize; i++)
						stack.pop();
				}
			}
		}
		
		StringBuilder sb = new StringBuilder();
		while(!stack.isEmpty()) {
			sb.append(stack.pop());
		}
		String result = sb.reverse().toString();
		if("".equals(result))
			result = "FRULA";
		System.out.print(result);
	}
}