import java.io.*;
import java.util.*;

class Main{
    public static void main(String[] args)throws Exception{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int count=0;
        int a = Integer.parseInt(in.readLine());
        int result =a;

        while(true){
            int left = a/10;
            int right = a%10;
            a = (right*10)+((left+right)%10);
            count++;
            if(result==a){
                break;
            }
        }
        
        System.out.println(count);
        
    }
}