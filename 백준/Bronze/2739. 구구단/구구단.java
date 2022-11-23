import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int a = Integer.parseInt(in.readLine());
        
        for(int i =1;i<10;i++){
            out.write(a+" * "+i+" = "+i*a+"\n");
        }
		
        
		
		
        
        
        
		out.flush();
		out.close();
	}

}
