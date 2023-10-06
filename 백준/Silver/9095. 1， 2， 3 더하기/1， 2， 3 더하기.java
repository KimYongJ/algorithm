// https://github.com/KimYongJ/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
class Main{
    public static int result;
    public static void main(String[] args)throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        while(T-->0){
            int num = Integer.parseInt(br.readLine());
            
            result = 0;
            
            DFS(num,0); // 순서 : base , sum
            
            sb.append(result).append("\n");
        }
        System.out.println(sb.toString());
        
    }
    public static void DFS(int num,int sum){
        if(sum>=num){
            if(sum==num){
                result++;
            }
            return;
        }
        DFS(num,sum+1);
        DFS(num,sum+2);
        DFS(num,sum+3);
    }
}