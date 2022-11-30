import java.io.*;
import java.util.*;
class Main{
    public static void main(String[] args)throws Exception{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int len = Integer.parseInt(in.readLine());
        
        for(int i=0;i<len;i++){
            StringTokenizer st = new StringTokenizer(in.readLine()," ");
            int h = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
			if(r % h == 0) {
				System.out.println((h * 100) + (r / h));
			} else {
				System.out.println(((r% h)* 100) + ((r / h) + 1));
			}
        }
    }
}