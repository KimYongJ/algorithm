//https://github.com/KimYongJ
import java.io.BufferedReader;
import java.io.InputStreamReader;

class Solution{
    public static void main(String[] args)throws Exception{
    	BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int l = Integer.parseInt(br.readLine());
        
        for(int i=1; i<=l; i++){
            boolean[] blist = new boolean[10];
            long N = Integer.parseInt(br.readLine());
            int ans = 0;
            while(!check(blist)){
                ans++;
                for(char c : String.valueOf(N*ans).toCharArray()){
                    blist[c-48] = true;
                }
            }
            sb.append("#").append(i).append(" ").append(ans*N).append("\n");
        }
        System.out.println(sb);
    }
    public static boolean check(boolean[] bool){
        for(boolean b : bool)
            if(!b)
                return b;
        return true;
    }
}