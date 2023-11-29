// https://github.com/KimYongJ/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
class Main{
    public static void main(String[] args)throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Stack<Character> stack;
        String str;
        int goodWord = 0,
            N = Integer.parseInt(br.readLine());
        
        for(int i=0; i<N; i++){
            str = br.readLine();
            stack = new Stack<Character>();
            if(str.length() % 2 == 0){ // 짝수만이 좋은 단어이다.
                
                for(int j=0; j<str.length(); j++){
                    char c = str.charAt(j);

                    if(!stack.isEmpty() && stack.peek() == c){
                        stack.pop();
                    }else{
                        stack.push(c);
                    }

                }
                if(stack.isEmpty())
                    goodWord++;
            }
        }
        System.out.println(goodWord);
    }
}