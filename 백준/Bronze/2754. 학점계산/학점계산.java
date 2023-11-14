// https://github.com/KimYongJ/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
class Main{
    public static void main(String[] args)throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String score = br.readLine();
        double result = 0;
        char c = score.charAt(0);
        if(c=='A') result = 4;
        else if(c=='B') result = 3;
        else if(c=='C') result = 2;
        else if(c=='D') result = 1;
        else{
            System.out.println(0.0);
            return;
        }
        c = score.charAt(1);
        if(c=='+') result += 0.3;
        else if(c=='-') result -= 0.3;
        System.out.println(result);
    }
}