import java.io.*;

class Main{
    public static void main(String[] args)throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int num = Integer.parseInt(br.readLine()),i=2;
        if(num==1)return;
        if(check(num)){
            System.out.println(num);
            return;
        }
        
        while(true){
            if(!check(i)){
                i++;
                continue;
            }
            if(num/i==1 && num%i==0){
                sb.append(i).append("\n");
                break;
            }
            if(num%i==0){
                num /= i;
                sb.append(i).append("\n");
            }else
                i++;
        }
        System.out.println(sb);
    }
    public static boolean check(int num){
        for(int i=2; i*i<=num; i++){
            if(num%i==0)
                return false;
        }
        return true;
    }
}