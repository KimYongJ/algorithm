import java.io.*;
import java.util.*;

class Solution
{
	public static void main(String args[]) throws Exception
	{
        StringTokenizer st;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int l = Integer.parseInt(br.readLine());
        for(int i=1; i<l+1; i++){
            st = new StringTokenizer(br.readLine());
            float sum =0, cnt =0;
            while(st.hasMoreTokens()){
                sum += Integer.parseInt(st.nextToken());
                cnt++;                
        	}
            System.out.println("#"+i+" "+Math.round(sum/cnt));
		}
	}
}