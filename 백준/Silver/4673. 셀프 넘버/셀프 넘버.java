import java.util.*;
import java.io.*;

class Main{
    public static void main(String[] args){
        StringBuilder sb = new StringBuilder();
        List<Integer> dlist = new ArrayList<Integer>();
        for(int i=0;i<=10000;i++){
            if(i<10){
                dlist.add(i+i);
            }else{
                int d = 0;
                d+=i;
                char[] cl = Integer.toString(i).toCharArray();
                for(char c : cl){
                    d+=c-'0';
                }
                dlist.add(d);                
            }
        }
        // 다시반복문을해서 dlist에 없는 값만 sb에 넣어주면된다.
        for(int j=1;j<=10000;j++){
            if(!dlist.contains(j)){
                sb.append(j).append("\n");
            }
        }
        System.out.println(sb);
    }
}