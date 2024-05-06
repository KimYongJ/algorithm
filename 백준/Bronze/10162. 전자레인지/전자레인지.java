import java.io.*;

class Main{
    public static void main(String[] args)throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        if(T % 10 != 0){
            sb.append(-1);
        }else{
            sb.append(T / 300).append(' ');
            T %= 300;
            sb.append(T / 60).append(' ');
            T %= 60;
            sb.append(T / 10);
        }   
        System.out.print(sb);
    }
}