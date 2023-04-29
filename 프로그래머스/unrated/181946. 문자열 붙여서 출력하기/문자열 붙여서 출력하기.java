import java.io.BufferedReader;
import java.io.InputStreamReader;
public class Solution {
    public static void main(String[] args)throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        String str = br.readLine().replace(" ","");
        
        System.out.println(str);
    }
}