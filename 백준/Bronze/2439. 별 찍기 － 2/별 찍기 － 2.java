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
    	int n = Integer.parseInt(in.readLine());
    	int s = n-1;
    	int count =1;
    	for(int i=0;i<n;i++) {
    		for(int j=0;j<s;j++) {
    			out.write(" ");
    		}
    		for(int j=0;j<count;j++) {
    			out.write("*");    			
    		}
    		out.write("\n");
    		count++;
    		s--;
    	}
    	
    	out.flush();
    	out.close();
    }
}