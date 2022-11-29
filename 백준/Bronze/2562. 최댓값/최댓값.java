import java.io.*;
import java.util.*;
class Main{
    public static void main(String[] args)throws Exception{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int max=0;
        int cnt=0;
        int data;
        for(int i=0;i<9;i++){
            data=Integer.parseInt(in.readLine());
            if(max<data){
                max=data;
                cnt=i;
            }
        }
        System.out.println(max);
        System.out.println(cnt+1);
        
    }
}