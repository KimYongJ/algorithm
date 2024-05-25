import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main{
    public static void main(String[] args)throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String str = "1";
        try{
            for(int i=0; i<100; i++){
                str = br.readLine();
                if(str.length() < 1)
                    break;
                sb.append(str).append('\n');
            }
        }catch(Exception e){}
        System.out.print(sb);
    }
}