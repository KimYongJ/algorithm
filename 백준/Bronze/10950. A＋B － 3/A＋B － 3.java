import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;


public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int num = Integer.parseInt(in.readLine());
		for(int i=0;i<num;i++) {
		
        String[] a = in.readLine().split(" ");
        System.out.println(Integer.parseInt(a[0])+Integer.parseInt(a[1]));
		}
	}
}

