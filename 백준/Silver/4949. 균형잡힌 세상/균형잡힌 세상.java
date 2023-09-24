// https://github.com/KimYongJ/algorithm/tree/main
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
class Main{
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args)throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while(true){
            String str = br.readLine();
            
            if(".".equals(str)) 
                break;
            
            sb.append(getBalance(str));
        }
        System.out.println(sb.toString());
    }
    // 핵심 로직 
    public static String getBalance(String str){
        Stack<Character> stack = new Stack<>();
        String result = "yes\n";
        
        int len = str.length();
        
        for(int i=0; i<len; i++){
            char c = str.charAt(i);
            if(c=='(' || c=='['){
                stack.push(c);
            }else if(c==')'|| c==']'){
                if(stack.isEmpty() || 
                     (stack.peek()!='(' && c==')') ||
                     (stack.peek()!='[' && c==']')){
                    result = "no\n";
                    break;
                }
                stack.pop();
            }
        }
        
        if(!stack.isEmpty()){
            result = "no\n";
        }
        
        return result;
    }
}