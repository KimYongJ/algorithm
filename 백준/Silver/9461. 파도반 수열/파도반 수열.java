import java.io.*;

class Main{
    public static void main(String... args)throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int l = Integer.parseInt(br.readLine());
        for(int i=0; i<l; i++){
            int x = Integer.parseInt(br.readLine());
            sb.append(find(x)).append("\n");
        }
        System.out.println(sb);
    }
    public static long find(int x){
        if(x<=3) return 1;
        long[] p = new long[] {1,1,1,2};
        for(int i=0; i<x-4; i++){
            long tmp = p[1]+p[2];
            p[0]=p[1];
            p[1]=p[2];
            p[2]=p[3];
            p[3]=tmp;
        }
        return p[3];
    }
    
}