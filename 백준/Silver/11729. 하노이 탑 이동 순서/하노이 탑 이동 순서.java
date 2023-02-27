import java.io.*;

class Main{
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args)throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int x = Integer.parseInt(br.readLine());
        
        sb.append((1<<x)-1).append("\n");
        func(1,3,x);
        System.out.println(sb);
    }
    public static void func(int a, int b, int n){
        if(n==1){
            sb.append(a).append(" ").append(b).append("\n");
            return;
        }
        func(a,6-a-b,n-1);
        sb.append(a).append(" ").append(b).append("\n");
        func(6-a-b,b,n-1);
    }
}