import java.io.BufferedReader;
import java.io.InputStreamReader;
public class Main{
    public static void main(String... args)throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        int idx = Integer.parseInt(br.readLine());
        System.out.println(str.charAt(idx-1));
    }
}