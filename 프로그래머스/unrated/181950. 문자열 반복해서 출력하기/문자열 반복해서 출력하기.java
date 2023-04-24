import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args)throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String[] str = br.readLine().split(" ");
        int num = Integer.parseInt(str[1]);
        for(int i=0; i<num; i++)
            sb.append(str[0]);
        System.out.println(sb.toString());
    }
}