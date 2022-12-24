import java.io.*;
import java.util.*;
class Main{
    public static int[] count = new int[246913];
    public static void main(String[] args)throws Exception{
        countFunction(); // 시작부터 소수판별해서 저장함
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int n = 1;
        while((n = Integer.parseInt(br.readLine()))!=0){
            sb.append(count[2*n] - count[n]).append("\n");
        }
        System.out.println(sb);
    }
    public static void countFunction(){
        int countNum = 0;
        for(int i=2; i<=246912; i++){
            if(check(i))
                countNum++;            
            count[i] = countNum;
        }
    }
    public static boolean check(int n){
        for(int i=2; i*i<=n; i++){
            if(n%i==0) return false;
        }
        return true;
    }
}