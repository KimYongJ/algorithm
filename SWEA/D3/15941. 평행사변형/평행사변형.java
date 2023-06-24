import java.io.BufferedReader;
import java.io.InputStreamReader;

class Solution{
    public static void main(String[] args)throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int l = Integer.parseInt(br.readLine());
        
        for(int i=1; i<=l; i++){
            int n = Integer.parseInt(br.readLine());
            sb.append("#").append(i).append(" ").append(n*n).append("\n");
        }
        System.out.print(sb);
    }
    
}