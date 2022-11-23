import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine()," ");
        int g = Integer.parseInt(st.nextToken());
        int o = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());
        int result =-1;
        
        if(n-o>0){
            result = g/(n-o)+1;
        }
        
        
        System.out.println(result);
		
	}

}
