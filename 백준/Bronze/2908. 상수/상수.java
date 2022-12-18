import java.io.*;
import java.util.*;
class Main{
    public static void main(String[] args)throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        String str1 = st.nextToken();
        String str2 = st.nextToken();
        boolean b = false;
        for(int i=2;i>=0;i--){
            if(str1.charAt(i)==str2.charAt(i)){
                continue;
            }else if(str1.charAt(i)>str2.charAt(i)){
                b = true; break;
            }else if(str1.charAt(i)<str2.charAt(i)){
                break;
            }
        }
        if(b){ // true면 str1 거꾸로 뒤집기
            for(int j=2;j>=0;j--)
                System.out.print(str1.charAt(j));
        }else{
            for(int j=2;j>=0;j--)
                System.out.print(str2.charAt(j));
        }
    }
}