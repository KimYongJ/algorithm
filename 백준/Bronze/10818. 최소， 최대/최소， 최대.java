import java.io.*;
import java.util.*;
class Main{
    public static void main(String[] args)throws Exception{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int len = Integer.parseInt(in.readLine());
        StringTokenizer st = new StringTokenizer(in.readLine()," ");
        int max=-1000001;
        int min=1000001;
        while(st.hasMoreTokens()) {
        	int num = Integer.parseInt(st.nextToken());
            if(max<num){
                max = num;
            }
            if(min>num){
                min = num;
            }
        }
        System.out.println(min+" "+max);
    }
}