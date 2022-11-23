import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
//StringTokenizer st = new StringTokenizer(br.readLine(), " ");
//int a = Integer.parseInt(st.nextToken());
//int b = Integer.parseInt(st.nextToken());
public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int x = Integer.parseInt(in.readLine());
        int num=1;
        int count=1;
        while(true){
            if(num>=x){
                break;
            }
            num+=count*6;
            count+=1;
        }
        System.out.println(count);
	}

}
