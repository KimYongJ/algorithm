import java.io.*;

class Main{
    public static void main(String[] args)throws Exception{
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int l = Integer.parseInt(br.readLine());
        for(int i=1; i<=l; i++){
            sb.append(i).append("\n");
        }
        System.out.println(sb);
    }
    
}