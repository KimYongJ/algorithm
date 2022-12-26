import java.io.*;
import java.util.*;
class Main{
    public static void main(String[] args)throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int sum=0;
        short[] r = new short[5];
        for(int i=0;i<5;i++){
            short data = Short.parseShort(br.readLine());
            sum+=data;
            r[i] = data;
        }
        Arrays.sort(r);
        sb.append(sum/5).append('\n').append(r[2]);
        System.out.println(sb);
    }
}