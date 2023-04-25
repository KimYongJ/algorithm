import java.io.BufferedReader;
import java.io.InputStreamReader;
public class Solution {
    public static void main(String[] args)throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String str = br.readLine();
        for(char c : str.toCharArray())
            if('A'<=c && c<='Z')
                sb.append((char)(c+' '));
            else
                sb.append((char)(c-' '));
        
        System.out.println(sb.toString());
    }
}