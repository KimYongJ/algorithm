import java.io.*;
import java.util.*;
import java.util.stream.*;
class Main{
    public static void main(String[] args)throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String a = br.readLine();
        int x = Integer.parseInt(a.split(" ")[0]);
        int y = Integer.parseInt(a.split(" ")[1]);
        //int[] n = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] score = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        Arrays.sort(score);
        System.out.println(score[x-y]);        
    }
}