import java.util.*;
import java.util.stream.*;
import java.io.*;

class Main{
    public static void main(String[] args)throws Exception{
        int result = 0;
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int len = Integer.parseInt(in.readLine());
        
        for(int i=1; i<=len; i++){
            if(i<100){
                result++;
                continue;
            }
	            int[] nlist = Stream.of(String.valueOf(i).split("")).mapToInt(Integer::parseInt).toArray();
	            int a = nlist[0]-nlist[1];
	            boolean check = true;
	            for(int j=0; j<nlist.length-1;j++){
	            		if(!(nlist[j]==nlist[j+1]+a))
	            			check=false;
	            }
	            if(check) {
	            	result++; 
	            }
        }
        
        System.out.println(result);
    }
}