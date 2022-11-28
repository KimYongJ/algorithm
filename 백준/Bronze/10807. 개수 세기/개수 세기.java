import java.io.*;
import java.util.*;
class Main{
    public static void main(String[] args)throws Exception{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int cnt=0;
        int len = Integer.parseInt(in.readLine());
        StringTokenizer st = new StringTokenizer(in.readLine()," ");
        int data = Integer.parseInt(in.readLine());
        for(int i=0;i<len;i++){
            if(data==Integer.parseInt(st.nextToken())){
                cnt++;
            }
        }
        System.out.println(cnt);
        
    }
}