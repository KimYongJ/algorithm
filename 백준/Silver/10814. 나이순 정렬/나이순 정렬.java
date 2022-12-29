import java.io.*;
import java.util.*;

class Main{
    public static void main(String[] args)throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int l = Integer.parseInt(br.readLine());
        StringBuilder[] sbArray = new StringBuilder[201];
        for(int i=0; i<201; i++)
            sbArray[i] = new StringBuilder();
        
        StringTokenizer st;
        for(int j=0; j<l; j++){
            st = new StringTokenizer(br.readLine());
            int age = Integer.parseInt(st.nextToken());
            String name = st.nextToken();
            sbArray[age].append(age).append(" ").append(name).append("\n");
        }
        
        for(StringBuilder s : sbArray)
            if(s!=null)
                bw.write(s.toString());
        
        bw.flush();
        bw.close();
        
    }
}