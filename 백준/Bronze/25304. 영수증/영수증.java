import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
        int total = Integer.parseInt(in.readLine());
        int max = Integer.parseInt(in.readLine());
        int result = 0;
        
        for(int i=0;i<max;i++){
            String[] input = in.readLine().split(" ");    
            result += Integer.parseInt(input[0])*Integer.parseInt(input[1]);
        }
        if(result==total){
            System.out.println("Yes");
        }else{
            System.out.println("No");
        }
		
	}

}
