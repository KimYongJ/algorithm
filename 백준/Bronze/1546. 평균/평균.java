import java.io.*;
import java.util.*;
class Main{
    public static void main(String[] args)throws Exception{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int l = Integer.parseInt(in.readLine());
        StringTokenizer st = new StringTokenizer(in.readLine()," ");
        int m = 0;
        double r =0;
        for(int i=0;i<l;i++){
            int data= Integer.parseInt(st.nextToken());
            r +=data;
            if(m<data) m=data;
        }
        System.out.println(r/m*100/l);
    }
}