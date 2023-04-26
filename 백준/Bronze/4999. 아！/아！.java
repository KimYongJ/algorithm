import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main{
    public static void main(String[] args)throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str1 = br.readLine();
        String str2 = br.readLine();
        
        System.out.println(str1.length()>=str2.length() ? "go" : "no");
    }
}