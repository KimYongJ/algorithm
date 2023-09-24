import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
class Main{
    public static void main(String[] args)throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        Stack<Integer> stack = new Stack<>();
        int n = Integer.parseInt(br.readLine());
        int[] base = new int[n];
        for(int i=0; i<n; i++){
            base[i] = Integer.parseInt(br.readLine());
        }
        int number = 1;
        int idx = 0;
        while(true){
            if(!stack.isEmpty() && stack.peek()==base[idx]){
                idx++;
                stack.pop();
                sb.append("-\n");
            }else if(n<number){
                if(!stack.isEmpty()){
                    sb = new StringBuilder();
                    sb.append("NO");  
                }
                break;
            }else{
                stack.push(number++);
                sb.append("+\n");
            }
        }
        System.out.println(sb.toString());
        
    }
}