import java.io.BufferedReader;
import java.io.InputStreamReader;
class Main{
    public static void main(String[] args)throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("long ".repeat( Integer.parseInt(br.readLine())/4 ));
        System.out.print("int");
        
    }
}