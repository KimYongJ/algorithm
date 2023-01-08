import java.io.*;

class Main{
    public static void main(String... args)throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long[] p = new long[101];
        StringBuilder sb = new StringBuilder();
        int l = Integer.parseInt(br.readLine());
        p[0] = 1;
        p[1] = 1;
        p[2] = 1;
        for(int i = 3; i<101; i++)
            p[i] = p[i-2]+p[i-3];
        
        for(int i=0; i<l; i++){
            int n = Integer.parseInt(br.readLine());
            sb.append(p[n-1]).append("\n");
        }
        System.out.println(sb);
    }
}