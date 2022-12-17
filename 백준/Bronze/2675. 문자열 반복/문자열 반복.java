import java.io.*;
import java.util.*;
class Main{
    public static void main(String[] args)throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int len = Integer.parseInt(br.readLine());
        for(int i=0;i<len;i++){
            StringTokenizer st = new StringTokenizer(br.readLine()," ");
            int repeat = Integer.parseInt(st.nextToken());
            String str = st.nextToken();           
            for(char c : str.toCharArray())
                sb.append((c+"").repeat(repeat));
            sb.append('\n');
        }
        System.out.println(sb);
        
    }
}
