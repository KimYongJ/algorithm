import java.io.*;
import java.util.*;
import java.util.stream.*;
class Main{
    public static void main(String[] args)throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int l = Integer.parseInt(br.readLine()) , rank = 0;
        int[] arr = new int[l], copy = new int[l];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<l; i++){
            arr[i] = copy[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(copy);
        HashMap<Integer,Integer> hm = new HashMap<>();

        for(int key : copy)
            if(!hm.containsKey(key))
                hm.put(key,rank++);
        
        for(int key : arr)
            sb.append(hm.get(key)).append(" ");
            
        System.out.println(sb);       
    }
}