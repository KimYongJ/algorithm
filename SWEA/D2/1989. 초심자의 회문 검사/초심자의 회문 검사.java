import java.io.*;

class Solution{
    public static void main(String[] args)throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int l = Integer.parseInt(br.readLine());
        
        for(int i=1; i<=l; i++){
            String str = br.readLine();
            int strlen = str.length();
            int result = 1;
            for(int j=0; j<strlen/2; j++){
                if(str.charAt(j) != str.charAt(strlen-j-1)){
                    result = 0;
                    break;
                }
            }
            sb.append("#").append(i).append(" ").append(result).append("\n");
        }
        System.out.println(sb);
    }
}