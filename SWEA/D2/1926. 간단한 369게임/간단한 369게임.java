import java.io.*;

class Solution{
 	public static void main(String... args)throws Exception{
     	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int l = Integer.parseInt(br.readLine());
        
        for(int i=1; i<=l; i++){
            int x = check(i);
            if(x==0)
                sb.append(i);
            else{
                for(int j=0; j<x; j++)  sb.append("-");
            }
            sb.append(" ");
        }
        System.out.println(sb);
    }
    public static int check(int i){
        int cnt =0;
        for(char c : Integer.toString(i).toCharArray())
            if(c=='3' || c=='6' || c=='9') cnt++;
		return cnt;
    }
}