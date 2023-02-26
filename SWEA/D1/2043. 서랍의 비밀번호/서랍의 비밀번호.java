import java.io.BufferedReader;
import java.io.InputStreamReader;
class Solution{
 	public static void main(String[] args)throws Exception{
        String s = new BufferedReader(new InputStreamReader(System.in)).readLine();
        System.out.println(1+Math.abs(Integer.parseInt(s.split(" ")[0])-Integer.parseInt(s.split(" ")[1])));
    }
}