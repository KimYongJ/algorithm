import java.io.*;

class Main{
    static int num,result;
    static int[] arr = new int[3];
    public static void main(String[] args)throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int l = Integer.parseInt(br.readLine());
        while(l-->0){
            num = Integer.parseInt(br.readLine());
            result = 0;
            check(num);
            sb.append(result).append("\n");
        }
        System.out.print(sb);
    }
    public static void search(int depth, int max){
        if(depth==3){
            if(arr[0]+arr[1]+arr[2]==num) result = 1;
            return;         
        }
        for(int i=1; i<=max;i++){
            arr[depth] = eureka(i);
            search(depth+1,max);
            if(result==1) return;
        }
    }
    public static void check(int x){
        for(int i=1; i<x; i++)
            if(eureka(i)>=x){
                search(0,i-1); 
                return;
            }
    }
    public static int eureka(int x){
        return x*(x+1)/2;
    }
}