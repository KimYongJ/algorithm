// https://github.com/kimyongj
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Solution{
    static int P,Q,R,S,W,ASUM,BSUM,result;
    public static void main(String[] args)throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int l = Integer.parseInt(br.readLine());
        
        for(int i=1; i<=l; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            ASUM = 0;
            BSUM = 0;
            P = Integer.parseInt(st.nextToken());
            Q = Integer.parseInt(st.nextToken());
            R = Integer.parseInt(st.nextToken());
            S = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            
            ASUM = P*W; // A사의 금액먼저 더함
            
            BSUM = W>R ?  (W-R)*S + Q : Q;
            
            result = ASUM > BSUM ? BSUM : ASUM;
            
            sb.append("#").append(i).append(" ").append(result).append("\n");
        }
        System.out.println(sb);
    }
    
}