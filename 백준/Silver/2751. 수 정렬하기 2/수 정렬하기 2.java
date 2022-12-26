import java.io.*;

class Main{
    public static void main(String[] args)throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        boolean[] b = new boolean[2000001];
        int len = Integer.parseInt(br.readLine());
        for(int i=0; i<len; i++)
            b[Integer.parseInt(br.readLine())+1000000] = true;            
        for(int j=0; j<2000001; j++)
            if(b[j]) sb.append(j-1000000).append("\n");
        System.out.println(sb);        
    }
}