import java.io.*;
import java.util.*;
class Main{
    public static void main(String[] args)throws Exception{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        StringBuilder sb = new StringBuilder();
        for(int i=x; i<=y; i++)
            if(check(i))
                sb.append(i).append("\n");
        System.out.println(sb);
    }
    public static boolean check(int n){
        if(n<=1) return false;
        for(int i=2; i*i<=n; i++)
            if(n%i==0)
                return false;
        return true;
    }
}