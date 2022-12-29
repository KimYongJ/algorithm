import java.io.*;
import java.util.*;

class Main{
    public static void main(String[] args)throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int l = Integer.parseInt(br.readLine());
        StringBuilder[] sbArray = new StringBuilder[201];
        for(int i=0; i<201; i++)
            sbArray[i] = new StringBuilder();
        
        StringTokenizer st;
        for(int j=0; j<l; j++){
            st = new StringTokenizer(br.readLine());
            int age = Integer.parseInt(st.nextToken());
            sbArray[age].append(age).append(" ").append(st.nextToken()).append("\n");
        }
        
        StringBuilder sb = new StringBuilder();
        for(StringBuilder s : sbArray)
            sb.append(s);
        
        System.out.println(sb);
        
    }
}