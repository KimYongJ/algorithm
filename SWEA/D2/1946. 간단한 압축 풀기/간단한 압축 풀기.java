import java.io.*;
import java.util.*;

class Solution{
    static StringBuilder result = new StringBuilder();
    public static void main(String[] args)throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int l = Integer.parseInt(br.readLine());
        
        for(int i=1; i<=l; i++){
            int num = Integer.parseInt(br.readLine());
            char[] alpha = new char[num];
            int[] repeat = new int[num];
            
            for(int j=0; j<num; j++){
                StringTokenizer st = new StringTokenizer(br.readLine());
                alpha[j] = (st.nextToken()).charAt(0);
                repeat[j] = Integer.parseInt(st.nextToken());;
            }
            
            StringBuilder sb = new StringBuilder();
            
            for(int j=0; j<num; j++)
                for(int x=0; x<repeat[j];x++)
                	sb.append(alpha[j]);
            
            String str = sb.toString(); // 하나로 합쳐진 srt
            // 결과입력
            result.append("#").append(i).append("\n");
            int idx = 0;
            for(char c : str.toCharArray()){
                if(idx==10){
                    idx = 0;
                    result.append("\n");
                }
                result.append(c);
                idx++;
            }
            result.append("\n");
        }
        System.out.println(result);
    }
}