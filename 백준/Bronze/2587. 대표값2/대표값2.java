import java.io.*;
import java.util.*;
class Main{
    public static void main(String[] args)throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int sum=0;
        short[] r = new short[5];
        for(int i=0;i<5;i++){
            short data = Short.parseShort(br.readLine());
            sum+=data;
            r[i] = data;
        }
        Arrays.sort(r);
        System.out.println(sum/5);
        System.out.println(r[2]);
    }
}