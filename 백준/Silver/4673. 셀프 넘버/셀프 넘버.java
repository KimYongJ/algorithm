import java.util.*;
import java.io.*;

class Main{
    public static void main(String[] args){
        StringBuilder sb = new StringBuilder();
        boolean[] check = new boolean[10001];
        for(int i=1;i<10001;i++){
            int a = d(i);
            if(a<10001){
                check[a]=true;
            }
        }
        
        for(int j=1;j<10001;j++){
            if(!check[j])
                sb.append(j).append("\n");
        }
       
        System.out.println(sb);
    }
    private static int d(int num){
        int sum = num;
        while(num!=0){
            sum += num%10;
            num /= 10;
        }
        return sum;
    }
    
}