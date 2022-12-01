import java.io.*;
import java.util.*;
class Main{
    public static void main(String[] args)throws Exception{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int len = Integer.parseInt(in.readLine());
        String[] str = new String[len];
        int result,cnt;
        for(int i=0;i<len;i++){
            str[i] = in.readLine();
        }
        for(int i=0;i<len;i++){
        	result =0;
        	cnt=0;
            for(int j=0;j<str[i].length();j++){
                if(str[i].charAt(j)=='O') ++cnt;
                else cnt=0;
                result+=cnt;
            }
            sb.append(result).append("\n");
        }
        System.out.println(sb);
    }
}