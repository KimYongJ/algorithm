import java.io.*;

class Main{
    public static void main(String[] args)throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int x = Integer.parseInt(br.readLine());
        int floor = 0, sum = 0;
        while(sum<x)
            sum += ++floor;
        int denominator = floor % 2 == 0 ? sum-x+1 : floor-sum+x;
        System.out.println(String.valueOf(floor+1-denominator)+"/"+String.valueOf(denominator));
    }
}