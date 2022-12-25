import java.io.*;
import java.util.*;

class Main{
    public static void main(String[] args)throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int len = Integer.parseInt(br.readLine());
        int[] r = new int[len];
        for(int i=0;i<len;i++)
            r[i] = Integer.parseInt(br.readLine());
        for(int x=0;x<len-1;x++)
            for(int y=0;y<len-x-1 ;y++)
                if(r[y]>r[y+1]){
                    int d = r[y];
                    r[y] = r[y+1];
                    r[y+1] = d;
                }
        for(int j=0;j<len;j++)
            sb.append(r[j]).append("\n");
        System.out.println(sb);
    }
}