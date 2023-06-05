import java.io.*;
import java.util.*;
class Solution{
    static int[] arr1,arr2;
    static int alen,blen,max;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String... args)throws Exception{
        StringBuilder sb = new StringBuilder();
        int l = Integer.parseInt(br.readLine());
        
        for(int i=1; i<=l; i++){
            max = 0;
			readData();    

            if(alen<=blen) 
                getMax(alen,arr1,blen,arr2);
            else 
                getMax(blen,arr2, alen, arr1);
            
            sb.append("#").append(i).append(" ").append(max).append("\n");
        }
        System.out.println(sb);
    }
    public static void getMax(int alen, int[] arr1, int blen, int[] arr2){
        for(int r=0; r <= blen-alen; r++){
            int sum = 0;
            
            for(int i=0; i<alen; i++) 
                sum += arr1[i] * arr2[i+r];
            
            max = sum>max ? sum : max;
        }
    }
    public static void readData()throws Exception{
        StringTokenizer st = new StringTokenizer(br.readLine());
        alen = Integer.parseInt(st.nextToken());
        blen = Integer.parseInt(st.nextToken());
        
        arr1 = new int[alen];
        arr2 = new int[blen];
        
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<alen; i++) arr1[i] = Integer.parseInt(st.nextToken());
        
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<blen; i++) arr2[i] = Integer.parseInt(st.nextToken());
    }
}