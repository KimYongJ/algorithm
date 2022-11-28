import java.io.*;
import java.util.*;

class Main{
    public static void main(String[] args)throws Exception{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int count=0;
        int a = Integer.parseInt(in.readLine());
        int result=a;
        do{
            a = (a%10*10)+((a/10+a%10)%10);
            count++;  
        }while(result!=a);
        System.out.println(count);
    }
}