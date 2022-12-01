import java.io.*;
import java.util.*;
class Main{
    public static void main(String[] args)throws Exception{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int len = Integer.parseInt(in.readLine());
        for(int x=0;x<len;x++){
            String str = in.readLine();
            int result = 0;
            int cnt = 0;
            for(int i=0;i<str.length();i++){
                if(str.charAt(i)=='O'){
                    ++cnt;
                }else{
                    for(int j=1;j<cnt+1;j++){
                        result+=j;
                    }
                    cnt=0;
                }
            }
            if(cnt!=0) {
                for(int j=1;j<cnt+1;j++){
                    result+=j;
                }
            }
            System.out.println(result);
        }
    }
}