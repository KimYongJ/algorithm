import java.io.*;

class Main{
    public static void main(String[] args)throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int k = Integer.parseInt(br.readLine());
        int three = 0 ;
        while(true){
            if(k%5==0){
                System.out.println(k/5+three);
                return;
            }else if(k<0){
                System.out.println(-1);
                return;
            }
            k-=3;
            three++;          
        }
    }
}