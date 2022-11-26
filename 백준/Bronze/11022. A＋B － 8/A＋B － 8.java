import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main{
    public static void main(String[] args)throws IOException{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
        
        int n = Integer.parseInt(in.readLine())+1;
        for(int i=1;i<n;i++){
        StringTokenizer st = new StringTokenizer(in.readLine()," ");
        int n1= Integer.parseInt(st.nextToken());
        int n2= Integer.parseInt(st.nextToken());
        out.write("Case #"+i+": "+n1+" + "+n2+" = "+(n1+n2)+"\n");
        }
        out.flush();
        out.close();
    }
}