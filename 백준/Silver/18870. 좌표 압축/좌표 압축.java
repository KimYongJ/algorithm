import java.io.*;
import java.util.*;
import java.util.stream.*;
class Main{
    public static void main(String[] args)throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int l = Integer.parseInt(br.readLine());
        int[] arr = new int[l];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<l; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        int[] copy = arr.clone();
        Arrays.sort(copy);
        HashMap<Integer,Integer> hm = new HashMap<>();
        int rank = 0;
        for(int key : copy)
            if(!hm.containsKey(key))
                hm.put(key,rank++);
        
        for(int key : arr)
            sb.append(hm.get(key)).append(" ");
            
        System.out.println(sb);       
    }
}