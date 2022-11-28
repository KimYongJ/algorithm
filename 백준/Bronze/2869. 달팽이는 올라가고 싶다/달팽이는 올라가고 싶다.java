import java.io.*;
import java.util.*;
class Main{
    public static void main(String[] args)throws Exception{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st= new StringTokenizer(in.readLine()," ");
        int a= Integer.parseInt(st.nextToken());
        int b= Integer.parseInt(st.nextToken());
        int v= Integer.parseInt(st.nextToken());
        if(a>=v) {
        	System.out.println(1);
        	return;
        }
        if((v-a)<(a-b)) {
        	System.out.println(2);
        	return;
        }
                if((v-a)%(a-b)>0) {
        	System.out.println(((v-a)/(a-b))+2);
        	return;
        }
        System.out.println(((v-a)/(a-b))+1);
    }
}