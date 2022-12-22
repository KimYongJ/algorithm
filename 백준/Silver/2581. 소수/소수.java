import java.io.*;

class Main{
    public static void main(String[] args)throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int start = Integer.parseInt(br.readLine());
        int end = Integer.parseInt(br.readLine());
        int min = 10000;
        int max = 0;
        for(int i=start; i<=end; i++){
            if(check(i)){
                max += i;
                if(min>i) min = i;
            }
        }
        if(max==0){
            System.out.println(-1);
            return;
        }
        System.out.println(max);
        System.out.println(min);
    }
    public static boolean check(int num){
        if(num==1) return false;
        for(int i=2; i*i<=num; i++){
            if(num%i==0) return false;
        }
        return true;
    }
}