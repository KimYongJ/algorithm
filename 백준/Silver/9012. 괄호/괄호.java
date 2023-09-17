import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
class Main{
    public static void main(String[] args)throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());
        for(int i=0; i<n; i++){
            String str = br.readLine();
            sb.append(excute(str)).append("\n");
        }
        System.out.println(sb.toString());
    }
    public static String excute(String str){
        String result = "YES";
        Stack<Character>stack = new Stack<>();
        for(char c : str.toCharArray()){
            if(c==')'){
               if(stack.isEmpty() || stack.peek()!='('){
                   result = "NO";
                   break;
               }
               stack.pop();
            }else{
                stack.push(c);
            }
        }
        if(!stack.isEmpty()){
            result = "NO";
        }
        return result;
    }
}