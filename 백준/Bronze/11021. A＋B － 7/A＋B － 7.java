
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
//StringTokenizer st = new StringTokenizer(br.readLine(), " ");
//int a = Integer.parseInt(st.nextToken());
//int b = Integer.parseInt(st.nextToken());
public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
        
        int n = Integer.parseInt(in.readLine());
        int[][] data = new int[n][2];
        for(int i=0;i<n;i++){
            StringTokenizer st = new StringTokenizer(in.readLine()," ");
            data[i][0] = Integer.parseInt(st.nextToken());
            data[i][1] = Integer.parseInt(st.nextToken());
            int a = data[i][0]+data[i][1];
        	out.write("Case #"+(i+1)+": "+String.valueOf(a)+"\n");
        }

        out.flush();
        out.close();
	}
}
